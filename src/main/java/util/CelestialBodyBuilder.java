package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.CelestialBody;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URL;

public class CelestialBodyBuilder {
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public static CelestialBody fromJsonString(String jsonString) {
        return getCelestialBody(jsonString);
    }

    public static CelestialBody fromJsonFileResource(String pathToJson) {
        return getCelestialBody(CelestialBodyBuilder.class.getResource(pathToJson));
    }

    @Nullable
    private static CelestialBody getCelestialBody(Object resource) {
        try {
            CelestialBody star = null;
            if (resource instanceof String) {
                star = objectMapper.readValue((String) resource, CelestialBody.class);
            } else if (resource instanceof URL) {
                star = objectMapper.readValue((URL) resource, CelestialBody.class);
            }
            if (star == null) {
                System.out.println("ERROR: Could not build celestial body from provided resource");
                return null;
            }
            star.init(); // to not overcomplicate things with custom deserializer
            return star;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
