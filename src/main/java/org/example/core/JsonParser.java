package org.example.core;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.example.core.Flat;
import org.example.core.FlatSerializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public class JsonParser<T>{

    public List<T> deserialize(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        return mapper.readValue(new File(fileName), new TypeReference<List<T>>(){});
    }

    public void serialize(HashSet<Flat> flats) throws IOException{
        FileWriter writer = new FileWriter("flats.json", false);

        writer.append('[');

        for(Flat flat: flats){
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule("FlatSerializer",
                    new Version(1, 0, 0, null, null, null));
            module.addSerializer(Flat.class, new FlatSerializer());
            mapper.registerModule(module);

            writer.write(mapper.writeValueAsString(flat));
            writer.append(',');
        }

        writer.append(']');
    }

}
