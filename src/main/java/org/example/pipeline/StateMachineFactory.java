package org.example.pipeline;

import org.apache.commons.lang3.StringUtils;
import org.example.core.Response;
import org.example.core.graph.State;
import org.example.core.graph.StateMachine;
import org.example.core.graph.scenario.LinearScenario;
import org.example.core.graph.scenario.Scenario;
import org.example.core.graph.step.*;
import org.example.core.searcher.JsonFlatSearcher;
import org.example.core.searcher.Option;
import org.example.core.searcher.FlatSearcher;

import java.io.FileNotFoundException;
import java.util.*;

public class StateMachineFactory {
    private static final List<String> allowedCities = new ArrayList<>(Arrays.asList("Екатеринбург", "Москва"));
    public static final String START_RESPONSE_TEXT = (
            "Привет! Я помогу выбрать подходящую квартиру.\n" +
            "Если готовы начать поиск прямо сейчас, введите максимальную сумму, за которую готовы купить квартиру."
    );

    public static StateMachine create() throws FileNotFoundException {
        Map<String, String> nextScenario = new HashMap<>();
        nextScenario.put("simpleScenario", "simpleScenario");
        State startState = new State("simpleScenario");
        Response startResponse = new Response(START_RESPONSE_TEXT);

        return new StateMachine(createScenarios(), nextScenario, startState, startResponse);
    }

    private static Map<String, Scenario> createScenarios() throws FileNotFoundException{
        Map<String, Scenario> scenarios = new HashMap<>();
        scenarios.put("simpleScenario", createSimpleScenario());

        return scenarios;
    }

    private static Scenario createSimpleScenario() throws FileNotFoundException {
        List<Step> steps = new ArrayList<>(Arrays.asList(
                createMaxPriceStep(),
                createCityStep(),
                createFlatSuggestingStep()
        ));

        return new LinearScenario(steps, true);
    }

    private static Step createMaxPriceStep() {
        String cities = StringUtils.join(allowedCities, "\n");

        String onSuccessText = "Вы ввели корректное значение. Выберите город из списка:\n" + cities;
        String onFailText = "Некорректное значение. Введите сумму еще раз";

        return new LongNumberStep(onSuccessText, onFailText, Option.MAX_PRICE.getName());
    }

    private static Step createCityStep() {
        String onSuccessTextTemplate = "Идет подбор квартир в {0}.\nВведите количество комнат";
        String onFailTextTemplate =
                "Вы не ввели город из списка. Попробуйте ввести город еще раз!\nПоддерживаемые города: {0}.";
        return new TextExtractionStep(allowedCities, onSuccessTextTemplate, onFailTextTemplate, Option.CITY.getName());
    }

    private static Step createFlatSuggestingStep() throws FileNotFoundException{
        List<Step> steps = new ArrayList<>(Arrays.asList(
                createRoomsStep(),
                createFlatSearcherStep()
        ));
        String onFailText = "К сожалению квартир с такими параметрами нет :(";

        return new ChainStep(steps, onFailText);
    }

    private static Step createRoomsStep() {
        String onFailText = "Некорректное значение. Введите сумму еще раз";
        return new LongNumberStep(onFailText, Option.ROOMS_COUNT.getName());
    }

    private static Step createFlatSearcherStep() throws FileNotFoundException {
        FlatSearcher flatSearcher = new JsonFlatSearcher("flats.json");
        return new FlatSearcherStep(flatSearcher);
    }
}
