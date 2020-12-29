package org.example.core.searcher;
import org.example.core.Flat;
import org.example.core.FlatJsonMarshaller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JsonFlatSearcher implements FlatSearcher {
    private final List<Flat> flats;

    public JsonFlatSearcher(String filePath) throws IOException {
        flats = FlatJsonMarshaller.deserialize(filePath);
    }

    @Override
    public List<Flat> searchFlats(SearchParams params) {
        ArrayList<Flat> appropriateFlats = new ArrayList<>();
        for (Flat flat : flats) {
            if (isAppropriateFlat(flat, params))
                appropriateFlats.add(flat);
        }
        return appropriateFlats;
    }

    private boolean isAppropriateFlat(Flat flat, SearchParams params) {
        if (flat.getPrice() > params.getMaxPrice())
            return false;
        if (!flat.getRoomsAmount().equals(params.getRoomsAmount()))
            return false;

        return flat.getCity().equalsIgnoreCase(params.getCity());
    }
}
