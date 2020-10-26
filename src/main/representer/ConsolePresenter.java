package main.representer;

import main.businesslogic.Chat;
import main.businesslogic.Request;
import main.businesslogic.Response;

import java.util.Scanner;

public class ConsolePresenter implements Presenter {
    private Chat chat;

    public ConsolePresenter(Chat chat) {
        this.chat = chat;
    }

    @Override
    public void run() {
        Integer userId = 0;
        Integer userCount = 2;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();
            Request request = new Request(message, userId);
            Response response = this.chat.getResponse(request);
            System.out.println(response.getMessageText());
            }

    }
}

