package deltaTests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.Driver;
import utilities.PropertyReader;
import utilities.SeleniumUtils;

import java.lang.reflect.Method;
import java.time.Duration;

public class TestBase {

    protected WebDriver driver;
    protected static ExtentReports report;
    protected static ExtentSparkReporter htmlReport;
    protected static ExtentTest logger;


    @BeforeSuite(alwaysRun = true)
    public void setupReport(){
        report = new ExtentReports();
        String path = System.getProperty("user.dir") + "/target/extentReports/extentReport.html";
        System.out.println(path);
        htmlReport = new ExtentSparkReporter(path);
        report.attachReporter(htmlReport);

        report.setSystemInfo("Name", "Delta Homepage Test Automation results");
        report.setSystemInfo("Browser", PropertyReader.getProperties("browser"));
        report.setSystemInfo("URL",PropertyReader.getProperties("urlHome"));
    }

    @AfterSuite(alwaysRun = true)
    public  void tearDownReport(){
        report.flush(); // write the output to HTML file
    }


    @BeforeMethod(alwaysRun = true)
    public void setupMethod(Method method) {

        driver = Driver.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        logger = report.createTest(method.getName());



    }

    @AfterMethod(alwaysRun = true)
    public void tearDownMethod(ITestResult testResult) {
        if(testResult.getStatus() == ITestResult.SUCCESS){
            logger.pass("Test PASSED: " + testResult.getName() );
        }else if(testResult.getStatus() == ITestResult.SKIP){
            logger.skip("Test SKIPPED: " + testResult.getName() );
        }else if(testResult.getStatus() == ITestResult.FAILURE){
            logger.fail("Test FAILED: " + testResult.getName() );
            logger.fail(testResult.getThrowable());
            logger.addScreenCaptureFromPath(SeleniumUtils.getScreenshotOnFailure());
        }

        Driver.quitDriver();
    }




}


