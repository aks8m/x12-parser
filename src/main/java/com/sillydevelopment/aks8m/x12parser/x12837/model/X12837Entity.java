package com.sillydevelopment.aks8m.x12parser.x12837.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aks8m on 2/16/17.
 */
public class X12837Entity {

    private List<String> rawTextData;

    public X12837Entity(List<String> rawTextData) {
        this.rawTextData = rawTextData;
    }

    public List<String> getRawTextData() {
        return rawTextData;
    }

    public void setRawTextData(List<String> rawTextData) {
        this.rawTextData = rawTextData;
    }
}
