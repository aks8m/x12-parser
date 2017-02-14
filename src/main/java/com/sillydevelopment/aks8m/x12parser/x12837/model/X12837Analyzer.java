package com.sillydevelopment.aks8m.x12parser.x12837.model;

/**
 * Created by aks8m on 2/14/17.
 */
public class X12837Analyzer {

    private X12837Model x12837Model = new X12837Model();
    private String[] x12StringData;

    public X12837Analyzer(String[] x12StringData) {
        this.x12StringData = x12StringData;
    }

    public X12837Model analyze(){

        firstAnalysis();
        secondAnalysis();
        finalAnalysis();

        return this.x12837Model;
    }

    private void firstAnalysis(){

        for(String line : x12StringData){
            System.out.println(line);
        }
    }

    private void secondAnalysis(){

    }

    private void finalAnalysis(){

    }
}
