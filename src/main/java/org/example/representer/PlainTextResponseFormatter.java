package org.example.representer;

import org.example.core.Flat;
import org.example.core.Response;
import org.example.pipeline.StateMachineFactory;

import java.util.List;

public class PlainTextResponseFormatter {
    static String format(Response response) {
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
        builder.append("\n\nСпасибо за обращение!\n\n");
        builder.append(StateMachineFactory.START_RESPONSE_TEXT);

        return builder.toString();
    }

    private static String flatToString(Flat flat) {
        // todo: actualize
        return "---------------------------------------\n" +
                "Город: " + flat.getCity() + "\n" +
                "Цена: " + flat.getPrice() + "\n" +
                "Площадь: " + flat.getSquare() + "\n" +
                "Адрес: " + flat.getAddress() + "\n" +
                "---------------------------------------\n";
    }
}
