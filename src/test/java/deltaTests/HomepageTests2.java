package deltaTests;

import com.sun.xml.internal.ws.server.DefaultResourceInjector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomepagePage;
import utilities.PropertyReader;

import java.time.Duration;
import java.util.List;

public class HomepageTests2 extends TestBase {

    @Test
    public void AdvanceSearchButton() throws InterruptedException {
        HomepagePage advance = new HomepagePage();

        driver.get(PropertyReader.getProperties("urlHome"));
        logger.info("Click AdvanceSearch");
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals("https://www.delta.com/")) {
            System.out.println("Current URL is: " + currentUrl);
        } else {
            System.out.println("Test failed. The current url was " + currentUrl);
        }
        Select basicEconom = new Select(advance.BasicEconomy);
        Actions actions = new Actions(driver);
        Thread.sleep(2000);
        if (!advance.advanceSearch.isSelected() && advance.ShowFaresButton.isSelected()) {
            advance.advanceSearch.click();
            actions.click(advance.ShowFaresButton).build().perform();
            basicEconom.selectByIndex(2);
        } else {
            System.out.println("Advance search button is not clickable");
        }
    }

    @Test (dependsOnMethods = "AdvanceSearchButton")
    public void VerifyFaresNames(){
        logger.info("Check Best Fare options");
        Select dropdown= new Select(driver.findElement(By.xpath("//select[@id='faresFor']//option[@class='ng-tns-c1-2 ng-star-inserted']")));
        List<WebElement> options= dropdown.getOptions();
        for (int i=0; i<options.size();i++){
            System.out.println(options.get(i).getText());
        }

    }
        @Test
        public void FlightStatusButton(){

            driver.get(PropertyReader.getProperties("urlHome"));
            logger.info("Click FlightStatus button");
            HomepagePage FlightStatus = new HomepagePage();
            FlightStatus.FlightStatusButton.click();
        }



 @DataProvider(name="Alloptions",parallel = true)
    public Object[][]name(){
        return  new Object[][]{
                new Object[]{1},
                new Object[]{2},
                new Object[]{3},
                new Object[]{4},
                new Object[]{5},
                new Object[]{6},

        };
 }






}








