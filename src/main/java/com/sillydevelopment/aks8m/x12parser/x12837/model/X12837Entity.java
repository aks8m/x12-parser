package com.sillydevelopment.aks8m.x12parser.x12837.model;


/**
 * Created by aks8m on 2/7/17.
 */
public class X12837Entity {

    private Location location;
    private Action action;
    private CR cr;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public CR getCr() {
        return cr;
    }

    public void setCr(CR cr) {
        this.cr = cr;
    }
}
