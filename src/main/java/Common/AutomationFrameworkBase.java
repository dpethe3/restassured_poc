package Common;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class AutomationFrameworkBase {


    public static Properties	CONFIG	= null;


    public AutomationFrameworkBase()
    {
    }
    public void loadConfigProperties(String propFilePath)
    {
        // Properties prop;
        try {
            CONFIG = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/config.properties");
            CONFIG.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

