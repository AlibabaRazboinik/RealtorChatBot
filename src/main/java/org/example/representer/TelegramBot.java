package org.example.representer;

import org.example.core.Request;
import org.example.core.Response;
import org.example.core.graph.StateMachine;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {
    private final StateMachine stateMachine;

    TelegramBot(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public void onUpdateReceived(Update update) {
        update.getUpdateId();

        if (update.hasMessage()) {
            long chatId = update.getMessage().getChatId();

            String userId = "tg_" + chatId;
            String message_text = update.getMessage().getText();

            Request request = new Request(message_text, userId);
            Response response = stateMachine.getResponse(request);
            String responseText = PlainTextResponseFormatter.format(response);

            SendMessage sendMessage = new SendMessage().setChatId(chatId);
            sendMessage.setText(responseText);

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() { return "@handy_realtor_bot"; }

    @Override
    public String getBotToken() { return "1468035361:AAEY-R3XXXAzK2VfTk0yeYMdheuy4D2iCxQ"; }

}