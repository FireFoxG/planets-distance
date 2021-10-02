package util;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Position {
    @JsonProperty
    private double x;
    @JsonProperty
    private double y;
    @JsonProperty
    private double z;

    public Position() {
    }

    public Position(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public String toString() {
        return  "x=" + x +
                ", y=" + y +
                ", z=" + z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Double.compare(position.x, x) == 0 &&
                Double.compare(position.y, y) == 0 &&
                Double.compare(position.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
