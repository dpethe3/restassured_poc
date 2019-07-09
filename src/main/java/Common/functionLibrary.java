package Common;

import org.testng.Assert;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class functionLibrary {
    private static final String TIMESTAMP_FORMAT = "ddMMyyyyhhmmss";
    public boolean verifyStringValue(String expected,String Actual)
    {
        boolean isSuccess= true;

     try {
         Assert.assertEquals(expected, Actual);

     }
     catch (AssertionError e) {

         isSuccess=false;
     }

        return isSuccess;

    }

    public boolean verifyNumberValue(int expected,int Actual)
    {
        boolean isSuccess= true;

        try {
            Assert.assertEquals(expected,Actual);

        }
        catch (AssertionError e) {

            isSuccess=false;
        }

        return isSuccess;

    }

    public  String getTimeStamp(String format) {

        return new SimpleDateFormat(format).format(new Timestamp(System.currentTimeMillis()));
    }

    public  String getTimeStamp() {
        return getTimeStamp(TIMESTAMP_FORMAT);
    }
}
