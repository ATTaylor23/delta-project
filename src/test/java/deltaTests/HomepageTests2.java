package deltaTests;

import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomepagePage;
import utilities.Driver;
import utilities.PropertyReader;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class HomepageTests2 extends TestBase {
   // ChromeOptions chromeOptions = new ChromeOptions();
    //chromeOptions.AddAdditionalCapability("useAutomationExtension", false);
    //chromeOptions.AddExcludedArgument("enable-automation");
    //  Driver = new ChromeDriver(chromeOptions);

    Faker faker = new Faker();
    Actions calendar = new Actions(Driver.getDriver());
    HomepagePage flightStatusButton = new HomepagePage();



    // Test Case:1
    @Test (alwaysRun = true)
    public void AdvanceSearchButton() throws InterruptedException {
        // Navigate URL
        Driver.getDriver().get(PropertyReader.getProperties("urlHome"));
       Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Delete all cookies, popUps, alertMessages
        Driver.getDriver().manage().deleteAllCookies();
        WebElement topAlert = Driver.getDriver().findElement(By.xpath("//div[@class='close-advisory col-1 pr-0 pl-0']//button"));
        calendar.click(topAlert).build().perform();
        WebElement alertPop =  Driver.getDriver().findElement(By.xpath("//p[.='Join SkyMiles® for free']//following-sibling::button[@aria-label='Use Escape or close for modal']"));
        new WebDriverWait( Driver.getDriver(), Duration.ofSeconds(6)).until(ExpectedConditions.visibilityOf(alertPop));
        alertPop.click();


        // Mouse or KeyBoard actions
        Thread.sleep(3000);
        flightStatusButton.FlightStatusButton.click();
        calendar.moveToElement(flightStatusButton.CalendarButton)
                .click()
                .moveToElement(flightStatusButton.MonthButton)
                .click()
                .keyDown(flightStatusButton.RandomNumber,Keys.SHIFT).sendKeys(flightStatusButton.RandomNumber, faker.number().digits(5))
                .keyUp(flightStatusButton.RandomNumber,Keys.SHIFT)
                .clickAndHold(flightStatusButton.LocationFrom).sendKeys("Dubai")
                .keyUp(flightStatusButton.LocationFrom, Keys.SHIFT)
                .clickAndHold(flightStatusButton.LocationToButton)
                .moveToElement(flightStatusButton.Todestination).sendKeys("Uzbekistan")
                .keyUp(Keys.SHIFT).build().perform();
        Thread.sleep(4000);

        // Using  JavascriptExecutor to click action
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", flightStatusButton.SubmitButton);


        // Verify text
//      Assert.assertEquals(MessageVerification.getText(), "Flight Status & Notifications");
        Assert.assertTrue(driver.getPageSource().contains("Flight Status & Notifications"));

    }


    // Test Case:2
    @Test (alwaysRun = true)
    public void AdvanceSearchDropDown() throws InterruptedException {
        Driver.getDriver().get(PropertyReader.getProperties("urlHome"));
        flightStatusButton.advanceSearch.click();
         calendar.click(Driver.getDriver().findElement(By.xpath("//span[@id='faresFor-val']")));
         try {
             List<WebElement> actualName = driver.findElements(By.xpath("//span//ul[@id='faresFor-desc']//li"));

             SoftAssert softAssert = new SoftAssert();
             Thread.sleep(5000);
             List<String> expectedName = new ArrayList<>();
             expectedName.add(0, "Basic Economy");
             expectedName.add(1, "Main Cabin");
             expectedName.add(2, "Delta Comfort");
             expectedName.add(3, "First Class");
             expectedName.add(4, "Delta Premium Select");
             expectedName.add(5, "Delta One");
             for (int i = 0; i < actualName.size(); i++) {
                 softAssert.assertEquals(actualName.get(i).getText(), expectedName.get(i));
             }
             softAssert.assertAll();
         }catch (AssertionError e){
             e.printStackTrace();
         }
            //  Select BasicEconomyDropDown = new Select(selectOptions); // Select  did not work
        }


    @Test
    public void SearchOptions() {
        Driver.getDriver().get(PropertyReader.getProperties("urlHome"));
        List<WebElement> options = Driver.getDriver().findElements(By.xpath("//div[@class='row booking-widget_search-checkbox-section ng-untouched ng-pristine ng-invalid']//label"));
        Random rand = new Random();
        int list = rand.nextInt(options.size());
        options.get(list).click();

    }


    @Test(dataProvider = "enterData")
    public void MyTrip(String firstName,String lastName) {

        logger.info("Click MyTrip");
        Driver.getDriver().get(PropertyReader.getProperties("urlHome"));
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement topAlert = Driver.getDriver().findElement(By.xpath("//div[@class='close-advisory col-1 pr-0 pl-0']//button"));
        calendar.click(topAlert).build().perform();

        try {
            calendar.doubleClick(flightStatusButton.MyTripButton).build().perform();
            calendar.moveToElement( flightStatusButton.number)
                    .click()
                    .sendKeys( flightStatusButton.number,faker.number().digits(10))
                    .keyUp(Keys.SHIFT).build().perform();
            flightStatusButton.FName.sendKeys(firstName, Keys.ENTER);
            flightStatusButton.LName.sendKeys(lastName, Keys.ENTER);
            calendar.click(flightStatusButton.SubmitButton).build().perform();
        } catch (Exception e) {
            System.out.println("The MyTrip testcase did not pass");
        }


    }


@DataProvider (parallel = true)
    public Object[][] enterData(){
      Object[][]  fillBlank={ {faker.name().firstName(), faker.name().lastName() }};
      return fillBlank;
}


    @Test
    public void tearDownMethod(){

        Driver.quitDriver();
    }

}


