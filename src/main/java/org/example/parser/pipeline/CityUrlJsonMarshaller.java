package org.example.parser.pipeline;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.utils.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class CityUrlJsonMarshaller {
    public static List<CityUrl> deserialize(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String filePath = "city-urls/" + fileName;
        return mapper.readValue(ResourceLoader.loadStream(filePath), new TypeReference<List<CityUrl>>(){});
    }

    public static void serialize(Collection<CityUrl> objects, String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(filePath), objects);
    }
}
