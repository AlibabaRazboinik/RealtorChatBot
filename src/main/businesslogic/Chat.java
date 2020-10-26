package main.businesslogic;

import main.businesslogic.searcher.JsonSearcher;
import main.businesslogic.searcher.SearchParams;


import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

// -1 состояние: состояние старта диалога
// 0 состояние: узнать максимальную цену
// 1 состояние: узнать нужный город
// 2 состояние: узнать кол-во комнат
// 3 состояние: выкатить варианты и попрощаться

public class Chat {
    HashMap<Integer, Integer> usersStates;
    HashMap<Integer, SearchParams> usersParams;

    public Chat() {
        this.usersStates = new HashMap<>();
        this.usersParams = new HashMap<>();
    }


    public Response getResponse(Request request) {
        Integer userID = request.getUserId();
        Integer state = -1;
        if (this.usersStates.containsKey(userID)) {
            state = this.usersStates.get(userID);
        } else
            this.usersStates.put(userID, state);

        switch (state) {
            case -1 -> {
                return startState(request);
            }
            case 0 -> {
                return state0(request);
            }
            case 1 -> {
                return state1(request);
            }
            case 2 -> {
                return state2(request);
            }
        }
        return null;
    }

    public Response startState(Request request) {
        this.usersStates.put(request.getUserId(), 0);
        this.usersParams.put(request.getUserId(), new SearchParams());
        return new Response("Привет! Я помогу выбрать подходящую квартиру.\n" +
                "Если готовы начать поиск прямо сейчас, введите максимальную сумму," +
                "за которую готовы купить квартиру."
        );
    }

    public Response state0(Request request) {
        Long maxPrice;
        try {
            maxPrice = Long.parseLong(request.getMessageText());
        } catch (NumberFormatException e) {
            maxPrice = null;
        }
        if (maxPrice != null) {
            this.usersParams.get(request.getUserId()).setMaxPrice(maxPrice);
            this.usersStates.put(request.getUserId(), 1);
            return new Response("Вы ввели корректное значение. " +
                    "Выберите город из списка:\n" +
                    "Екатеринбург\n Москва");
        }
        else {
            this.usersStates.put(request.getUserId(), 0);
            return new Response("Некорректное значение. Введите сумму еще раз");
        }
    }

    public Response state1(Request request) {
        if (request.getMessageText().contains("Москва")) {
            this.usersParams.get(request.getUserId()).setCity("Москва");
            this.usersStates.put(request.getUserId(), 2);
            return new Response("Идет подбор квартир в Москве.\n" +
                    "Введите количество комнат");
        }
        if (request.getMessageText().contains("Екатеринбург")) {
            this.usersParams.get(request.getUserId()).setCity("Екатеринбург");
            this.usersStates.put(request.getUserId(), 2);
            return new Response("Идет подбор квартир в Екатеринбурге.\n" +
                    "Введите количество комнат");
        }
        this.usersStates.put(request.getUserId(), 1);
        return new Response("Вы не ввели город из списка. \n" +
                "Попробуйте ввести город из списка (Екатеринбург, Москва) еще раз!");
    }

    public Response state2(Request request) {
        Integer roomsCount;
        try {
            roomsCount = Integer.parseInt(request.getMessageText());
        } catch (NumberFormatException e) {
            roomsCount = null;
        }
        if (roomsCount != null) {
            this.usersParams.get(request.getUserId()).setRoomsCount(roomsCount);
            this.usersStates.put(request.getUserId(), 3);
            try {
                JsonSearcher jsonSearcher = new JsonSearcher("offers.json");
                List<Flat> appropriateFlats = jsonSearcher.searchFlats(this.usersParams.get(request.getUserId()));
                if (appropriateFlats != null && appropriateFlats.size() != 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (Flat appropriateFlat : appropriateFlats) {
                        stringBuilder.append(appropriateFlat.getInfo());
                    }
                    this.usersStates.put(request.getUserId(), -1);
                    this.usersParams.put(request.getUserId(), new SearchParams());
                    return new Response(stringBuilder.toString() +
                            "\nСпасибо за обращение!", appropriateFlats);
                }
                this.usersStates.put(request.getUserId(), -1);
                this.usersParams.put(request.getUserId(), new SearchParams());
                return new Response("К сожалению, по вашему запросу " +
                        "в базе ничего не налось.");

            } catch (FileNotFoundException e) {
                this.usersStates.put(request.getUserId(), -1);
                this.usersParams.put(request.getUserId(), new SearchParams());
                return new Response("К сожалению, базаданных сломалась. " +
                        "Приносим извинения, скоро исправим проблемы.");
            }
        }
        this.usersStates.put(request.getUserId(), 2);
        return new Response("Введено некорректное значение.\n" +
                "Попробуйте ввести количество комнат еще раз!");
    }
}
