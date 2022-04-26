package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import utilities.Driver;
import utilities.PropertyReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class HotelPage {


    public HotelPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[@data-analytics-id='home-shop-2']")
    public WebElement shopHotelsBtn;

    @FindBy(id = "qf-0q-destination")
    public WebElement cityBox;

    @FindBy(id = "secondary-static-link-15")
    public WebElement hotelButton2;

    @FindBy(id = "overlay-q-dont-know-dates")
    public WebElement idk;

    @FindBy(xpath = "//input[@id='overlay-q-destination']")
    public WebElement overlayCityBox;

    public void hotelPageSelection() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

        Driver.getDriver().get(PropertyReader.getProperties("urlHome"));

        boolean isPresent = Driver.getDriver().findElements(By.xpath("//button[@class='advisory-close-icon float-right circle-outline icon-moreoptionsclose']")).size() > 0;

        if(isPresent){
            js.executeScript("arguments[0].click();", Driver.getDriver().findElement(By.xpath("(//button[@role='button'])[1]")));
            shopHotelsBtn.click();
            Thread.sleep(3000);
        }else{
            shopHotelsBtn.click();
        }
    }

    public void secondaryPath(){
        Driver.getDriver().get(PropertyReader.getProperties("urlHome"));

        Driver.getDriver().findElement(By.xpath("//a[@id='headSectab1']")).click();
        hotelButton2.click();

    }

    public void switchTabs(){
        ArrayList<String> tabs = new ArrayList<>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(tabs.get(1));
    }

    public void covidAlert(){
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();


        boolean covidAlert = Driver.getDriver().findElements(By.xpath("//a[@class='icon close-icon po-a cursor c-hds-arch-3']")).size() > 0;

        if(covidAlert){
            js.executeScript("arguments[0].click();", Driver.getDriver().findElement(By.xpath("//a[@class='icon close-icon po-a cursor c-hds-arch-3']")));
        }
    }


}
