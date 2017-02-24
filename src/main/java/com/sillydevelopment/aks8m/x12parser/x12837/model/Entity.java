package com.sillydevelopment.aks8m.x12parser.x12837.model;

import java.util.List;

/**
 * Created by aks8m on 2/16/17.
 */
public class Entity {

    private Location location;
    private Action action;
    private CR cr;

    public Entity(Location location, Action action, CR cr) {
        this.location = location;
        this.action = action;
        this.cr = cr;
    }

    public Location getLocation() {
        return location;
    }

    public Action getAction() {
        return action;
    }

    public CR getCr() {
        return cr;
    }
}
