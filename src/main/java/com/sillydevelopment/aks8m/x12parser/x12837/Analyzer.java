package com.sillydevelopment.aks8m.x12parser.x12837;

import com.sillydevelopment.aks8m.x12parser.x12837.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aks8m on 2/14/17.
 */
public class Analyzer {

    private Model model = new Model();
    private ArrayList<String> x12StringData;
    private ArrayList<Integer> ignoreIndexes = new ArrayList<Integer>();
    private ArrayList<Integer> locationIndexes = new ArrayList<Integer>();

    private String[] ignoreStrings = {"007030X323 - HEALTH CARE CLAIM: PROFESSIONAL (837)"
                                        ,"FEBRUARY 2017 "};

    public Analyzer(ArrayList<String> x12StringData) {
        this.x12StringData = x12StringData;
    }

    public Model getModel() {
        return model;
    }

    public void analyze() {
        initAnalysis();
        buildModel();
    }

    private void initAnalysis() {
        findIgnoreIndexes();
        removeIgnoreIndexes();
        findLocationIndexes();
    }

    private void findIgnoreIndexes() {
        for (int i = 0; i < x12StringData.size(); i++) {
            for (String ignoreString : ignoreStrings) {
                if (x12StringData.get(i).contains(ignoreString)) {
                    ignoreIndexes.add(i);
                }
            }
        }
    }

    private void removeIgnoreIndexes() {
        int shiftCount = 0;
        for (int index : ignoreIndexes) {
            x12StringData.remove(index - shiftCount);
            shiftCount++;
        }
    }

    private void findLocationIndexes() {
        for (int i = 0; i < x12StringData.size(); i++) {
            if (x12StringData.get(i).contains("Location X323")) {
                locationIndexes.add(i);
            }
        }
    }

    private void buildModel() {
        extractEntities();
        //perform sub analysis on location, action, and cr to fit output
        //Add to model all entities
    }

    private ArrayList<Entity> extractEntities() {
        ArrayList<Entity> entities = new ArrayList<Entity>();
        for (int i = 0; i < locationIndexes.size() - 1; i++) {
            entities.add(
                    buildEntity(
                            findEntityData(locationIndexes.get(i), locationIndexes.get(i + 1)) ) );
        }

        //Need to handle the last location index in the locationIndexes array
        entities.add(
                buildEntity(
                        findEntityData(locationIndexes.get(locationIndexes.size() -1), x12StringData.size()) ) );
        return entities;
    }

    private ArrayList<String> findEntityData(int start, int end) {
        return new ArrayList<String>( x12StringData.subList(start,end) );
    }

    private Entity buildEntity(ArrayList<String> entityData){
        Location location;
        Action action;
        CR cr;

        location = extractLocation(entityData);
        action = extractAction(entityData);
        cr = extractCR(entityData);

        return new Entity(null, null, null);
    }

    private Location extractLocation(ArrayList<String> entityData){
        return new Location(null);
    }

    private Action extractAction(ArrayList<String> entityData){
        return new Action(null);
    }

    private CR extractCR(ArrayList<String> entityData){
        return new CR(null);
    }

}
