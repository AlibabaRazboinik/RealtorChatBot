package org.example.core.searcher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.core.Flat;
import org.example.core.FlatSerializer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class JsonFlatSearcher implements FlatSearcher {
    private final List<Flat> flats;

    public JsonFlatSearcher(String filePath) throws FileNotFoundException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Flat.class, new FlatSerializer()).create();
        FileReader file = new FileReader(filePath);

        flats = gson.fromJson(file, new TypeToken<List<Flat>>() {}.getType());
    }

    public List<Flat> getFlats() {
        return this.flats;
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
