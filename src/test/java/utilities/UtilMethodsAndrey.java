package utilities;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.JoinSkyMilesPage;
import pages.VacationDealsPage;

import java.time.Duration;

public class UtilMethodsAndrey {

    public  static String obtainingNewSkyMilesNumber()  {
        Driver.getDriver().get(PropertyReader.getProperties("urlVacation"));

        VacationDealsPage vacationDealsPage = new VacationDealsPage();
        vacationDealsPage.JoinMilesClub.click(); //clicking to join the SkyMiles Club

        JoinSkyMilesPage joinSkyMilesPage = new JoinSkyMilesPage();
        joinSkyMilesPage.firstNameInput.sendKeys(new Faker().name().firstName()); //generating fake firstName input
        joinSkyMilesPage.lastNameInput.sendKeys(new Faker().name().lastName()); //generating fake lastName input
        joinSkyMilesPage.emailInput.sendKeys(new Faker().internet().emailAddress());// generating fake email input
        new Select(joinSkyMilesPage.selectDobMonth).selectByValue("04"); //selecting a month in DOB
        new Select(joinSkyMilesPage.selectDobDay).selectByIndex(25); //selecting a day in DOB
        new Select(joinSkyMilesPage.selectDobYear).selectByVisibleText("1988"); //selecting a year in DOB
        new Select(joinSkyMilesPage.selectCountryRegion).selectByValue("US"); // selecting country
        joinSkyMilesPage.inputZip.sendKeys(new Faker().address().zipCode()); // entering fake zip
        joinSkyMilesPage.checkboxAgreePrivacy.click(); //PrivacyPolicy checkbox check
        WebDriverWait waitBeforeJoinSkyMiles = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        joinSkyMilesPage.joinNowButton.click(); // joining club to obtain a new number


        WebDriverWait waitSkyMilesNumber = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        waitSkyMilesNumber.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(), 'Your SkyMiles Number is')]//span"))); //locating a new SkyMiles number element

        return Driver.getDriver().findElement(By.xpath("//p[contains(text(), 'Your SkyMiles Number is')]//span")).getText(); //returning a new SkyMiles number captured from element

    }
}
