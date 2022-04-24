package deltaTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.SeleniumUtils;


import java.time.Duration;

public class helpCenterTests extends TestBase {

    WebDriver driver;

    @BeforeClass
    public void setupClass(){
        WebDriverManager.chromedriver().setup();
    }




    @BeforeMethod
    public void navigateToHelpCenter() throws InterruptedException {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait( Duration.ofSeconds( 5 ) );
        driver.manage().window().maximize();
        driver.get( "https://www.delta.com/us/en/need-help/overview" );
        driver.manage().deleteAllCookies();


        //Handle the cookies notification:
        driver.findElement( By.xpath( "//button[@class='cookie-close-icon float-right circle-outline']" ) ).click();
        Thread.sleep( 1000 );
        driver.findElement( By.xpath( "//a[.='Find Travel Requirements']" ) ).click();

    }


    @Test
    public void roundTripTest() throws InterruptedException {
        /** Round trip**/

        JavascriptExecutor js = ( JavascriptExecutor ) driver;
        // Then scroll down
        Actions actions = new Actions( driver );

        actions.sendKeys( Keys.PAGE_DOWN ).build().perform();
        Thread.sleep( 2000 );


        // Check if the round trip button  works
        Thread.sleep( 2000 );
        WebElement rndTrip = driver.findElement( By.xpath( "//div[@id='smt-covwidget-checkbox-elementrountrip']" ) );
        js.executeScript( "arguments[0].scrollIntoViewIfNeeded()" ,rndTrip );


        Thread.sleep( 3000 );
        rndTrip.click();

        // Verify the click on round trip
        System.out.println( "Is \"Round Trip\" selected? " + rndTrip.isSelected() );
    }


    @Test
    public void oneWayTrip() throws InterruptedException {
        /** One-Way trip**/
        JavascriptExecutor js = ( JavascriptExecutor ) driver;


        // Check if the one way trip button works
        Thread.sleep( 2000 );
        WebElement OneTrip = driver.findElement( By.xpath( "//div[@id='smt-covwidget-checkbox-elementoneway']" ) );
        js.executeScript( "arguments[0].scrollIntoViewIfNeeded()" ,OneTrip );


        Thread.sleep( 3000 );
        OneTrip.click();


        //  Verify the click on One way trip
        System.out.println( "Is \"One-way Trip\" selected? " + OneTrip.isSelected() );
    }


    @Test
    public void domesticFlightsTest() throws InterruptedException {
        /** //Then click on the US Domestic Flight tabs **/
        JavascriptExecutor js = ( JavascriptExecutor ) driver;


        // Check if the one way trip button works
        Thread.sleep( 3000 );
        WebElement DomesticFlights = driver.findElement( By.xpath( "//div//ul//li[@type='button'][@id='smt-tab-domestic']" ) );
        js.executeScript( "arguments[0].scrollIntoViewIfNeeded()" ,DomesticFlights );


        Thread.sleep( 5000 );
        DomesticFlights.click();


        //Verify if the domestic flights tab was clicked
        String actual = DomesticFlights.getText();
        String expected = "U.S. Domestic";


        Assert.assertTrue( actual.equals( expected ) );
        System.out.println("Test was successful");

    }

    @Test
    public void negativeFlightsTest() throws InterruptedException {
        /** This is a Negative Test for Domestic flights Search up**/
        JavascriptExecutor js = ( JavascriptExecutor ) driver;


        // Check if the one way trip button works
        Thread.sleep( 3000 );
        WebElement DomesticFlights = driver.findElement( By.xpath( "//div//ul//li[@type='button'][@id='smt-tab-domestic']" ) );
        js.executeScript( "arguments[0].scrollIntoViewIfNeeded()" ,DomesticFlights );


        Thread.sleep( 5000 );
        DomesticFlights.click();



        try {

            driver.findElement( By.xpath( "//li[@class='smt-combobox-result smt-combobox-result']" ) ).click();

        }catch(NoSuchElementException e){
            //Should screenshot as soon as the "Everywhere" tab is not selected under domestic flights section

            System.out.println("Drop down was unable to be selected. No drop down menus will show.");
            SeleniumUtils.getScreenshotOnFailure();


        }



    }

    @Test
    public void clickNo() throws InterruptedException {
        /** This test will scroll down on the Help Center page and click no, a new window should open with a pop up **/


        JavascriptExecutor js = ( JavascriptExecutor ) driver;

        WebElement noBTN = driver.findElement( By.xpath( "//a[@id='noBtn']" ) );
        js.executeScript( "arguments[0].scrollIntoViewIfNeeded()" ,noBTN );
        Thread.sleep( 1000 );



        noBTN.click();
        Thread.sleep( 3000 );


        SeleniumUtils.getScreenshotOnPass();

    }




    @AfterMethod
    public void closeBrowserWindow() {
        driver.close();

    }


    @AfterTest
    public void TerminateSession() {
        driver.quit();
    }





}

