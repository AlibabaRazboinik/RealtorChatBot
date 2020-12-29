package org.example.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceLoader {
    public static InputStream loadStream(String resourcePath) throws FileNotFoundException {
        ClassLoader loader = ResourceLoader.class.getClassLoader();
        InputStream stream = loader.getResourceAsStream(resourcePath);
        if (stream == null) {
            throw new FileNotFoundException("File \"" + resourcePath + "\" not found");
        }

        return stream;
    }

    public static BufferedReader loadBufferedReader(String resourcePath) throws FileNotFoundException {
        InputStream stream = loadStream(resourcePath);
        return new BufferedReader(new InputStreamReader(stream));
    }
}
