package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import utilities.PropertyReader;

public class HomepagePage{

    public HomepagePage(){

        PageFactory.initElements(Driver.getDriver(), this);
    }




// instance variables for SingUp
     @FindBy(xpath = "//a[@class='sign-up btn btn-link']")
     public WebElement SingUp;

    @FindBy (xpath = "//input[@aria-label='First Name']")
    public WebElement FirstName;

    @FindBy (xpath = "//input[@aria-label='Last Name']")
    public WebElement LastName;

    @FindBy (xpath = "//button[@id='basic-info-next']")
    public WebElement nextButton;


// instance variables for ContactInfo
    @FindBy (xpath="//span[contains(text(),'Contact Info') and @class='standard-enrollment__section-title--number2']")
    public WebElement contactInfoButton;

    @FindBy(xpath = "//input[@aria-label='Address Line 1']")
    public WebElement address;

    @FindBy(xpath = "//input[@aria-label='City']")
    public WebElement city;

    @FindBy(xpath = "//input[@aria-label='Postal Code']")
    public WebElement postalCode;

    @FindBy(xpath = "//input[@aria-label='Phone Number']")
    public WebElement phoneNumber;

    @FindBy(xpath = "//input[@aria-label='Email Address']")
    public WebElement emailAddress;

    @FindBy(xpath = "//button[@id='contact-info-next']")
    public WebElement nextContactButton;



//instance variables for Login Info page
     @FindBy(xpath = "//input[@aria-label='Enter a Username']")
    public WebElement userName;

    @FindBy(xpath = "//input[@aria-label='Enter  a Password']")
    public WebElement passWord;

    @FindBy(xpath = "//input[@aria-label='Confirm Password']")
    public WebElement ConfirmPassword;


    @FindBy(xpath = "//input[@aria-label='Answer 1']")
    public WebElement Answer1;


    @FindBy(xpath = "//input[@aria-label='Answer 2']")
    public WebElement Answer2;

    @FindBy(xpath = "//button[@id='login-info-submit']")
    public WebElement LoginButton;


  //   instance variables for MyTrip page
  @FindBy(xpath = "//label[contains(text(),'First Name (Required)')]")
  public WebElement FName;


    @FindBy(xpath = "//input[@placeholder='Last Name']")
    public WebElement LName;


    @FindBy(xpath = "//button[@id='btn-mytrip-submit']")
    public WebElement SubmitButton;




   // instance methods

    public void loginWithValidCredentials(){
        SingUp.click();
        FirstName.sendKeys(PropertyReader.getProperties("Aziza"));
        LastName.sendKeys(PropertyReader.getProperties("Ahmedova"));
        nextButton.click();

        contactInfoButton.click();
        address.sendKeys("1234 N 56 Street");
       city.sendKeys("Kansas");
       postalCode.sendKeys("66022");
       phoneNumber.sendKeys("(816)123-4567");
       emailAddress.sendKeys("aziza@gmail.com");
        nextContactButton.click();
      userName.sendKeys("Azizam09");
       passWord.sendKeys("Aziza09!");
       ConfirmPassword.sendKeys("Aziza09!");
       Answer1.sendKeys("Sizov");
       Answer2.sendKeys("Tuzik");
       LoginButton.click();

  FName.sendKeys("Aziza");
LName.sendKeys("Ahmedova");
SubmitButton.click();

}










}
