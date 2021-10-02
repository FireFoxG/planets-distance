import model.CelestialBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.Position;
import util.PositionsUtil;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionsTest {

    @Test
    @DisplayName("Checking calculation of distance between two positions")
    void testDistanceBetweenTwoPositions() {
        Position pos1 = new Position(1,1,-1);
        Position pos2 = new Position(3, 2, 1);
        assertEquals(3, PositionsUtil.getDistanceBetween(pos1, pos2));
        assertEquals(3, PositionsUtil.getDistanceBetween(pos2, pos1));
    }

    @Test
    @DisplayName("Checking calculation of distance between two celestial bodies")
    void testDistanceBetweenTwoCelestialBodies() {
        CelestialBody body1 = new CelestialBody("Alpha", new Position(5, -6, 3), Collections.emptyList());
        CelestialBody body2 = new CelestialBody("Beta", new Position(-3, 2, 7), Collections.emptyList());
        SolarSystemNavigator navigator = SolarSystemNavigator.getInstanceFromString("{}");
        assertEquals(12, navigator.getDistanceBetweenTwoCelestialBodies(body1, body2));
        assertEquals(12, navigator.getDistanceBetweenTwoCelestialBodies(body2, body1));
    }

    @Test
    @DisplayName("Check absolute position calculation")
    void testAbsolutePositionCalculation() {
        CelestialBody body1 = new CelestialBody("Alpha", new Position(5, -6, 3), Collections.emptyList());
        CelestialBody body2 = new CelestialBody("Beta", new Position(-2, 4, 6), List.of(body1));
        assertEquals(new Position(3, -2, 9), body1.getAbsolutePosition());
        body2.setParent(new CelestialBody("Barycenter", new Position(1, -1, 1), Collections.emptyList()));
        assertEquals(new Position(4, -3, 10), body1.getAbsolutePosition());
    }

    @Test
    @DisplayName("Checking calculation of distance between two celestial bodies, one of which is satellite")
    void testDistanceBetweenTwoCelestialBodiesIfOneIsSatellite() {
        CelestialBody body1 = new CelestialBody("Alpha", new Position(5, -6, 3), Collections.emptyList());
        CelestialBody body2 = new CelestialBody("Gamma", new Position(0.1, -0.5, -0.3), Collections.emptyList());
        CelestialBody body3 = new CelestialBody("Beta", new Position(-3.1, 2.5, 7.3), List.of(body2));
        SolarSystemNavigator navigator = SolarSystemNavigator.getInstanceFromString("{}");
        assertEquals(12, navigator.getDistanceBetweenTwoCelestialBodies(body1, body2));
        assertEquals(12, navigator.getDistanceBetweenTwoCelestialBodies(body2, body1));
    }




}
