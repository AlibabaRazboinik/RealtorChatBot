package org.example.core.graph.step;

import org.example.core.Request;
import org.example.core.Response;

import java.util.Map;

public class LongNumberStep implements Step {
    private String onSuccessText;
    private final String onFailText;
    private final String optionName;
    private final boolean needRespondWithNullOnSuccess;

    public LongNumberStep(String onSuccessText, String onFailText, String optionName) {
        this.onSuccessText = onSuccessText;
        this.onFailText = onFailText;
        this.optionName = optionName;
        needRespondWithNullOnSuccess = false;
    }

    public LongNumberStep(String onFailText, String optionName) {
        this.onFailText = onFailText;
        this.optionName = optionName;
        needRespondWithNullOnSuccess = true;
    }

    public Response respond(Request request, Map<String, Object> options) {
        try {
            String messageText = request.getMessageText();
            Long parsedValue = Long.parseLong(messageText);
            options.put(optionName, parsedValue);

            if (needRespondWithNullOnSuccess) {
                return null;
            }

            return new Response(onSuccessText, true);
        } catch (NumberFormatException e) {
            return new Response(onFailText, false);
        }
    }
}
