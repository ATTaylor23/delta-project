package pages;

import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HomepagePage {

    public HomepagePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }




}
