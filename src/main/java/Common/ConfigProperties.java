package Common;

public class ConfigProperties {

    public static final String	API_URL=AutomationFrameworkBase.CONFIG.getProperty("url_qa");
    public static final String prod_API_URL=AutomationFrameworkBase.CONFIG.getProperty("prod_url");
    public static final String qaenv=AutomationFrameworkBase.CONFIG.getProperty("env");
    public static final String prodenv=AutomationFrameworkBase.CONFIG.getProperty("prod_env");
}
