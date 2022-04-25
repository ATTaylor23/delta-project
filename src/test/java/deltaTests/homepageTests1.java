package deltaTests;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.bouncycastle.asn1.dvcs.DVCSObjectIdentifiers;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomepagePage;
import utilities.Driver;
import utilities.PropertyReader;
import utilities.SeleniumUtils;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class homepageTests1 extends TestBase {



    // Navigate URL
    @Test (priority = 1)
    public void NavigateUrl() {
        logger.info("Navigate to url");
        driver.get(PropertyReader.getProperties("urlHome"));
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.delta.com/";
        if (currentUrl.equals(expectedUrl)) {
            System.out.println("Current URL is: " + currentUrl);
        } else {
            System.out.println("Test failed. The current url was " + currentUrl);
        }



    }


    @Test (dependsOnMethods = { "NavigateUrl"})
    public void verifyHomepageTitle() {
        logger.info("Click SingUp");
        driver.get("https://www.delta.com/");
        Actions actions = new Actions(driver);
        WebElement signUp = Driver.getDriver().findElement(By.xpath("//a[@class='sign-up btn btn-link']"));
        actions.doubleClick(signUp).build().perform();
        String name= "Basic Info";
        driver.findElement(By.xpath("//legend[@class='standard-enrollment__section-title--active']//span[contains(text(), ' Basic Info')]"));
        Assert.assertTrue(driver.getTitle().contains(name));

    }



    // Check Click Sing-up button functionality
    @Test(alwaysRun = true)
    public void DC_2_TC_SingUp() throws IOException, InterruptedException {

        logger.info("Click SingUp");
        driver.get(PropertyReader.getProperties("urlHome"));
        Thread.sleep(2000);
        HomepagePage SingUpButton = new HomepagePage();

        Actions actions = new Actions(driver);
        WebElement topAlert = driver.findElement(By.xpath("//div[@class='close-advisory col-1 pr-0 pl-0']//button"));
        actions.click(topAlert).build().perform();
        WebElement alertPop = driver.findElement(By.xpath("//p[.='Join SkyMiles® for free']//following-sibling::button[@aria-label='Use Escape or close for modal']"));
        new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.visibilityOf(alertPop));
        alertPop.click();

        WebElement signUp = Driver.getDriver().findElement(By.xpath("//a[@class='sign-up btn btn-link']"));
        actions.doubleClick(signUp).build().perform();
        Thread.sleep(2000);
            WebElement dob = driver.findElement(By.xpath("//div[@class='idp-dropdown']//child::div[2]"));
             JavascriptExecutor js = ( JavascriptExecutor ) driver;
            ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,500)");
            dob.click();


        logger.info("Enter FirstName");
        SingUpButton.FirstName.sendKeys(PropertyReader.getProperties("FirstName"));
        Thread.sleep(1000);
        logger.info("Enter LastName");
        SingUpButton.LastName.sendKeys(PropertyReader.getProperties("LastName"));


       WebElement month=driver.findElement(By.xpath("//div[@class='idp-dropdown'][1]//li[@id='monthoption-1']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",month);
        Select month1= new Select(month);
        month1.selectByVisibleText("Feb");

        Thread.sleep(2000);
            try {
			if (month.isEnabled() && month.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", month);
				System.out.println("Clicking on element with using java script click");
			} else {
				System.out.println("Unable to click on element");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document "+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element was not found in DOM "+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to click on element "+ e.getStackTrace());
		}}








//    Select date = new Select(driver.findElement(By.id("idp-date__selected")));
//    date.selectByIndex(1);
//
//
//
//    Select year = new Select(driver.findElement(By.id("idp-year__selected")));
//        year.selectByVisibleText("1995");
//    Thread.sleep(2000);
//        Select gender = new Select(driver.findElement(By.id("idp-gender__selected")));
//        gender.selectByVisibleText("Female");
//        logger.info("Click Next");
//        SingUpButton.nextButton.click();
//}catch (Exception e) {
//    System.out.println("Cannot click on \"MM\" element");
//    SeleniumUtils.getScreenshotOnFailure();
//    System.out.println("ScreenShot was Taken");
//}


//        WebElement actualName = driver.findElement(By.xpath("//span[contains(text(), 'Basic Info') and @class ='standard-enrollment__section-title--number1']"));
//        Assert.assertEquals(true, actualName.isDisplayed());
//        System.out.println("Test is Passed");








    @Test( priority = 2,groups = "smoke")
    public void DC_9_TC_ContactInfo() {
        logger.info("Click Contact Info");
        driver.get(PropertyReader.getProperties("urlHome"));
        HomepagePage contactInfo= new HomepagePage();
         SeleniumUtils.waitFor(3);
        contactInfo.contactInfoButton.click();
        WebElement actualName = driver.findElement(By.xpath("span[contains(text(),'Contact Info') and @class='standard-enrollment__section-title--number2']"));
        Assert.assertEquals(true, actualName.isDisplayed());
        System.out.println("Test is Passed");
        Select country = new Select(driver.findElement(By.xpath("//div[@id='idp-addresscountry__selected']")));
        country.selectByIndex(1);
        contactInfo.address.sendKeys("1234 N 56 Street");
        contactInfo.city.sendKeys("Kansas");
        Select state = new Select(driver.findElement(By.xpath("//div[@id='idp-countrySubdivisionCode__selected']")));
        state.selectByVisibleText("Kansas");
        contactInfo.postalCode.sendKeys("66022");
        contactInfo.phoneNumber.sendKeys("(816)123-4567");
        contactInfo.emailAddress.sendKeys("aziza@gmail.com");
        driver.findElement(By.xpath("//input[@aria-label='Confirm Email Address']")).sendKeys("aziza@gmail.com");
        contactInfo.nextContactButton.click();


    }


    // Enter username and password, security question in Login Info page

    @Test ( priority = 3,groups = "smoke")
    public void DC_10_TC_LoginInfo() {
        logger.info("Click Login Info");
        driver.get(PropertyReader.getProperties("urlHome"));
        HomepagePage   loginInfoPage = new HomepagePage();
        loginInfoPage.userName.sendKeys("Azizam09!");
        loginInfoPage.passWord.sendKeys("Aziza09!");
        loginInfoPage.ConfirmPassword.sendKeys("Aziza09!");
        Select question1 = new Select(driver.findElement(By.xpath("//div[@id='idp-securityQs1__selected']")));
        question1.selectByIndex(1);
        loginInfoPage.Answer1.sendKeys("Sizov");
        Select question2 = new Select(driver.findElement(By.xpath("//span[@aria-label='Select Question']/parent::div[@id='idp-securityQs1__selected']")));
        question2.selectByIndex(1);
        loginInfoPage.Answer2.sendKeys("Tuzik");
        loginInfoPage.LoginButton.click();


    }




    // Check Book menu functionality on Header Bar
       @Test
    public void DC_3_TC_Book() {

           try {
               logger.info("Click BookButton");
               driver.get(PropertyReader.getProperties("urlHome"));
               HomepagePage BookButton = new HomepagePage();
               Thread.sleep(3000);

               Actions actions = new Actions(driver);
               WebElement topAlert = driver.findElement(By.xpath("//div[@class='close-advisory col-1 pr-0 pl-0']//button"));
               actions.click(topAlert).build().perform();


               WebElement alertPop = driver.findElement(By.xpath("//p[.='Join SkyMiles® for free']//following-sibling::button[@aria-label='Use Escape or close for modal']"));
               new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.visibilityOf(alertPop));
               alertPop.click();

               WebElement searchBox=driver.findElement(By.xpath("//a[@id='fromAirportName']//span[contains(text(),'From')]"));
                searchBox.click();
                actions.keyDown(Keys.SHIFT).
                    sendKeys(searchBox,"DXB").keyUp(Keys.SHIFT).
                    keyDown(Keys.SHIFT).sendKeys(driver.findElement(By.xpath("//a[@id='toAirportName']//span[contains(text(),'To')]")),"TAS").build().perform();

    Thread.sleep(2000);
    Select RoundTrip = new Select(driver.findElement(By.xpath("//span[@id='selectTripType-val']")));
    RoundTrip.selectByIndex(2);
    Thread.sleep(1000);
    Select monthCombo = new Select(driver.findElement(By.xpath("//span[contains(text(),'April')]")));
    monthCombo.selectByVisibleText("April");

    Select yearCombo = new Select(driver.findElement(By.xpath("//span[@class='dl-datepicker-year dl-datepicker-year-0']")));
    yearCombo.selectByVisibleText("2022");
    Thread.sleep(1000);
    Select dateCombo = new Select(driver.findElement(By.xpath("//a[@aria-label='30 April 2022, Saturday']")));
    yearCombo.selectByVisibleText("30");

    Select passenger = new Select(driver.findElement(By.xpath("//span[@id='passengers-val']")));
    passenger.selectByIndex(1);
}catch (Exception a){
    System.out.println("Test did not pass");
}

}


    @Test
    public void DC_11_TC_MyTrip() throws InterruptedException {
        try {
            logger.info("Click MyTrip");
            driver.get(PropertyReader.getProperties("urlHome"));
            HomepagePage BookMenu = new HomepagePage();
            Thread.sleep(3000);

            Actions actions = new Actions(driver);
            WebElement topAlert = driver.findElement(By.xpath("//div[@class='close-advisory col-1 pr-0 pl-0']//button"));
            actions.click(topAlert).build().perform();


            WebElement alertPop = driver.findElement(By.xpath("//p[.='Join SkyMiles® for free']//following-sibling::button[@aria-label='Use Escape or close for modal']"));
            new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.visibilityOf(alertPop));
            alertPop.click();

            driver.manage().deleteAllCookies();
            Actions action = new Actions(driver);
            action.keyDown(driver.findElement(By.xpath("//ul[@id='navPrimary']//a[@id='headPrimary3']")), Keys.SHIFT).sendKeys("#9349919762").keyUp(Keys.SHIFT).build().perform();
            BookMenu.FName.sendKeys("FirstName");
            BookMenu.LName.sendKeys("LastName");
            BookMenu.SubmitButton.click();
        }catch (Exception b){
            System.out.println("The MyTrip testcase did not pass");
        }
    }





   @Test
    public void tearDownMethod(){
        Driver.quitDriver();
    }

}


