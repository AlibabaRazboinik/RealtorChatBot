package org.example.core.graph.step;

import org.example.core.Flat;
import org.example.core.Request;
import org.example.core.Response;
import org.example.core.searcher.SearchParams;
import org.example.core.searcher.FlatSearcher;

import java.util.List;
import java.util.Map;

public class FlatSearcherStep implements Step {
    private final FlatSearcher searcher;

    public FlatSearcherStep(FlatSearcher flatSearcher) {
        this.searcher = flatSearcher;
    }

    @Override
    public Response respond(Request request, Map<String, Object> options) {
        SearchParams params = new SearchParams(options);
        List<Flat> flats = searcher.searchFlats(params);

        if (flats.size() > 0) {
            return new Response(flats);
        }
        return null;
    }
}
