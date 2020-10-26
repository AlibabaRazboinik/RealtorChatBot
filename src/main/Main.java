package main;

import java.io.FileNotFoundException;

import main.businesslogic.Chat;
import main.representer.ConsolePresenter;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Chat chat = new Chat();
        ConsolePresenter presenter = new ConsolePresenter(chat);
        presenter.run();

    }
}
