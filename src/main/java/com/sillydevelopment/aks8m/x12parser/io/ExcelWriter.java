package com.sillydevelopment.aks8m.x12parser.io;

import com.sillydevelopment.aks8m.x12parser.x12837.model.Entity;
import com.sillydevelopment.aks8m.x12parser.x12837.model.Model;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by aks8m on 2/14/17.
 */
public class ExcelWriter {

    private static String[] columnHeaderTitles = {"Number", "Front Matter Section", "Loop ID", "Position Number", "Segment ID"
            , "Segment Name", "Reference Designator", "Data Element", "Data Element Name", "Action Type"
            ,"Action Description", "CR", "Change Request Description"};

    public static void WriteToExcel(Model model, String excelOutputLocation) throws IOException{
        FileOutputStream out = new FileOutputStream(excelOutputLocation);
        Workbook wb = new HSSFWorkbook();
        Sheet s = wb.createSheet();
        wb.setSheetName(0, "Claim 837P");

        createColumnHeaders(s.createRow(0));
        populateDataRows(model, s);

        wb.write(out);
        out.close();
    }

    private static void createColumnHeaders(Row columnHeaderRow){
        int columnCount = 0;
        for (String title : columnHeaderTitles) {
            Cell c = columnHeaderRow.createCell(columnCount);
            c.setCellValue(title);
            columnCount++;
        }
    }

    private static void populateDataRows(Model model, Sheet sheet){
        int rowCount = 1;
        for(Entity entity : model.getEntities()) {
            Row row = sheet.createRow(rowCount);
            row.createCell(0).setCellValue(rowCount);
            row.createCell(1).setCellValue(entity.getLocation().getFrontMatterSection());
            row.createCell(2).setCellValue(entity.getLocation().getLoopID());
            row.createCell(3).setCellValue(entity.getLocation().getPositionID());
            row.createCell(4).setCellValue(entity.getLocation().getSegmentID());
            row.createCell(5).setCellValue(entity.getLocation().getSegmentName());
            row.createCell(6).setCellValue("N/A");
            row.createCell(7).setCellValue("N/A");
            row.createCell(8).setCellValue("N/A");
            row.createCell(9).setCellValue(entity.getAction().getActionType());
            row.createCell(10).setCellValue(entity.getAction().getActionDescription());
            row.createCell(11).setCellValue(entity.getCr().getCrID());
            row.createCell(12).setCellValue(entity.getCr().getCrDescription());
            rowCount++;
        }
    }


}
