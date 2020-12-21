package org.example.core.graph.step;

import org.apache.commons.lang3.StringUtils;
import org.example.core.Request;
import org.example.core.Response;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class TextExtractionStep implements Step {
    public final List<String> textsToExtract;
    public final String onSuccessTextTemplate;
    public final String onFailTextTemplate;
    public final String optionName;

    public TextExtractionStep(
            List<String> textsToExtract,
            String onSuccessTextTemplate,
            String onFailTextTemplate,
            String optionName
    ) {
        this.textsToExtract = textsToExtract;
        this.onSuccessTextTemplate = onSuccessTextTemplate;
        this.onFailTextTemplate = onFailTextTemplate;
        this.optionName = optionName;
    }
    @Override
    public Response respond(Request request, Map<String, Object> options) {
        String requestText = request.getMessageText();
        for (String textToExtract : textsToExtract) {
            if (requestText.contains(textToExtract)) {
                options.put(optionName, textToExtract);
                String onSuccessText = MessageFormat.format(onSuccessTextTemplate, textToExtract);

                return new Response(onSuccessText, true);
            }
        }

        String allowedTexts = StringUtils.join(textsToExtract, ", ");
        String onFailText = MessageFormat.format(onFailTextTemplate, allowedTexts);
        return new Response(onFailText);
    }
}
