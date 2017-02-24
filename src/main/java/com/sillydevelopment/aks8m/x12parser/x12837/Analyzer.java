package com.sillydevelopment.aks8m.x12parser.x12837;

import com.sillydevelopment.aks8m.x12parser.x12837.model.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by aks8m on 2/14/17.
 */
public class Analyzer {

    //REMOVE LATER
    int locationCount = 0;
    int actionCount = 0;
    int crCount = 0;
    //

    Model model;
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
        model = new Model( extractEntities() );
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
        return new Entity(location, action, cr);
    }

    private Location extractLocation(ArrayList<String> entityData){
        Location location;
        int actionIndex = 0;
        ArrayList<String> locationData;

        for (int i = 0; i < entityData.size(); i++) {
            if(entityData.get(i).contains("Action ")) {
                actionIndex = i;
            }
        }
        locationData = new ArrayList<String> ( entityData.subList(0, actionIndex) );

        if(( locationData.get(0).indexOf("|") == locationData.get(0).lastIndexOf("|") )
                && !(locationData.get(1).contains("-")) ) {
            //Is X12 Chapter Update
            location = new Location(true,locationData.get(1),"N/A"
                                    ,"N/A", "N/A", "N/A");
            locationCount++;
        }else{
            //Is X12 Update
            if(locationData.get(0).indexOf("|") == locationData.get(0).lastIndexOf("|") ) {
                //Scenario 1: Not chapter update, but missing all '|' data
                String segmentID = locationData.get(1).split("-")[0];
                String segmentName = "";

                if(locationData.get(1).split("-").length > 2) {
                    segmentName = locationData.get(1).replace(segmentID + " -", "");
                }else{
                    segmentName = locationData.get(1).split("-")[1];
                }

                location = new Location(false,"N/A","N/A"
                                        , "N/A", segmentID, segmentName);
                locationCount++;
            }else if(locationData.get(0).split("|").length == 4){
                //Scenario 2: Missing LoopID
                int splitLength = locationData.get(0).split("\\|").length;

                String positionID = locationData.get(0).split("\\|")[splitLength - 1];
                String segmentID = locationData.get(1).split("-")[0];
                String segmentName = "";

                if(locationData.get(1).split("-").length > 2) {
                    segmentName = locationData.get(1).replace(segmentID + " -", "");
                }else{
                    segmentName = locationData.get(1).split("-")[1];
                }

                location = new Location(false,"N/A", positionID, "N/A"
                                        , segmentID, segmentName);
                locationCount++;
            }else{
                //Scenario 3: Containing everything
                int splitLength = locationData.get(0).split("\\|").length;

                String positionID = locationData.get(0).split("\\|")[splitLength - 2];
                String loopID = locationData.get(0).split("\\|")[splitLength - 1];
                String segmentID = locationData.get(1).split("-")[0];
                String segmentName = "";

                if(locationData.get(1).split("-").length > 2) {
                    segmentName = locationData.get(1).replace(segmentID + " -", "");
                }else{
                    segmentName = locationData.get(1).split("-")[1];
                }

                location = new Location(false, "N/A", positionID, loopID
                                        , segmentID, segmentName);
                locationCount++;
            }
        }

        return location;
    }

    private Action extractAction(ArrayList<String> entityData){
        int actionIndex = 0;
        int crIndex = 0;
        ArrayList<String> actionData;

        for (int i = 0; i < entityData.size(); i++) {
            int splitLength = entityData.get(i).split("CR\\s\\d+").length;

            if(entityData.get(i).contains("Action ")) {
                actionIndex = i;
            }
            if(splitLength > 1) {
                crIndex = i;
            }
        }

        actionData = new ArrayList<String> ( entityData.subList(actionIndex, crIndex) );

        String actionType = actionData.get(0).split("Action ")[1];
        StringBuilder actionDescription = new StringBuilder();

        for(int i = 1; i < actionData.size(); i++){
            actionDescription.append(" " + actionData.get(i));
        }
        actionCount++;
        return new Action(actionType, actionDescription.toString());
    }

    private CR extractCR(ArrayList<String> entityData){
        int crIndex = 0;
        ArrayList<String> crData;

        for (int i = 0; i < entityData.size(); i++) {
            int splitLength = entityData.get(i).split("CR\\s\\d+").length;

            if(splitLength > 1) {
                crIndex = i;
            }
        }

        crData = new ArrayList<String> ( entityData.subList(crIndex, entityData.size()) );

        String crID = crData.get(0).replace( crData.get(0).split("CR\\s\\d+")[1], "" ).split("CR")[1];
        StringBuilder crDescription = new StringBuilder();
        crDescription.append(crData.get(0).split("CR\\s\\d+")[1]);

        for(int i = 1; i < crData.size(); i++ ) {
            crDescription.append(" " + crData.get(i));
        }

        crCount++;
        return new CR(crID, crDescription.toString());
    }

    @Override
    public String toString() {
        return "Analyzer{" +
                "locationCount=" + locationCount +
                ", actionCount=" + actionCount +
                ", crCount=" + crCount +
                ", model=" + model +
                ", x12StringData=" + x12StringData +
                ", ignoreIndexes=" + ignoreIndexes +
                ", locationIndexes=" + locationIndexes +
                ", ignoreStrings=" + Arrays.toString(ignoreStrings) +
                '}';
    }
}
