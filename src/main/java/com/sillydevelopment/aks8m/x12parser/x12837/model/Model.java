package com.sillydevelopment.aks8m.x12parser.x12837.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aks8m on 2/15/17.
 */
public class Model {

    private List<Entity> x12837Entities = new ArrayList<Entity>();

    public List<Entity> getX12837Entities() {
        return x12837Entities;
    }

    public void addX12837Entity(Entity entity) {
        x12837Entities.add(entity);
    }



}
