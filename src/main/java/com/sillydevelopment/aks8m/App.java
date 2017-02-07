package com.sillydevelopment.aks8m;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

/**
 *
 * Application entry point
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {

        PDFParser pdfParser = new PDFParser(
                new RandomAccessFile(new File("input/837_Health Care Claim Professional X323-change-log.pdf"), "r"));
        pdfParser.parse();
        PDDocument pdDocument = new PDDocument(pdfParser.getDocument());
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        pdfTextStripper.setEndPage(pdDocument.getNumberOfPages());
        System.out.println(pdfTextStripper.getText(pdDocument));

    }
}
