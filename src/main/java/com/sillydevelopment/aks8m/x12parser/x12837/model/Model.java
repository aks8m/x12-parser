package com.sillydevelopment.aks8m.x12parser.x12837.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aks8m on 2/21/17.
 */
public class Model {

    private List<Entity> entities = new ArrayList<Entity>();

    public Model(List<Entity> entities) {
        this.entities = entities;
    }

    public List<Entity> getEntities() {
        return entities;
    }
}
