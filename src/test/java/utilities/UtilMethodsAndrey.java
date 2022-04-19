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
        vacationDealsPage.JoinMilesClub.click();

        JoinSkyMilesPage joinSkyMilesPage = new JoinSkyMilesPage();
        joinSkyMilesPage.firstNameInput.sendKeys(new Faker().name().firstName());
        joinSkyMilesPage.lastNameInput.sendKeys(new Faker().name().lastName());
        joinSkyMilesPage.emailInput.sendKeys(new Faker().internet().emailAddress());
        new Select(joinSkyMilesPage.selectDobMonth).selectByValue("04");
        new Select(joinSkyMilesPage.selectDobDay).selectByIndex(25);
        new Select(joinSkyMilesPage.selectDobYear).selectByVisibleText("1988");
        new Select(joinSkyMilesPage.selectCountryRegion).selectByValue("US");
        joinSkyMilesPage.inputZip.sendKeys(new Faker().address().zipCode());
        joinSkyMilesPage.checkboxAgreePrivacy.click();
        WebDriverWait waitBeforeJoinSkyMiles = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        joinSkyMilesPage.joinNowButton.click();


        WebDriverWait waitSkyMilesNumber = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        waitSkyMilesNumber.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(), 'Your SkyMiles Number is')]//span")));

        return Driver.getDriver().findElement(By.xpath("//p[contains(text(), 'Your SkyMiles Number is')]//span")).getText();

    }
}
