package org.example.representer;

import org.example.core.graph.StateMachine;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class TelegramPresenter implements Presenter {
    private final TelegramBot telegramBot;
    private final TelegramBotsApi telegram;

    public TelegramPresenter(StateMachine stateMachine) {
        ApiContextInitializer.init();
        this.telegram = new TelegramBotsApi();
        this.telegramBot = new TelegramBot(stateMachine);
    }

    @Override
    public void run() {
        try {
            telegram.registerBot(telegramBot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
