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
        Parser parser = ParserFactory.GetParser(X12Types.X12837, args[0], args[1]);
        parser.parse();
        parser.analyze();
        parser.write();
    }
}
