package com.sillydevelopment.aks8m.x12parser.x12837.model;

/**
 * Created by aks8m on 2/20/17.
 */
public class Location {

    //Attributes if location block is for X12 chapter updates
    private boolean isChapterUpdate;
    private String frontMatterSection;

    //Attributes if locaiton block is for other X12 updates
    private String positionID;
    private String loopID;
    private String segmentID;
    private String segmentName;

    public Location(boolean isChapterUpdate, String frontMatterSection, String positionID, String loopID, String segmentID, String segmentName) {
        this.isChapterUpdate = isChapterUpdate;
        this.frontMatterSection = frontMatterSection;
        this.positionID = positionID;
        this.loopID = loopID;
        this.segmentID = segmentID;
        this.segmentName = segmentName;
    }

    public boolean isChapterUpdate() {
        return isChapterUpdate;
    }

    public String getFrontMatterSection() {
        return frontMatterSection;
    }

    public String getPositionID() {
        return positionID;
    }

    public String getLoopID() {
        return loopID;
    }

    public String getSegmentID() {
        return segmentID;
    }

    public String getSegmentName() {
        return segmentName;
    }
}
