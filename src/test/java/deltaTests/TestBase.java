package deltaTests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utilities.Driver;
import utilities.PropertyReader;
import utilities.SeleniumUtils;

import java.time.Duration;

public class TestBase {

    WebDriver driver;
    protected static ExtentReports report;
    protected static ExtentSparkReporter htmlReport;
    protected static ExtentTest logger;


    @BeforeMethod(alwaysRun = true)
    public void setupMethod(){

        driver = Driver.getDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));



    }

    @AfterMethod(alwaysRun = true)
    public void tearDownMethod(){

        SeleniumUtils.getScreenshotOnPass();
        SeleniumUtils.getScreenshotOnFailure();

        Driver.quitDriver();

    }



}
