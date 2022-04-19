package pages;

import org.openqa.selenium.By;
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

    WebElement driver;

    public HotelPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[@data-analytics-id='home-shop-2']")
    public WebElement shopHotelsBtn;

    @FindBy(id = "qf-0q-destination")
    public WebElement cityBox;



}
