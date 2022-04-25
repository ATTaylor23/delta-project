package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.Driver;
import utilities.PropertyReader;

public class HomepagePage {

    public HomepagePage() {

        PageFactory.initElements(Driver.getDriver(), this);
    }

    // Project 2

// instance variables for Advance search Button functionality
    @FindBy(xpath = "//a[contains(text(),'Advanced Search')]")
    public WebElement advanceSearch;

    @FindBy(xpath = "//input[@id='nearbyAirports']")
    public WebElement ShowFaresButton;

    @FindBy(xpath = "//span[contains(text(),'Basic Economy')]")
    public WebElement BasicEconomy;


    //  instance variables for Flight Status Button functionality
    @FindBy(xpath = "//a[@id='headPrimary4']")
    public WebElement FlightStatusButton;

    // Click calendar after Flight Status
    @FindBy(id = "input_departureDate_1")
    public WebElement CalendarButton;


    // Click April 30
    @FindBy(xpath = " //tbody[@class='dl-datepicker-tbody-0']//tr[6]//td[7]//a[@aria-label='30 April 2022, Saturday']")
    public WebElement MonthButton;


    //Random enter number
    @FindBy(xpath = "//input[@id='flightNo']")
    public WebElement RandomNumber;


    // check Best Fares all name options
    @FindBy(xpath = "//a[@id='headPrimary4']")
    public WebElement BestFaresAllOptions;


    // Verify Flight Status notifications
    @FindBy(xpath = "//h1[contains(text(),'Flight Status & Notifications')]")
    public WebElement Notification;


// Project 1

    // instance variables for SingUp
    @FindBy(xpath = "//div//div[@class='signup-container ng-star-inserted']//a[@href='/profile/enrolllanding.action']")
    public WebElement SingUp;

    @FindBy(xpath = "//input[@aria-label='First Name']")
    public WebElement FirstName;

    @FindBy(xpath = "//input[@aria-label='Last Name']")
    public WebElement LastName;

    @FindBy(xpath = "//button[@id='basic-info-next']")
    public WebElement nextButton;


    // instance variables for ContactInfo
    @FindBy(xpath = "//span[contains(text(),'Contact Info') and @class='standard-enrollment__section-title--number2']")
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
    @FindBy(xpath = "//div[@class='col-lg-2 offset-md-2 col-md-8 offset-lg-0']//input[@placeholder='First Name']")
    public WebElement FName;


    @FindBy(xpath = "//div[@class='col-lg-2 offset-md-2 col-md-8 offset-lg-0']//input[@placeholder='Last Name']")
    public WebElement LName;


    @FindBy(xpath = "//button[@id='btn-mytrip-submit']")
    public WebElement SubmitButton;

}


   // instance methods

//    public  void loginWithValidCredentials(){
//
//        // Sig Up button
//        SingUp.click();
//
//        // Basic Info button
//        FirstName.sendKeys(PropertyReader.getProperties("Aziza"));
//        LastName.sendKeys(PropertyReader.getProperties("Ahmedova"));
//        nextButton.click();
//
//
//        // Contact Info button
//        contactInfoButton.click();
//        address.sendKeys("1234 N 56 Street");
//       city.sendKeys("Kansas");
//       postalCode.sendKeys("66022");
//       phoneNumber.sendKeys("(816)123-4567");
//       emailAddress.sendKeys("aziza@gmail.com");
//        nextContactButton.click();
//
//        //Login button
//        userName.sendKeys("Azizam09");
//       passWord.sendKeys("Aziza09!");
//       ConfirmPassword.sendKeys("Aziza09!");
//       Answer1.sendKeys("Sizov");
//       Answer2.sendKeys("Tuzik");
//       LoginButton.click();
//
//       // MyTrip button
//       FName.sendKeys("Aziza");
//       LName.sendKeys("Ahmedova");
//       SubmitButton.click();
//
//
//       // Advance search button
//        advanceSearch.click();
//        ShowFaresButton.click();
//
//        /*Flight Status Button */
//        FlightStatusButton.click();
//
//
//        // BestFaresAllOptions names
//        BestFaresAllOptions.click();
//
//        //Verify notification
//        Notification.getText();
//        CalendarButton.click();
//        MonthButton.click();
//        RandomNumber.click();
//
//}
//
//
//








