package com.sillydevelopment.aks8m.x12parser.io;

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by aks8m on 2/14/17.
 */
public class PDFTextReader {

    public static ArrayList<String> ReadPDFTextData(String x12filePath, String splitRegex){
        PDFParser pdfParser;
        PDDocument pdDocument;
        PDFTextStripper pdfTextStripper;
        ArrayList<String> textData = null;

        try {
            pdfParser = new PDFParser(new RandomAccessFile(new File(x12filePath), "r"));
            pdfParser.parse();

            pdDocument = new PDDocument(pdfParser.getDocument());
            pdfTextStripper = new PDFTextStripper();
            pdfTextStripper.setEndPage(pdDocument.getNumberOfPages());
            textData = new ArrayList<String>(Arrays.asList(pdfTextStripper.getText(pdDocument).split(splitRegex)));
        }catch (IOException ioE) {
            ioE.printStackTrace();
        }

        return textData;
    }

}
