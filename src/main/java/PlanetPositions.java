public class PlanetPositions {
    public static void main(String[] args) throws SolarSystemNavigator.BodyNotFoundException {
        SolarSystemNavigator navigator = SolarSystemNavigator.getInstance("/planets.json");
        //SolarSystemNavigator navigator = SolarSystemNavigator.getInstanceFromString("{}");
        System.out.println(navigator.getDistanceBetween("Mün", "Kerbal"));
    }
}
