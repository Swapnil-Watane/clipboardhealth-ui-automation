package Common;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBaseClass {

    public TestBaseClass() {
        BasicConfigurator.configure();
    }

    protected static final Logger logger= Logger.getLogger(TestBaseClass.class);
    private static final String DRIVER_KEY = "webdriver.chrome.driver";
    private static final String DRIVER_VALUE = "chromeDriverLocation";
    private static final String DEFAULT_BASE_URL = "defaultBaseUrl";
    protected ConfigManager cm = new ConfigManager();
    protected WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty(DRIVER_KEY, cm.getProperty(DRIVER_VALUE));
        driver =  new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.navigate().to(cm.getProperty(DEFAULT_BASE_URL));
    }

    @AfterClass
    public void rollback() {
        try {
            driver.quit();
        } catch (Exception e) {
            logger.warn("Issue encountered while closing the driver: " + e.getMessage());
        }
    }

}
