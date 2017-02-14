package com.sillydevelopment.aks8m.x12parser.factory;

import com.sillydevelopment.aks8m.x12parser.x12837.X12837Parser;

/**
 * Created by aks8m on 2/7/17.
 */
public class ParserFactory {

    public static Parser GetParser(X12Types x12Types, String x12SourcePDFLocation, String excelOutputLocation){

        switch (x12Types){
            case X12837: return new X12837Parser(x12SourcePDFLocation, excelOutputLocation);
        }

        return null;
    }
}
