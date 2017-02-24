package com.sillydevelopment.aks8m.x12parser.x12837.model;

import java.util.List;

/**
 * Created by aks8m on 2/20/17.
 */
public class Action {

    private String actionType;
    private String actionDescription;

    public Action(String actionType, String actionDescription) {
        this.actionType = actionType;
        this.actionDescription = actionDescription;
    }

    public String getActionType() {
        return actionType;
    }

    public String getActionDescription() {
        return actionDescription;
    }
}
