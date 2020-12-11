package org.example.representer;

import org.example.core.Flat;
import org.example.core.Request;
import org.example.core.Response;
import org.example.core.graph.StateMachine;

import java.util.List;
import java.util.Scanner;

public class ConsolePresenter implements Presenter {
    private StateMachine stateMachine;
    private static final String consoleUserId = "console_user";

    public ConsolePresenter(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public void run() {
        respond("<start_message>");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();
            respond(message);
        }
    }

    private void respond(String message) {
        Request request = new Request(message, consoleUserId);
        Response response = this.stateMachine.getResponse(request);

        String consoleString = makeConsoleString(response);
        System.out.println(consoleString);
    }

    private static String makeConsoleString(Response response) {
        if (response.getMessageText() != null && response.getFlats() == null) {
            return response.getMessageText();
        }
        if (response.getMessageText() == null && response.getFlats() != null) {
            return flatsToString(response.getFlats());
        }
        if (response.getMessageText() != null && response.getFlats() != null) {
            String responseText = response.getMessageText();
            String flatsInText = flatsToString(response.getFlats());

            return responseText + "\n\n" + flatsInText;
        }

        return "Произошла ошибка. Попробуйте обратиться позже";
    }

    private static String flatsToString(List<Flat> flats) {
        StringBuilder builder = new StringBuilder();
        for (Flat flat : flats) {
            builder.append(flatToString(flat));
        }
        builder.append("\n\nСпасибо за обращение!");

        return builder.toString();
    }

    private static String flatToString(Flat flat) {
        return "---------------------------------------\n" +
                "Город: " + flat.getCity() + "\n" +
                "Цена: " + flat.getPrice() + "\n" +
                "Тип: " + flat.getType() + "\n" +
                "Площадь: " + flat.getSquare() + "\n" +
                "Район: " + flat.getArea() + "\n" +
                "Адрес: " + flat.getAddress() + "\n" +
                "Число комнат: " + flat.getRoomsCount() + "\n" +
                "---------------------------------------\n";
    }
}
