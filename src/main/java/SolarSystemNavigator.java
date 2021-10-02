import model.CelestialBody;
import org.jetbrains.annotations.Nullable;
import util.CelestialBodyBuilder;
import util.PositionsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SolarSystemNavigator {
    private final Map<String, CelestialBody> celestialBodies;

    /**
     * Creates instance of SolarSystemNavigator from a JSON file located under resources folder
     * Expected JSON format:
     *  {
     *      name: string
     *      relativePosition: {
     *          x: double,
     *          y: double,
     *          z: double
     *      },
     *      satellites: []
     *  }
     *   where satellites is array of the elements similar to root element
     * @param resourceFileName name of the JSON file under resources folder
     * @return instance of SolarSystemNavigator
     */
    public static SolarSystemNavigator getInstance(String resourceFileName) {
        CelestialBody system = CelestialBodyBuilder.fromJsonFileResource(resourceFileName);
        return new SolarSystemNavigator(system);
    }

    /**
     * Creates instance of SolarSystemNavigator from a JSON string
     * @param jsonString string in JSON format
     * @return instance of SolarSystemNavigator
     */
    public static SolarSystemNavigator getInstanceFromString(String jsonString) {
        CelestialBody system = CelestialBodyBuilder.fromJsonString(jsonString);
        return new SolarSystemNavigator(system);
    }

    /**
     * @param name name of celestial body to look up
     * @return CelestialBody object if it exists, null otherwise
     */
    @Nullable
    public CelestialBody find(String name) {
        return celestialBodies.get(name);
    }

    /**
     * @param name1 name of first celestial body to look up
     * @param name2 name of second celestial body to look up
     * @return distance between two celestial bodies with given names, if both are found
     * @throws BodyNotFoundException if one of the celestial bodies was not found by provided name
     */
    public double getDistanceBetween(String name1, String name2) throws BodyNotFoundException {
        CelestialBody celestialBody1 = findOrFail(name1);
        CelestialBody celestialBody2 = findOrFail(name2);
        return getDistanceBetweenTwoCelestialBodies(celestialBody1, celestialBody2);
    }

    /**
     * Similar to getDistanceBetween, but with CelestialBody objects
     * @param body1 first celestial body
     * @param body2 second celestial body
     * @return distance between two bodies
     */
    public double getDistanceBetweenTwoCelestialBodies(CelestialBody body1, CelestialBody body2) {
        return PositionsUtil.getDistanceBetween(body1.getAbsolutePosition(), body2.getAbsolutePosition());
    }

    private SolarSystemNavigator(CelestialBody mainBody) {
        celestialBodies = getAllCelestialBodies(mainBody);
    }


    private CelestialBody findOrFail(String name) throws BodyNotFoundException {
        CelestialBody celestialBody = find(name);
        if(celestialBody == null) {
           throw new BodyNotFoundException("Could not find body with name %s%n".formatted(name));
        }
        return celestialBody;
    }

    private Map<String, CelestialBody> getAllCelestialBodies(CelestialBody celestialBody) {
        List<CelestialBody> celestialBodies = new ArrayList<>();
        recurseDown(celestialBodies, celestialBody);
        return celestialBodies.stream().collect(Collectors.toMap(CelestialBody::getName, body -> body));
    }

    private void recurseDown(List<CelestialBody> collector, CelestialBody celestialBody) {
        if(celestialBody == null) {
            return;
        }
        collector.add(celestialBody);
        if(celestialBody.getSatellites() == null) {
            return;
        }
        for(CelestialBody satellite : celestialBody.getSatellites()) {
            recurseDown(collector, satellite);
        }
    }

    static class BodyNotFoundException extends Exception {

        public BodyNotFoundException(String message) {
            super(message);
        }
    }
}
