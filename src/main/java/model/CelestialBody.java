package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import util.Position;
import util.PositionsUtil;

import java.util.List;

public class CelestialBody {
    @JsonProperty
    private String name;
    @JsonProperty
    private Position relativePosition;
    private CelestialBody parent;
    @JsonProperty
    private List<CelestialBody> satellites;

    public CelestialBody() {
    }

    public CelestialBody(String name, Position relativePosition, List<CelestialBody> satellites) {
        this.name = name;
        this.relativePosition = relativePosition;
        this.satellites = satellites;
        init();
    }

    /**
     * Setting relations to parent celestial body
     */
    public void init() {
        if (satellites != null) {
            satellites.forEach(satellite -> {
                satellite.setParent(this);
                satellite.init();
            });
        }
    }

    public Position getRelativePosition() {
        return relativePosition;
    }

    public void setParent(CelestialBody parent) {
        this.parent = parent;
    }

    public CelestialBody getParent() {
        return parent;
    }

    public Position getAbsolutePosition() {
        if (parent == null) {
            return relativePosition;
        }
        return PositionsUtil.sum(parent.getAbsolutePosition(), relativePosition);
    }

    public List<CelestialBody> getSatellites() {
        return satellites;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "model.CelestialBody{" +
                "name='" + name + '\'' +
                ", relativePosition=" + relativePosition +
                ", satellites=" + satellites +
                '}';
    }
}
