package org.example;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import org.example.core.JsonParser;
import org.example.parser.ParseException;
import org.example.parser.flat.AvitoFlatParser;
import org.example.parser.flat.CianFlatParser;
import org.example.core.Flat;
import org.example.parser.offerfeed.AvitoFeedParser;
import org.example.parser.offerfeed.CianFeedParser;
import org.example.parser.pipeline.ParserPipeLine;

import org.example.core.graph.StateMachine;
import org.example.pipeline.StateMachineFactory;
import org.example.representer.Presenter;
import org.example.representer.TelegramPresenter;


public class Main {
    public static void main(String args[]) throws IOException, ParseException, InterruptedException {
        // todo: create flats.json
        JsonParser jsonParser = new JsonParser();
        jsonParser.serialize(pipeLineParser());
    }

    private static HashSet<Flat> pipeLineParser() throws ParseException, InterruptedException, IOException {
        AvitoFeedParser avitoFeedParser = new AvitoFeedParser();
        AvitoFlatParser avitoFlatParser = new AvitoFlatParser();
        List<Flat> avitoFlats = ParserPipeLine.parse("avito", avitoFeedParser, avitoFlatParser);

        CianFeedParser cianFeedParser = new CianFeedParser();
        CianFlatParser cianFlatParser = new CianFlatParser();
        List<Flat> cianFlats = ParserPipeLine.parse("cian", cianFeedParser, cianFlatParser);

        HashSet<Flat> flats = new HashSet<>();
        flats.addAll(avitoFlats);
        flats.addAll(cianFlats);

        return flats;
    }

    private static void runChatBot() throws FileNotFoundException {
        StateMachine stateMachine = StateMachineFactory.create();
        Presenter presenter = new TelegramPresenter(stateMachine);
        presenter.run();
    }
}
