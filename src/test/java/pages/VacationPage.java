package pages;

import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class VacationPage {

    public VacationPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }




}
