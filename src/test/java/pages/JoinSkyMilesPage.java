package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class JoinSkyMilesPage {

    public JoinSkyMilesPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(id = "first_name")
    public WebElement firstNameInput;

    @FindBy (id="last_name")
    public WebElement lastNameInput;

    @FindBy(id="email")
    public WebElement emailInput;

    @FindBy(id="postal_code")
    public WebElement inputZip;

    @FindBy(xpath = "//select[@id='month']")
    public WebElement selectDobMonth;

    @FindBy (css = "select#day")
    public WebElement selectDobDay;

    @FindBy (id = "year")
    public WebElement selectDobYear;

    @FindBy (css = "select.form-control.required")
    public WebElement selectCountryRegion;

    @FindBy (xpath = "//input[@value='optin']")
    public WebElement checkboxAgreePrivacy;

    @FindBy (xpath = "//input[@class='btn btn-custom btn-lg btn-block']")
    public WebElement joinNowButton;
}
