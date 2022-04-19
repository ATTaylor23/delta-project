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



    @Test(groups = "Andrey")
    public void DeltaMilesRegistrationLink(){
        driver.get(PropertyReader.getProperties("urlVacation"));
        VacationDealsPage vacationDealsPage = new VacationDealsPage();
        vacationDealsPage.JoinMilesClub.click();
        Assert.assertEquals(driver.getTitle(), "Delta - Join SkyMiles");
    }


    @Test (groups = "Andrey")
    public void ShopThisDealCouponSMEARN75KWithoutSkyMiles(){
        driver.get(PropertyReader.getProperties("urlVacation"));

        VacationDealsPage vacationDealsPage = new VacationDealsPage();
        vacationDealsPage.promoECertificateOption.click();
        vacationDealsPage.spanDropDownSmearn75KOption.click();
        vacationDealsPage.shopThisDealButton.click();

        VacationDealsSearchPage vacationDealsSearchPage = new VacationDealsSearchPage();
        Assert.assertEquals(vacationDealsSearchPage.promoCodeInputBox.getAttribute("value"), "SMEARN75K");
    }


    @Test (groups = "Andrey")
    public void ShopThisDealCouponSMGIFT400WithoutSkyMiles()  {
        driver.get(PropertyReader.getProperties("urlVacation"));

        VacationDealsPage vacationDealsPage = new VacationDealsPage();
        vacationDealsPage.promoECertificateOption.click();
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait1.until(ExpectedConditions.visibilityOf(vacationDealsPage.spanDropDownSmgift400Option));
        vacationDealsPage.spanDropDownSmgift400Option.click();
        vacationDealsPage.shopThisDealButton.click();

        VacationDealsSearchPage vacationDealsSearchPage = new VacationDealsSearchPage();
        Assert.assertEquals(vacationDealsSearchPage.promoCodeInputBox.getAttribute("value"), "SMGIFT400");
    }


    @Test (groups = "Andrey")
    public void ShopThisDealCouponSMGIFT400WithSkyMiles() throws InterruptedException {
        driver.get(PropertyReader.getProperties("urlVacation"));

        VacationDealsPage vacationDealsPage = new VacationDealsPage();
        String skyMiles = UtilMethodsAndrey.obtainingNewSkyMilesNumber();
        Thread.sleep(1000);
        driver.navigate().back();
        driver.navigate().back();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));

        vacationDealsPage.promoECertificateOption.click();
        vacationDealsPage.SkyMilesInput.sendKeys(skyMiles);
        vacationDealsPage.spanDropDownSmgift400Option.click();
        Thread.sleep(1000);

        vacationDealsPage.shopThisDealButton.click();

        VacationDealsSearchPage vacationDealsSearchPage = new VacationDealsSearchPage();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(vacationDealsSearchPage.promoCodeInputBox.getAttribute("value"),"SMGIFT400");
        softAssert.assertEquals(vacationDealsSearchPage.skyMilesInputBox.getAttribute("value"), skyMiles);
        softAssert.assertAll();

    }


    @Test (groups = "Andrey")
    public void ShopThisDealCouponSMEARN75KWithSkyMiles() throws InterruptedException {
        driver.get(PropertyReader.getProperties("urlVacation"));

        VacationDealsPage vacationDealsPage = new VacationDealsPage();
        String skyMiles = UtilMethodsAndrey.obtainingNewSkyMilesNumber();
        Thread.sleep(1000);
        driver.navigate().back();
        driver.navigate().back();

        vacationDealsPage.promoECertificateOption.click();
        vacationDealsPage.SkyMilesInput.sendKeys(skyMiles);
        vacationDealsPage.spanDropDownSmearn75KOption.click();
        vacationDealsPage.shopThisDealButton.click();

        VacationDealsSearchPage vacationDealsSearchPage = new VacationDealsSearchPage();
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(vacationDealsSearchPage.promoCodeInputBox.getAttribute("value"),"SMEARN75K");
        softAssert.assertEquals(vacationDealsSearchPage.skyMilesInputBox.getAttribute("value"), skyMiles);
        softAssert.assertAll();
    }



}
