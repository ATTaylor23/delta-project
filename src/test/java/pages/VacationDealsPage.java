package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class VacationDealsPage {

    public VacationDealsPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(linkText = "Join for free today.")
    public WebElement JoinMilesClub;


    @FindBy (css = "span.select-ui-icon.icon-Dropdown-caret")
    public WebElement promoECertificateOption;

    @FindBy (xpath = "//input[@name='smnumber']")
    public WebElement SkyMilesInput;

    @FindBy (xpath = "//button[@class='button ui-link shopBtn cta-red']")
    public WebElement shopThisDealButton;

    @FindBy (css ="a[href='https://www.delta.com/us/en/delta-vacations/featured-partners/amresorts-collection']")
    public WebElement discoverMoreLinkLeft;

    @FindBy (xpath = "//a[@href='https://www.delta.com/us/en/delta-vacations/caribbean-vacations/dominican-republic-vacations']")
    public WebElement discoverMoreLinkRight;

    @FindBy(linkText = "Where to Go Now")
    public WebElement whereToGoNowLink;

    @FindBy (css = "a[href='https://www.delta.com/us/en/delta-vacations/cdc-requirements-faqs']")
    public WebElement faqHotelInfoLink;

    @FindBy (xpath = "//a[@href='https://www.delta.com/us/en/delta-vacations/delta-carestandard']")
    public WebElement deltaStandardsLink;

    @FindBy (xpath = "//li[contains(text(), 'SMGIFT400')]")
    public WebElement spanDropDownSmgift400Option;

    @FindBy (xpath = "//li[contains(text(), 'SMEARN75K')]")
    public WebElement spanDropDownSmearn75KOption;

    @FindBy (xpath = "//a[contains(text(), 'designed for SkyMiles Members')]")
    public WebElement designedForSkyMilesLink;

    @FindBy (linkText = "Earn")
    public WebElement earnSkyMilesLink;

    @FindBy (linkText = "use")
    public WebElement useSkuMilesLink;


}
