package com.sillydevelopment.aks8m.x12parser.io;

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

/**
 * Created by aks8m on 2/14/17.
 */
public class PDFTextReader {

    private String splitRegex = "\n";
    private String x12filePath;

    public PDFTextReader(String x12filePath){
        this.x12filePath = x12filePath;
    }

    public String[] readPDFTextData(){
        PDFParser pdfParser;
        PDDocument pdDocument;
        PDFTextStripper pdfTextStripper;
        String[] textData = null;

        try {
            pdfParser = new PDFParser(new RandomAccessFile(new File(this.x12filePath), "r"));
            pdfParser.parse();

            pdDocument = new PDDocument(pdfParser.getDocument());
            pdfTextStripper = new PDFTextStripper();
            pdfTextStripper.setEndPage(pdDocument.getNumberOfPages());

            textData = pdfTextStripper.getText(pdDocument).split(splitRegex);
        }catch (IOException ioE) {
            ioE.printStackTrace();
        }

        return textData;
    }

}
