package org.example;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.example.core.FlatJsonMarshaller;
import org.example.parser.ParseException;
import org.example.parser.connector.JsoupConnector;
import org.example.parser.flat.AvitoFlatParser;
import org.example.parser.flat.CianFlatParser;
import org.example.core.Flat;
import org.example.parser.feed.AvitoFeedParser;
import org.example.parser.feed.CianFeedParser;
import org.example.parser.pipeline.ParserPipeLine;

import org.example.agent.JsoupParse;
import org.example.core.graph.StateMachine;
import org.example.pipeline.StateMachineFactory;
import org.example.representer.Presenter;
import org.example.representer.TelegramPresenter;
import org.jsoup.nodes.Document;


public class Main {
    public static void main(String args[]) throws IOException, ParseException, InterruptedException {
        Collection<Flat> flats = pipeLineParser();
        FlatJsonMarshaller.serialize(flats, "parsedFlats.json");
    }

    private static HashSet<Flat> pipeLineParser() throws ParseException, InterruptedException, IOException {
        JsoupConnector connector = new JsoupConnector();

        System.out.println("Avito parsing");
        AvitoFeedParser avitoFeedParser = new AvitoFeedParser(connector);
        AvitoFlatParser avitoFlatParser = new AvitoFlatParser(connector);
        List<Flat> avitoFlats = ParserPipeLine.parse("avito", avitoFeedParser, avitoFlatParser);

        System.out.println("Cian parsing");
        CianFeedParser cianFeedParser = new CianFeedParser(connector);
        CianFlatParser cianFlatParser = new CianFlatParser(connector);
        List<Flat> cianFlats = ParserPipeLine.parse("cian", cianFeedParser, cianFlatParser);

        HashSet<Flat> flats = new HashSet<>();
        flats.addAll(avitoFlats);
        flats.addAll(cianFlats);

        return flats;
    }

    private static void runChatBot() throws IOException {
        StateMachine stateMachine = StateMachineFactory.create();
        Presenter presenter = new TelegramPresenter(stateMachine);
        presenter.run();
    }

    public static void jsoupParser() {
        JsoupParse parser = new JsoupParse();
        try {
            Document doc = parser.getJsoupDocument("https://www.avito.ru/ekaterinburg/nedvizhimost");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}