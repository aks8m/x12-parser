package com.sillydevelopment.aks8m.x12parser.x12837;

import com.sillydevelopment.aks8m.x12parser.x12837.model.X12837Entity;
import com.sillydevelopment.aks8m.x12parser.x12837.model.X12837Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aks8m on 2/14/17.
 */
public class X12837Analyzer {

    private X12837Model x12837Model = new X12837Model();
    private ArrayList<String> x12StringData;
    private ArrayList<Integer> locationIndexes = new ArrayList<Integer>();
    private ArrayList<Integer> ignoreIndexes = new ArrayList<Integer>();

    private String[] ignoreStrings = {"007030X323 - HEALTH CARE CLAIM: PROFESSIONAL (837)"
                                        ,"FEBRUARY 2017 "};

    public X12837Analyzer(ArrayList<String> x12StringData) {
        this.x12StringData = x12StringData;
    }

    public X12837Model analyze(){

        X12837Model x12837Model = new X12837Model();

        preProcessStringData();
        buildX12837Model(x12837Model);

        return x12837Model;
    }

    private void preProcessStringData(){

        for(int i = 0; i < x12StringData.size(); i++){

            if(x12StringData.get(i).contains("Location X323")){
                locationIndexes.add(i);
            }

            for(String ignoreString : ignoreStrings){

                if(x12StringData.get(i).contains(ignoreString)){
                    ignoreIndexes.add(i);
                    System.out.println(x12StringData.get(i));
                }
            }
        }
    }

    private X12837Model buildX12837Model(X12837Model x12837Model){

        extractEntityChunks(x12837Model);

        return x12837Model;
    }

    private void extractEntityChunks(X12837Model x12837Model){

        for(int i = 0; i < locationIndexes.size() - 1; i++){

            x12837Model.addX12837Entity(findEntity(locationIndexes.get(i), locationIndexes.get(i + 1)));
        }

        x12837Model.addX12837Entity(findEntity(locationIndexes.get(locationIndexes.size() -1), x12StringData.size()));
    }

    private X12837Entity findEntity(int start, int end){

        List entity = x12StringData.subList(start,end);
        return new X12837Entity(entity);
    }

}
