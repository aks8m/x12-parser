package com.sillydevelopment.aks8m.x12parser.x12837;

import com.sillydevelopment.aks8m.x12parser.io.ExcelWriter;
import com.sillydevelopment.aks8m.x12parser.io.PDFTextReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by aks8m on 2/7/17.
 */
public class Parser implements com.sillydevelopment.aks8m.x12parser.factory.Parser {

    private PDFTextReader pdfTextReader;
    private ArrayList<String> parsedDataStringArray;
    private String x12SourcePDFLocation;
    private String excelOutputLocation;
    private Analyzer analyzer;

    public Parser(String x12SourcePDFLocation, String excelOutputLocation) {
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
        parsedDataStringArray = PDFTextReader.ReadPDFTextData(x12SourcePDFLocation, "\n");
    }

    public void analyze() {
        analyzer = new Analyzer(parsedDataStringArray);
        analyzer.analyze();
    }

    public void write() {
        try {
            ExcelWriter.WriteToExcel(analyzer.getModel(), excelOutputLocation);
        }catch (IOException ioE){
            ioE.printStackTrace();
        }
    }

}
