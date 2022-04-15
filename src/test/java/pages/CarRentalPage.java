package pages;

import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class CarRentalPage {

    public CarRentalPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

}
