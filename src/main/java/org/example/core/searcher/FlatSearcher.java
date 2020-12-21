package org.example.core.searcher;

import org.example.core.Flat;

import java.util.List;

public interface FlatSearcher {
    List<Flat> searchFlats(SearchParams params);
}
