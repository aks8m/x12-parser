package com.sillydevelopment.aks8m;

import com.sillydevelopment.aks8m.x12parser.factory.Parser;
import com.sillydevelopment.aks8m.x12parser.factory.ParserFactory;
import com.sillydevelopment.aks8m.x12parser.factory.X12Types;

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
        Parser parser = ParserFactory.GetParser(X12Types.X12837,
                                                args[0],
                                                "/Users/aks8m/code/x12-parser/ouput");
        parser.parse();
        parser.analyze();
        parser.write();
    }
}
