package deltaTests;


import org.bouncycastle.asn1.dvcs.DVCSObjectIdentifiers;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomepagePage;
import utilities.Driver;
import utilities.PropertyReader;

import java.io.IOException;

public class homepageTests extends TestBase {


    // Navigate URL
    @Test
    public void NavigateUrl() {
        logger.info("Navigate to url");
        driver.get(PropertyReader.getProperties("urlHome"));
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.delta.com/";
        if (currentUrl.equals(expectedUrl)) {
            System.out.println("Test passed: " + currentUrl);
        } else {
            System.out.println("Test failed. The current url was " + currentUrl);
        }


    }


    // Check Click Sing-up button functionality
    @Test
    public void DC_2_TC_SingUp() throws IOException, InterruptedException {
        logger.info("Click SingUp");
        driver.get(PropertyReader.getProperties("urlHome"));
        Thread.sleep(2000);
        HomepagePage SingUpButton = new HomepagePage();
        SingUpButton.SingUp.click();
        Thread.sleep(2000);

        boolean isPresent = Driver.getDriver().findElements(By.xpath("//button[@aria-label='Use Escape or close for modal']")).size() > 0;
        Thread.sleep(2000);
        if(isPresent){
            Driver.getDriver().findElement(By.xpath("//button[@aria-label='Use Escape or close for modal']")).click();
            SingUpButton.SingUp.click();
        }else{
            SingUpButton.SingUp.click();
        }
        logger.info("Enter FirstName");
        SingUpButton.FirstName.sendKeys(PropertyReader.getProperties("FirstName"));
        logger.info("Enter LastName");
        SingUpButton.LastName.sendKeys(PropertyReader.getProperties("LastName"));
        logger.info("Enter DOB");
        Select month = new Select(driver.findElement(By.id("idp-month__selected")));
        month.selectByIndex(1);
        Select date = new Select(driver.findElement(By.id("idp-date__selected")));
        date.selectByIndex(1);
        Select year = new Select(driver.findElement(By.id("idp-year__selected")));
        year.selectByVisibleText("1995");
        Select gender = new Select(driver.findElement(By.id("idp-gender__selected")));
        gender.selectByVisibleText("Female");
        logger.info("Click Next");
        SingUpButton.nextButton.click();
        WebElement actualName = driver.findElement(By.xpath("//span[contains(text(), 'Basic Info') and @class ='standard-enrollment__section-title--number1']"));
        Assert.assertEquals(true, actualName.isDisplayed());
        System.out.println("Test is Passed");

    }


    @Test
    public void DC_9_TC_ContactInfo() {
        logger.info("Click Contact Info");
        driver.get(PropertyReader.getProperties("urlHome"));
        HomepagePage contactInfo = new HomepagePage();
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

    @Test
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
           logger.info("Click Book");
           driver.get(PropertyReader.getProperties("urlHome"));
           HomepagePage  BookMenu = new HomepagePage();

            WebElement searchBox=driver.findElement(By.xpath("//span[contains(text(),'From')]"));
            Actions actions=new Actions(driver);
            actions.keyDown(Keys.SHIFT).
                    sendKeys(searchBox,"DXB").
                    keyUp(Keys.SHIFT).sendKeys(driver.findElement(By.xpath("//span[contains(text(),'To')]")),"TAS").sendKeys(searchBox,Keys.ENTER).build().perform();


    Select RoundTrip= new Select (driver.findElement(By.xpath("//span[@id='selectTripType-val']")));
    RoundTrip.selectByIndex(2);

    Select monthCombo = new Select(driver.findElement(By.xpath("//span[contains(text(),'April')]")));
    monthCombo.selectByVisibleText("April");

    Select yearCombo = new Select(driver.findElement(By.xpath("//span[@class='dl-datepicker-year dl-datepicker-year-0']")));
    yearCombo.selectByVisibleText("2022");

    Select dateCombo = new Select(driver.findElement(By.xpath("//a[@aria-label='30 April 2022, Saturday']")));
    yearCombo.selectByVisibleText("30");



       Select passenger = new Select(driver.findElement(By.xpath("//span[@id='passengers-val']")));
      passenger.selectByIndex(1);

}


    @Test
    public void DC_11_TC_MyTrip() {
        logger.info("Click MyTrip");
        driver.get(PropertyReader.getProperties("urlHome"));
        HomepagePage  BookMenu = new HomepagePage();

    Actions action=new Actions(driver);
    action.keyDown(driver.findElement(By.xpath(" //input[@id='confirmationNo']")), Keys.SHIFT).sendKeys("#9349919762").keyUp(Keys.SHIFT).build().perform();
    BookMenu.FName.sendKeys("FirstName");
    BookMenu.LName.sendKeys("LastName");
    BookMenu.SubmitButton.click();

    }

}


