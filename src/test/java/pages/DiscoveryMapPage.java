package pages;

import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class DiscoveryMapPage {

    public DiscoveryMapPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }




}
