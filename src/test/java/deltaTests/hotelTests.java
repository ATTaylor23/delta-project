package deltaTests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.HotelPage;
import utilities.PropertyReader;
import utilities.SeleniumUtils;

public class hotelTests extends TestBase{


    @Test
    public void testTest(){
        driver.get(PropertyReader.getProperties("urlHome"));

        HotelPage hotelPage = new HotelPage();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].click", hotelPage.shopHotelsBtn);

        driver.manage().window().maximize();



    }
}
