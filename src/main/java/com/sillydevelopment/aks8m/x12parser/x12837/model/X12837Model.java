package com.sillydevelopment.aks8m.x12parser.x12837.model;

import java.util.ArrayList;

/**
 * Created by aks8m on 2/7/17.
 */
public class X12837Model {

    private ArrayList entities = new ArrayList();

    public ArrayList getEntities() {
        return entities;
    }

    public void addEntity(X12837Entity x12837Entity){
        entities.add(x12837Entity);
    }

}
