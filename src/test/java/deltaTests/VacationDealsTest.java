package deltaTests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.VacationDealsPage;
import pages.VacationDealsSearchPage;
import utilities.PropertyReader;
import utilities.UtilMethodsAndrey;

import java.time.Duration;
import java.util.Set;

public class VacationDealsTest extends TestBase{


// simple test to check if SkyMiles link on the page is working
    @Test(groups = "Andrey")
    public void DeltaMilesRegistrationLink(){
        driver.get(PropertyReader.getProperties("urlVacation"));
        VacationDealsPage vacationDealsPage = new VacationDealsPage();
        vacationDealsPage.JoinMilesClub.click();
        Assert.assertEquals(driver.getTitle(), "Delta - Join SkyMiles");
    }

// testing if coupon SMEARN75K can be selected and applied (entered) at the bottom of the next page
// SkyMiles number is omitted
    @Test (groups = "Andrey")
    public void ShopThisDealCouponSMEARN75KWithoutSkyMiles(){
        driver.get(PropertyReader.getProperties("urlVacation"));

        VacationDealsPage vacationDealsPage = new VacationDealsPage();
        vacationDealsPage.promoECertificateOption.click();      //checking coupon option button
        vacationDealsPage.spanDropDownSmearn75KOption.click();  //selecting coupon
        vacationDealsPage.shopThisDealButton.click();          //shopping with selected coupon

        VacationDealsSearchPage vacationDealsSearchPage = new VacationDealsSearchPage();
        Assert.assertEquals(vacationDealsSearchPage.promoCodeInputBox.getAttribute("value"), "SMEARN75K");
    }

// testing if coupon SMGIFT400 can be selected and applied (entered) at the bottom of the next page
// SkyMiles number is omitted
    @Test (groups = "Andrey")
    public void ShopThisDealCouponSMGIFT400WithoutSkyMiles()  {
        driver.get(PropertyReader.getProperties("urlVacation"));

        VacationDealsPage vacationDealsPage = new VacationDealsPage();
        vacationDealsPage.promoECertificateOption.click();  // checking coupon options button
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait1.until(ExpectedConditions.visibilityOf(vacationDealsPage.spanDropDownSmgift400Option));
        vacationDealsPage.spanDropDownSmgift400Option.click(); // selecting a coupon
        vacationDealsPage.shopThisDealButton.click(); // shopping with selected coupon

        VacationDealsSearchPage vacationDealsSearchPage = new VacationDealsSearchPage();
        Assert.assertEquals(vacationDealsSearchPage.promoCodeInputBox.getAttribute("value"), "SMGIFT400");
    }


// testing if coupon SMGIFT400 can be selected and applied (entered) at the bottom of the next page
// SkyMiles number is added and generated through method in UtilMethodsAndrey
    @Test (groups = "Andrey")
    public void ShopThisDealCouponSMGIFT400WithSkyMiles() throws InterruptedException {
        driver.get(PropertyReader.getProperties("urlVacation"));

        VacationDealsPage vacationDealsPage = new VacationDealsPage();
        String skyMiles = UtilMethodsAndrey.obtainingNewSkyMilesNumber(); // obtaining new Skymiles Number from utilMethodsAndrey
        Thread.sleep(1000);
        driver.navigate().back();
        driver.navigate().back();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));

        vacationDealsPage.promoECertificateOption.click(); // checking coupon option button
        vacationDealsPage.SkyMilesInput.sendKeys(skyMiles); // entering SkyMiles number captired in the string
        vacationDealsPage.spanDropDownSmgift400Option.click(); // selecting a coupon
        Thread.sleep(1000);

        vacationDealsPage.shopThisDealButton.click(); // shopping with selected coupon and SkyMiles number

        VacationDealsSearchPage vacationDealsSearchPage = new VacationDealsSearchPage();

        SoftAssert softAssert = new SoftAssert(); // verifying both coupon and Skymiles number are applied
        softAssert.assertEquals(vacationDealsSearchPage.promoCodeInputBox.getAttribute("value"),"SMGIFT400");
        softAssert.assertEquals(vacationDealsSearchPage.skyMilesInputBox.getAttribute("value"), skyMiles);
        softAssert.assertAll();

    }

// testing if coupon SMEARN75K can be selected and applied (entered) at the bottom of the next page
// SkyMiles number is added and generated through method in UtilMethodsAndrey

    @Test (groups = "Andrey")
    public void ShopThisDealCouponSMEARN75KWithSkyMiles() throws InterruptedException {
        driver.get(PropertyReader.getProperties("urlVacation"));

        VacationDealsPage vacationDealsPage = new VacationDealsPage();
        String skyMiles = UtilMethodsAndrey.obtainingNewSkyMilesNumber(); // obtaining new Skymiles Number from utilMethodsAndrey
        Thread.sleep(1000);
        driver.navigate().back();
        driver.navigate().back();

        vacationDealsPage.promoECertificateOption.click(); //checking coupon options button
        vacationDealsPage.SkyMilesInput.sendKeys(skyMiles); // entering Skymiles number captured in the String
        vacationDealsPage.spanDropDownSmearn75KOption.click(); // selecting a coupon
        vacationDealsPage.shopThisDealButton.click(); //searching for deals

        VacationDealsSearchPage vacationDealsSearchPage = new VacationDealsSearchPage();
        SoftAssert softAssert = new SoftAssert();
        // verifying both coupon and Skymiles number are applied
        softAssert.assertEquals(vacationDealsSearchPage.promoCodeInputBox.getAttribute("value"),"SMEARN75K");
        softAssert.assertEquals(vacationDealsSearchPage.skyMilesInputBox.getAttribute("value"), skyMiles);
        softAssert.assertAll();
    }



}
