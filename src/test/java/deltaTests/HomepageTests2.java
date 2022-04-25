package deltaTests;

import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomepagePage;
import utilities.Driver;
import utilities.PropertyReader;

import java.util.List;
import java.util.Random;


public class HomepageTests2 extends TestBase {

    WebElement MessageVerification;
    HomepagePage flightStatusButton = new HomepagePage();
    Faker faker = new Faker();
    Actions calendar = new Actions(driver);


    @Test
    public void AdvanceSearchButton() {
        driver.get(PropertyReader.getProperties("urlHome"));
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
    public void AdvanceSearchDropDown() {
        driver.get(PropertyReader.getProperties("urlHome"));
        flightStatusButton.advanceSearch.click();
        WebElement button = driver.findElement(By.xpath("//span[contains(text(),'Basic Economy')]"));
        button.click();
        WebElement selectOptions = driver.findElement(By.xpath("//span//ul[@id='faresFor-desc']//li"));
        Select BasicEconomyDropDown = new Select(selectOptions);
        List<WebElement> options = BasicEconomyDropDown.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println(options.get(i).getText());
        }
    }


    @Test
    public void SearchOptions() {
        driver.get(PropertyReader.getProperties("urlHome"));
        List<WebElement> options = driver.findElements(By.xpath("//div[@class='row booking-widget_search-checkbox-section ng-untouched ng-pristine ng-invalid']//label"));
        Random rand = new Random();
        int list = rand.nextInt(options.size());
        options.get(list).click();

    }


    @Test(dataProvider = "enterData")
    public void SingUp(String firstName,String lastName, String cityName) {
        driver.get(PropertyReader.getProperties("urlHome"));
        WebElement signUp = Driver.getDriver().findElement(By.xpath("//a[@class='sign-up btn btn-link']"));
        calendar.doubleClick(signUp).build().perform();
        driver.findElement(flightStatusButton.FirstName.sendKeys(firstName,Keys.ENTER);
        driver.findElement(flightStatusButton.LastName.sendKeys(lastName,Keys.ENTER);
        driver.findElement(flightStatusButton.address.sendKeys(cityName,Keys.ENTER);

    }


@DataProvider
    public Object[][] enterData(){
      Object[][]  fillBlank={{""+faker.name().firstName()},{""+faker.name().lastName()},{""+faker.address().cityName()}};
      return fillBlank;
}


}








