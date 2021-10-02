package util;

public class PositionsUtil {
    /**
     * @param pos1 first position
     * @param pos2 second position
     * @return simple sum of two positions, assuming that their axles are pointed the same way
     */
    public static Position sum(Position pos1, Position pos2) {
        return new Position(pos1.getX() + pos2.getX(), pos1.getY() + pos2.getY(), pos1.getZ() + pos2.getZ());
    }

    public static double getDistanceBetween(Position pos1, Position pos2) {
        return Math.sqrt(squareOf(pos1.getX() - pos2.getX()) + squareOf(pos1.getY() - pos2.getY()) + squareOf(pos1.getZ() - pos2.getZ()));
    }

    private static double squareOf(double number) {
        return Math.pow(number, 2);
    }
}
