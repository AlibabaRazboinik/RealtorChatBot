package org.example;

import java.io.FileNotFoundException;

import org.example.core.graph.StateMachine;
import org.example.pipeline.StateMachineFactory;
import org.example.representer.ConsolePresenter;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        StateMachine stateMachine = StateMachineFactory.create();
        ConsolePresenter presenter = new ConsolePresenter(stateMachine);
        presenter.run();
    }
}
