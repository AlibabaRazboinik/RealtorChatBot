package main.businesslogic.searcher;

import main.businesslogic.Flat;

import java.util.List;

public interface Searcher {
    List<Flat> searchFlats(SearchParams params);
}
