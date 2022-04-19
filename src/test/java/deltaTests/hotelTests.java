package deltaTests;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HotelPage;
import utilities.Driver;
import utilities.PropertyReader;
import utilities.SeleniumUtils;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class hotelTests extends TestBase{



    @Test   (priority = 3, groups = "hotel")
    public void verifyShopHotelsButton() throws InterruptedException {
        HotelPage hotelPage = new HotelPage();


        driver.get(PropertyReader.getProperties("urlHome"));
        //Driver.getDriver().get(PropertyReader.getProperties("urlHotel"));


        Thread.sleep(4000);

        boolean isPresent = Driver.getDriver().findElements(By.xpath("//button[@class='advisory-close-icon float-right circle-outline icon-moreoptionsclose']")).size() > 0;

        if(isPresent){
            Driver.getDriver().findElement(By.xpath("//button[@class='advisory-close-icon float-right circle-outline icon-moreoptionsclose']")).click();
            hotelPage.shopHotelsBtn.click();
        }else{
            hotelPage.shopHotelsBtn.click();
        }
        String windowHandle = driver.getWindowHandle();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Thread.sleep(3500);

        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), PropertyReader.getProperties("urlHotel"));
        driver.close();
        driver.switchTo().window(tabs.get(0));

    }

    @Test   (priority = 1, groups = "hotel")
    public void verifyDeltaHotelsURL(){

        driver.get(PropertyReader.getProperties("urlHotel"));
        //Driver.getDriver().get(PropertyReader.getProperties("urlHotel"));

        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), "https://www.hotels-delta.com/?wapb3=%7cc.500863%7cl.en_US%7ct.Site%7cs.HomepageShopHotels");

    }

    @Test   (priority = 2, groups = "hotel")
    public void verifyAssociateGlobalVariable(){

        driver.get(PropertyReader.getProperties("urlHotel"));
        //Driver.getDriver().get(PropertyReader.getProperties("urlHotel"));

        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), PropertyReader.getProperties("urlHotel"));

    }

    @Test   (groups = {"hotel"})
    public void verifyChosenLocation() throws InterruptedException {
        Actions actions = new Actions(driver);
        HotelPage hotelPage = new HotelPage();

        String testLocation = "Eugene, Oregon";

        driver.get(PropertyReader.getProperties("urlHotel"));

        actions.doubleClick().build().perform();

        hotelPage.cityBox.sendKeys(testLocation, Keys.ENTER);
        Thread.sleep(3000);

        WebElement location = driver.findElement(By.xpath("//input[@id='q-destination']"));
        String local = location.getAttribute("value");

        Assert.assertTrue(local.contains(testLocation));

    }

    @Test   (groups = {"hotel"})
    public void negativeBookingConfirmation() throws InterruptedException {
        Faker faker = new Faker();
        Random randy = new Random();

        driver.get(PropertyReader.getProperties("urlHotel"));
        driver.findElement(By.xpath("//a[@id='hdr-account']")).click();

        int random_1 = randy.nextInt(100000);

        driver.findElement(By.xpath("//input[@name='confirmationNumber']")).sendKeys("" + random_1);

        driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys("" + faker.artist());

        driver.findElement(By.xpath("//input[@name='emailAddress']")).sendKeys("" + faker.internet().emailAddress());

        driver.findElement(By.xpath("//button[@name='findBooking']")).click();
        Thread.sleep(2000);

        WebElement para = driver.findElement(By.xpath("//p[contains(text(),\"We can’t find your booking. Please check these details match the details shown in your confirmation email.\")] "));
        Assert.assertEquals(para.getText(), "We can’t find your booking. Please check these details match the details shown in your confirmation email.");


    }

    @Test   (groups = {"hotel"})
    public void verifyCalendarLimit() throws InterruptedException {
        String city = "Miami";

        driver.get(PropertyReader.getProperties("urlHotel"));

        driver.findElement(By.xpath("//input[@class='widget-query-autosuggest clearable autosuggest-enabled']")).sendKeys(city, Keys.ENTER);
        Thread.sleep(3500);


        driver.findElement(By.id("widget-query-label-1")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("(//table[@cellspacing='0']//a[contains(text(), '28')])[1]")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//input[@name='q-localised-check-out']")).click();

        driver.findElement(By.xpath("(//button[@class='widget-datepicker-next'])[2]")).click();

        driver.findElement(By.xpath("(//table[@cellspacing='0']//a[contains(text(), '28')])[2]")).click();

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        String message = driver.findElement(By.xpath("//div[@class='form-error']//span")).getText();

        Assert.assertEquals(message, "Sorry, we can’t book a stay that’s longer than 28 days");

    }

}
