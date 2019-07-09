package Utilities.ExtentR;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {

    public ExtentReports GetReport(String store_name) {

        String Report_Name= "Automation_Result_"+store_name;

        ExtentHtmlReporter reporter = new ExtentHtmlReporter("./ExtentReports/" + Report_Name + ".html");

        reporter.config().setDocumentTitle("Automation Test Execution Summary");

        // Create object of ExtentReports class- This is main class which will create report
        ExtentReports extent = new ExtentReports();
        extent.setSystemInfo("Operating System",System.getProperty("os.name"));
        extent.setSystemInfo("Operating System Version",System.getProperty("os.version"));


        // attach the reporter which we created in Step 1
        extent.attachReporter(reporter);
      return extent;
    }
}
