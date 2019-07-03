package Utilities;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtilities {
    private static XSSFSheet ExcelWSheet;

    private static XSSFWorkbook ExcelWBook;

    private static XSSFCell cell;

    private static XSSFRow row;

    public static Object[][] readExcel(String datafile) throws IOException {
        DataFormatter dataFormatter = new DataFormatter();
        Object[][] data = null;
        String line = null;

        FileInputStream fis = new FileInputStream(datafile);
        ExcelWBook = new XSSFWorkbook(fis);
        ExcelWSheet = ExcelWBook.getSheetAt(0);
        int totalrows = ExcelWSheet.getPhysicalNumberOfRows();
        int ncols = ExcelWSheet.getRow(0).getLastCellNum();
        System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
        try {
            for (Row row : ExcelWSheet) {
                for (Cell cell : row) {

                    //String cellValue = cell.getStringCellValue();
                    String cellValue=dataFormatter.formatCellValue(cell);
                    System.out.print(cellValue + "\t");

                }
                System.out.println();
            }

            data = new Object[totalrows-1][ncols];

            for (int i = 1; i < totalrows; i++) {
                row = ExcelWSheet.getRow(i);
                for (int j = 0; j < ncols; j++) {
                    if (row == null) {
                        data[i][j] = "";
                    } else {

                        cell = row.getCell(j);
                        if (cell == null) {
                            data[i][j] = "";
                        } else {
                            String value = dataFormatter.formatCellValue(cell);
                            data[i-1][j] = value;
                        }

                    }
                }
            }

            }
        catch(Exception e){
                e.printStackTrace();
            }


            return data;

    }
}

