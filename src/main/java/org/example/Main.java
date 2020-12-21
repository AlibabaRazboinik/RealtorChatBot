package org.example;

import java.io.FileNotFoundException;

import org.example.core.graph.StateMachine;
import org.example.pipeline.StateMachineFactory;
import org.example.representer.ConsolePresenter;
import org.example.representer.Presenter;
import org.example.representer.TelegramPresenter;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        StateMachine stateMachine = StateMachineFactory.create();
        Presenter presenter = new TelegramPresenter(stateMachine);
        presenter.run();
    }
}
