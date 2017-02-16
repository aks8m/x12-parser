package com.sillydevelopment.aks8m.x12parser.x12837;

import com.sillydevelopment.aks8m.x12parser.io.PDFTextReader;
import com.sillydevelopment.aks8m.x12parser.factory.Parser;

import java.util.ArrayList;

/**
 * Created by aks8m on 2/7/17.
 */
public class X12837Parser implements Parser {

    private PDFTextReader pdfTextReader;
    private ArrayList<String> parsedDataStringArray;
    private String x12SourcePDFLocation;
    private String excelOutputLocation;
    private X12837Analyzer x12837Analyzer;

    public X12837Parser(String x12SourcePDFLocation, String excelOutputLocation) {
        this.x12SourcePDFLocation = x12SourcePDFLocation;
        this.excelOutputLocation = excelOutputLocation;
    }

    public String getX12SourcePDFLocation() {
        return x12SourcePDFLocation;
    }

    public String getExcelOutputLocation() {
        return excelOutputLocation;
    }

    public void parse(){
        pdfTextReader = new PDFTextReader(x12SourcePDFLocation);
        parsedDataStringArray = pdfTextReader.readPDFTextData();
    }

    public void analyze() {
        x12837Analyzer = new X12837Analyzer(parsedDataStringArray);
        x12837Analyzer.analyze();

    }

    public void write() {

    }

}
