package deltaTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.VacationDealsPage;
import utilities.PropertyReader;


import java.time.Duration;
import java.util.Set;

public class VacationDealsTest02 extends TestBase {




     @Test
    public void testAllLinksOnPage(){

         driver.get(PropertyReader.getProperties("urlVacation"));
         VacationDealsPage vacationDealsPage = new VacationDealsPage();
         Actions actions = new Actions(driver);

         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

         actions.click(vacationDealsPage.discoverMoreLinkRight).build().perform();
         actions.click(vacationDealsPage.discoverMoreLinkLeft).build().perform();
         actions.click(vacationDealsPage.designedForSkyMilesLink).build().perform();
         actions.click(vacationDealsPage.earnSkyMilesLink).build().perform();
         actions.click(vacationDealsPage.useSkuMilesLink).build().perform();


         SoftAssert softAssert = new SoftAssert();

         String discoverMoreLinkRightTitle = "Dominican Republic Vacation Packages & Travel Deals";
         switchToWindow(discoverMoreLinkRightTitle, driver);
         softAssert.assertEquals(driver.getTitle(),discoverMoreLinkRightTitle);

         String discoverMoreLinkLeftTitle = "AMR™ Collection";
         switchToWindow(discoverMoreLinkLeftTitle, driver);
         softAssert.assertEquals(driver.getTitle(), discoverMoreLinkLeftTitle);

         String designedForSkyMilesTitle = "Delta - Join SkyMiles";
         switchToWindow(designedForSkyMilesTitle, driver);
         softAssert.assertEquals(driver.getTitle(),designedForSkyMilesTitle);

         String earnSkyMilesTitle = "Earn Miles";
         switchToWindow(earnSkyMilesTitle, driver);
         softAssert.assertEquals(driver.getTitle(), earnSkyMilesTitle);

         String useSkuMilesTitle = "Use Miles";
         switchToWindow(useSkuMilesTitle, driver);
         softAssert.assertEquals(driver.getTitle(), useSkuMilesTitle);

         softAssert.assertAll();


     }






     public static void switchToWindow (String title, WebDriver driver){
         Set<String> windowHandles = driver.getWindowHandles();
         for(String windowHandle : windowHandles){
             driver.switchTo().window(windowHandle);
             if(driver.getTitle().equals(title)){
                 break;
             }
         }
     }

}
