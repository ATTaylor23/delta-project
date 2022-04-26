package deltaTests;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomepagePage;
import pages.HotelPage;
import utilities.Driver;
import utilities.PropertyReader;
import utilities.SeleniumUtils;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class hotelTests extends TestBase{




    @Test   (priority = 3, groups = "hotel")
    public void verifyShopHotelsButton() throws InterruptedException {
        HotelPage hotelPage = new HotelPage();

        hotelPage.hotelPageSelection();
        hotelPage.switchTabs();

        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), PropertyReader.getProperties("urlHotel"));
        Driver.getDriver().close();
        ArrayList<String> tabs = new ArrayList<>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(tabs.get(0));

    }

    @Test   (priority = 1, groups = "hotel")
    public void verifyDeltaHotelsURL(){

        Driver.getDriver().get(PropertyReader.getProperties("urlHotel"));

        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), "https://www.hotels-delta.com/?wapb3=%7cc.500863%7cl.en_US%7ct.Site%7cs.HomepageShopHotels");

    }

    @Test   (priority = 2, groups = "hotel")
    public void verifyAssociateGlobalVariable(){

        Driver.getDriver().get(PropertyReader.getProperties("urlHotel"));

        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), PropertyReader.getProperties("urlHotel"));

    }

    @Test   (groups = {"hotel"})
    public void verifyChosenLocation() throws InterruptedException {
        Actions actions = new Actions(Driver.getDriver());
        HotelPage hotelPage = new HotelPage();

        String testLocation = "Eugene, Oregon";

        Driver.getDriver().get(PropertyReader.getProperties("urlHotel"));

        actions.doubleClick().build().perform();

        hotelPage.cityBox.sendKeys(testLocation, Keys.ENTER);

        WebElement location = Driver.getDriver().findElement(By.xpath("//input[@class='widget-query-autosuggest clearable autosuggest-enabled active']"));
        String local = location.getAttribute("value");

        Assert.assertTrue(local.contains(testLocation));

    }

    @Test   (groups = {"hotel"})
    public void negativeBookingConfirmation() throws InterruptedException {
        Faker faker = new Faker();
        Random randy = new Random();

        Driver.getDriver().get(PropertyReader.getProperties("urlHotel"));
        Driver.getDriver().findElement(By.xpath("//a[@id='hdr-account']")).click();

        int random_1 = randy.nextInt(100000);

        Driver.getDriver().findElement(By.xpath("//input[@name='confirmationNumber']")).sendKeys("" + random_1);

        Driver.getDriver().findElement(By.xpath("//input[@name='lastName']")).sendKeys("" + faker.artist());

        Driver.getDriver().findElement(By.xpath("//input[@name='emailAddress']")).sendKeys("" + faker.internet().emailAddress());

        Driver.getDriver().findElement(By.xpath("//button[@name='findBooking']")).click();
        Thread.sleep(2000);

        WebElement para = Driver.getDriver().findElement(By.xpath("//p[contains(text(),\"We can’t find your booking. Please check these details match the details shown in your confirmation email.\")] "));
        Assert.assertEquals(para.getText(), "We can’t find your booking. Please check these details match the details shown in your confirmation email.");


    }

    @Test   (groups = {"hotel"})
    public void verifyCalendarLimit() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
        String city = "Miami";

        Driver.getDriver().get(PropertyReader.getProperties("urlHotel"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='widget-query-autosuggest clearable autosuggest-enabled']")));
        Driver.getDriver().findElement(By.xpath("//input[@class='widget-query-autosuggest clearable autosuggest-enabled']")).sendKeys(city, Keys.ENTER);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("widget-query-label-1")));
        Driver.getDriver().findElement(By.id("widget-query-label-1")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//table[@cellspacing='0']//a[contains(text(), '28')])[1]")));
        Driver.getDriver().findElement(By.xpath("(//table[@cellspacing='0']//a[contains(text(), '28')])[1]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='q-localised-check-out']")));
        Driver.getDriver().findElement(By.xpath("//input[@name='q-localised-check-out']")).click();

        Driver.getDriver().findElement(By.xpath("(//button[@class='widget-datepicker-next'])[2]")).click();

        Driver.getDriver().findElement(By.xpath("(//table[@cellspacing='0']//a[contains(text(), '28')])[2]")).click();

        Driver.getDriver().findElement(By.xpath("//button[@type='submit']")).click();

        String message = Driver.getDriver().findElement(By.xpath("//div[@class='form-error']//span")).getText();

        Assert.assertEquals(message, "Sorry, we can’t book a stay that’s longer than 28 days");

    }
    // Verifying a successful path to the personal information consent page

    @Test   (groups = {"hotel"})
    public void personalInfoConsent() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

        Driver.getDriver().get(PropertyReader.getProperties("urlHotel"));

        js.executeScript("window.scrollBy(0,1000)", "");

        Driver.getDriver().findElement(By.xpath("//h2[@aria-controls='widget-toggle-i0-e1']")).click();
        Driver.getDriver().findElement(By.id("ftr-dnsmpi")).click();

        ArrayList<String> tabs = new ArrayList<>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(tabs.get(1));

        Driver.getDriver().findElement(By.xpath("//label[@for='do-not-sell-checkbox']")).click();

        String message = Driver.getDriver().findElement(By.xpath("//h1")).getText();

        Assert.assertEquals(message, "DO NOT SELL MY PERSONAL INFORMATION");
    }
    // Choose "DO NOT" option and validate its success

    @Test   (groups = {"hotel"})
    public void DNSMPInfo() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

        Driver.getDriver().get(PropertyReader.getProperties("urlHotel"));

        js.executeScript("window.scrollBy(0,1000)", "");

        Driver.getDriver().findElement(By.xpath("//h2[@aria-controls='widget-toggle-i0-e1']")).click();
        Driver.getDriver().findElement(By.id("ftr-dnsmpi")).click();

        ArrayList<String> tabs = new ArrayList<>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(tabs.get(1));

        Driver.getDriver().findElement(By.xpath("//label[@for='do-not-sell-checkbox']")).click();

        WebElement noSell = Driver.getDriver().findElement(By.xpath("//span[contains(text(), \"Success! You have been opted out.\")]"));

        Assert.assertEquals(noSell.getAttribute("textContent"), "Success! You have been opted out.");

    }
    // Modify room details on the hotel page and select "Search", validating a new page appears

    @Test   (groups = {"hotel"})
    public void moreRoomOptions() throws InterruptedException {
        HotelPage hotelPage = new HotelPage();

        Driver.getDriver().get(PropertyReader.getProperties("urlHotel"));

        Driver.getDriver().findElement(By.id("qf-0q-compact-occupancy")).click();
        Driver.getDriver().findElement(By.xpath("//select[@id='qf-0q-compact-occupancy']//option[@data-more-options='true']")).click();

        Driver.getDriver().findElement(By.id("qf-0q-rooms")).click();
        Driver.getDriver().findElement(By.xpath("//select[@id='qf-0q-rooms']//option[@value='5']")).click();

        Driver.getDriver().findElement(By.id("qf-0q-room-0-adults")).click();
        Driver.getDriver().findElement(By.xpath("//select[@id='qf-0q-room-0-adults']//option[@value='17']")).click();

        Driver.getDriver().findElement(By.id("qf-0q-room-0-children")).click();
        Driver.getDriver().findElement(By.xpath("//select[@id='qf-0q-room-0-children']//option[@value='3']")).click();

        new Select(Driver.getDriver().findElement(By.id("qf-0q-room-0-child-0-age"))).selectByIndex(3);
        new Select(Driver.getDriver().findElement(By.id("qf-0q-room-0-child-1-age"))).selectByIndex(7);
        new Select(Driver.getDriver().findElement(By.id("qf-0q-room-0-child-2-age"))).selectByIndex(4);

        hotelPage.cityBox.clear();
        hotelPage.cityBox.sendKeys("Washington");

        Driver.getDriver().findElement(By.xpath("//button[@class='cta cta-strong']")).click();

        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), "https://www.hotels-delta.com/?wapb3=%7cc.500863%7cl.en_US%7ct.Site%7cs.HomepageShopHotels");


    }
    //Find the hotel page, switch tabs and remove the COVID-19 alert

    @Test   (groups = {"hotel"})
    public void covidAlertRemoval() throws InterruptedException {
        HotelPage hotelPage = new HotelPage();

        hotelPage.hotelPageSelection();
        hotelPage.switchTabs();

        hotelPage.covidAlert();

        Assert.assertFalse(Driver.getDriver().getPageSource().contains("//a[@class='icon close-icon po-a cursor c-hds-arch-3']"));

    }
    //Open the hotels page, switch tabs, change currency, and validate its presence

    @Test   (groups = {"hotel"})
    public void switchingCurrency() throws InterruptedException {
        HotelPage hotelPage = new HotelPage();

        hotelPage.hotelPageSelection();
        hotelPage.switchTabs();
        hotelPage.covidAlert();

        Driver.getDriver().findElement(By.id("header-toggle-currency")).click();
        Driver.getDriver().findElement(By.xpath("//a[@data-currency='UAH']")).click();

        Assert.assertTrue(Driver.getDriver().getPageSource().contains("UAH"));

    }
    // Validate a secondary path from the homepage to the hotel page

    @Test
    public void validate2ndPath(){
        HotelPage hotelPage = new HotelPage();

        hotelPage.secondaryPath();

        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), PropertyReader.getProperties("urlHotel"));
    }
    // Validate secondary path through half-screen

    @Test
    public void validateThruHalfScreen(){
        HotelPage hotelPage = new HotelPage();
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

        Driver.getDriver().get(PropertyReader.getProperties("urlHome"));
        Driver.getDriver().manage().window().setSize(new Dimension(1000, 800));

        Driver.getDriver().findElement(By.xpath("//a[@class='hamburger-icon d-lg-none']")).click();
        Driver.getDriver().findElement(By.xpath("//a[@class='nav-link icon-Dropdown-caret static-link d-block d-lg-none static-link-secondarynav']")).click();

        js.executeScript("arguments[0].click();", hotelPage.hotelButton2);

        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), PropertyReader.getProperties("urlHotel"));

    }
    //Hotel booking found through Apartments image link

    @Test (groups = {"destination"})
    public void apartments(){
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        HotelPage hotelPage = new HotelPage();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));

        String city = "Manhattan";

        Driver.getDriver().get(PropertyReader.getProperties("urlHotel"));

        js.executeScript("window.scrollBy(0,1000)", "");

        Driver.getDriver().findElement(By.xpath("(//div[@class='overlay cursor po-a post-none posl-none w-full h-full'])[1]")).click();
        hotelPage.idk.click();
        hotelPage.overlayCityBox.clear();
        hotelPage.overlayCityBox.sendKeys(city, Keys.ENTER);

        wait.until(ExpectedConditions.urlContains(Driver.getDriver().getCurrentUrl()));
        String url = Driver.getDriver().getCurrentUrl();

        Assert.assertTrue(url.contains(city));

    }
    //Hotel booking found through Country Retreats image link

    @Test (groups = {"destination"})
    public void countryRetreats(){
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        HotelPage hotelPage = new HotelPage();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));

        String city = "Nashville";

        Driver.getDriver().get(PropertyReader.getProperties("urlHotel"));

        js.executeScript("window.scrollBy(0,1000)", "");

        Driver.getDriver().findElement(By.xpath("(//div[@class='overlay cursor po-a post-none posl-none w-full h-full'])[2]")).click();
        hotelPage.idk.click();
        hotelPage.overlayCityBox.clear();
        hotelPage.overlayCityBox.sendKeys(city, Keys.ENTER);

        wait.until(ExpectedConditions.urlContains(Driver.getDriver().getCurrentUrl()));
        String url = Driver.getDriver().getCurrentUrl();

        System.out.println(city);
        System.out.println(url);

        Assert.assertTrue(url.contains(city));

    }
    //Hotel booking found through Resorts image link

    @Test (groups = {"destination"})
    public void resorts(){
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        HotelPage hotelPage = new HotelPage();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));

        String city = "Cancun";

        Driver.getDriver().get(PropertyReader.getProperties("urlHotel"));

        js.executeScript("window.scrollBy(0,1000)", "");

        Driver.getDriver().findElement(By.xpath("(//div[@class='overlay cursor po-a post-none posl-none w-full h-full'])[3]")).click();
        hotelPage.idk.click();
        hotelPage.overlayCityBox.clear();
        hotelPage.overlayCityBox.sendKeys(city, Keys.ENTER);

        String url = Driver.getDriver().getCurrentUrl();

        Assert.assertTrue(url.contains(city));

    }
    //Hotel booking found through Hotels image link

    @Test (groups = {"destination"})
    public void hotels(){
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        HotelPage hotelPage = new HotelPage();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));

        String city = "Martinsburg";

        Driver.getDriver().get(PropertyReader.getProperties("urlHotel"));

        js.executeScript("window.scrollBy(0,1000)", "");

        Driver.getDriver().findElement(By.xpath("(//div[@class='overlay cursor po-a post-none posl-none w-full h-full'])[4]")).click();
        hotelPage.idk.click();
        hotelPage.overlayCityBox.clear();
        hotelPage.overlayCityBox.sendKeys(city, Keys.ENTER);

        wait.until(ExpectedConditions.urlContains(Driver.getDriver().getCurrentUrl()));
        String url = Driver.getDriver().getCurrentUrl();

        Assert.assertTrue(url.contains(city));


    }
    //Hotel booking found through Vacation Homes image link

    @Test (groups = {"destination"})
    public void vacationHomes(){
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        HotelPage hotelPage = new HotelPage();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));

        String city = "Kingston";

        Driver.getDriver().get(PropertyReader.getProperties("urlHotel"));

        js.executeScript("window.scrollBy(0,1000)", "");

        Driver.getDriver().findElement(By.xpath("(//div[@class='overlay cursor po-a post-none posl-none w-full h-full'])[5]")).click();
        hotelPage.idk.click();
        hotelPage.overlayCityBox.clear();
        hotelPage.overlayCityBox.sendKeys(city, Keys.ENTER);

        wait.until(ExpectedConditions.urlContains(Driver.getDriver().getCurrentUrl()));
        String url = Driver.getDriver().getCurrentUrl();

        Assert.assertTrue(url.contains(city));

    }
    //Hotel booking found through City Homes image link

    @Test (groups = {"destination"})
    public void cityHomes(){
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        HotelPage hotelPage = new HotelPage();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));

        String city = "Manhattan";

        Driver.getDriver().get(PropertyReader.getProperties("urlHotel"));

        js.executeScript("window.scrollBy(0,1000)", "");

        Driver.getDriver().findElement(By.xpath("(//div[@class='overlay cursor po-a post-none posl-none w-full h-full'])[6]")).click();
        hotelPage.idk.click();
        hotelPage.overlayCityBox.clear();
        hotelPage.overlayCityBox.sendKeys(city, Keys.ENTER);

        wait.until(ExpectedConditions.urlContains(Driver.getDriver().getCurrentUrl()));
        String url = Driver.getDriver().getCurrentUrl();

        Assert.assertTrue(url.contains(city));

    }

}
