package deltaTests;

import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomepagePage;
import utilities.Driver;
import utilities.PropertyReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class HomepageTests2 extends TestBase {

    WebElement MessageVerification;

    Faker faker = new Faker();
    Actions calendar = new Actions(Driver.getDriver());


    @Test
    public void AdvanceSearchButton() {
        Driver.getDriver().get(PropertyReader.getProperties("urlHome"));
        HomepagePage flightStatusButton = new HomepagePage();
        flightStatusButton.FlightStatusButton.click();
        calendar.clickAndHold(flightStatusButton.CalendarButton)
                .click(flightStatusButton.MonthButton)
                .click(flightStatusButton.RandomNumber).sendKeys("" + faker.number())
                .clickAndHold(flightStatusButton.LocationFrom).sendKeys("Dubai")
                .keyUp(flightStatusButton.LocationFrom, Keys.SHIFT)
                .clickAndHold(flightStatusButton.LocationToButton)
                .moveToElement(flightStatusButton.Todestination).sendKeys("Uzbekistan")
                .keyUp(Keys.SHIFT);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", flightStatusButton.SubmitButton);
        Assert.assertEquals(MessageVerification.getText(), "Flight Status & Notifications");
    }


    @Test
    public void AdvanceSearchDropDown() throws InterruptedException {
        Driver.getDriver().get(PropertyReader.getProperties("urlHome"));
        HomepagePage flightStatusButton = new HomepagePage();
        flightStatusButton.advanceSearch.click();
       calendar.click(Driver.getDriver().findElement(By.xpath("//span[@id='faresFor-val']")));


           List<WebElement>actualName=driver.findElements(By.xpath("//span//ul[@id='faresFor-desc']//li"));

            SoftAssert softAssert= new SoftAssert();
            Thread.sleep(5000);
            List<String>expectedName = new ArrayList<>();
            expectedName.add(0, "Basic Economy");
            expectedName.add(1, "Main Cabin");
            expectedName.add(2, "Delta Comfort");
            expectedName.add(3, "First Class");
            expectedName.add(4, "Delta Premium Select");
            expectedName.add(5, "Delta One");
             for (int i=0; i<actualName.size();i++){
            softAssert.assertEquals(actualName.get(i).getText(), expectedName.get(i));
        }
            softAssert.assertAll();

            //  Select BasicEconomyDropDown = new Select(selectOptions); //  it did not work due to there was not select tag
        }


    //span//ul[@id='faresFor-desc']/li
    @Test
    public void SearchOptions() {
        Driver.getDriver().get(PropertyReader.getProperties("urlHome"));
        List<WebElement> options = Driver.getDriver().findElements(By.xpath("//div[@class='row booking-widget_search-checkbox-section ng-untouched ng-pristine ng-invalid']//label"));
        Random rand = new Random();
        int list = rand.nextInt(options.size());
        options.get(list).click();

    }


    @Test(dataProvider = "enterData")
    public void SingUp(String firstName,String lastName, String cityName) {
     Driver.getDriver().get(PropertyReader.getProperties("urlHome"));
        HomepagePage elements = new HomepagePage();
        WebElement signUp = Driver.getDriver().findElement(By.xpath("//a[@class='sign-up btn btn-link']"));
        calendar.doubleClick(signUp).build().perform();
//        driver.findElement(elements.FirstName.sendKeys(firstName);
//       WebElement element1= Driver.getDriver().findElements(By.xpath(flightStatusButton .LastName.sendKeys(lastName,Keys.ENTER)));
//
//        driver.findElement(flightStatusButton.address.sendKeys(cityName,Keys.ENTER);

    }


@DataProvider (parallel = true)
    public Object[][] enterData(){
      Object[][]  fillBlank={ {faker.name().firstName(), faker.name().lastName(),faker.address().cityName() },
        {faker.name().firstName(), faker.name().lastName(),faker.address().cityName() },
        {faker.name().firstName(), faker.name().lastName(),faker.address().cityName() },
        {faker.name().firstName(), faker.name().lastName(),faker.address().cityName() }};
      return fillBlank;
}


}







