package Utilities;

import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TestDataProvider {

    ExcelUtilities excelutilities = null;

    @DataProvider
    public static Object[][] authHeader_queryfile() throws Exception {
        return getTestData_queryfile("llpclienttoken");
    }

    public static Object[][] getTestData_queryfile(String filename) throws IOException {
       // String dataFile = System.getProperty(filename, filename + ".xlsx");
        //String dataFile= System.getProperty(filename,"src/test/resources/dataFiles/" + filename +".xlsx");
        String dataFile =  "D:/R_01/restassured_poc/src/test/resources/dataFiles/"+ filename +".xlsx";
      // Object[][] data=locateFile(dataFile);
        Object[][] data=ExcelUtilities.readExcel(dataFile);;
        return data;
    }


}



