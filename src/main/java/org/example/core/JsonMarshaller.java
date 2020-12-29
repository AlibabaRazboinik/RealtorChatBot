package org.example.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class JsonMarshaller {
    public static <T> List<T> deserialize(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(fileName), new TypeReference<List<T>>(){});
    }

    public static <T> void serialize(Collection<T> objects, String filePath) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(filePath), objects);
    }
}
