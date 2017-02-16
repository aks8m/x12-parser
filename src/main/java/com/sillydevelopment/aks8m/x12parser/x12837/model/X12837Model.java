package com.sillydevelopment.aks8m.x12parser.x12837.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aks8m on 2/15/17.
 */
public class X12837Model {

    List<X12837Entity> x12837Entities = new ArrayList<X12837Entity>();

    public List<X12837Entity> getX12837Entities() {
        return x12837Entities;
    }

    public void addX12837Entity(X12837Entity x12837Entity){
        x12837Entities.add(x12837Entity);
    }

}
