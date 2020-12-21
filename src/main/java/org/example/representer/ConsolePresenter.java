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

        String consoleString = PlainTextResponseFormatter.format(response);
        System.out.println(consoleString);
    }
}
