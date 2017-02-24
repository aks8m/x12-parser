package com.sillydevelopment.aks8m.x12parser.x12837.model;

/**
 * Created by aks8m on 2/20/17.
 */
public class CR {

    private String crID;
    private String crDescription;

    public CR(String crID, String crDescription) {
        this.crID = crID;
        this.crDescription = crDescription;
    }

    public String getCrID() {
        return crID;
    }

    public String getCrDescription() {
        return crDescription;
    }
}
