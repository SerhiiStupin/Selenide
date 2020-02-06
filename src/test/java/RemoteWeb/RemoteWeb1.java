package RemoteWeb;

import SelenideUI.Pages.NewOwnerPage;
import SelenideUI.Pages.NewVeterPage;
import SelenideUI.Pages.OwnersPage;
import SelenideUI.Pages.VeterinariansPage;
import SelenideUI.TestBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URI;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.assertj.core.api.Assertions.assertThat;

public class RemoteWeb1 {
    TestBase testBase = new TestBase();
        private WebDriver driver;

    @BeforeClass
    public void setUp () throws MalformedURLException {
            Configuration.baseUrl = "http://192.168.0.106:8000/petclinic";
            Configuration.remote = "http://localhost:4444/wd/hub";
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");
            capabilities.setCapability("enableVNC", true);
            Configuration.browserCapabilities = capabilities;

        driver = new RemoteWebDriver(
                URI.create("http://localhost:4444/wd/hub").toURL(),
                capabilities
        );
    }

    @AfterClass
    public void tearDown () {
        driver.quit();
    }

    @Test
    public void addNewOwnerTest () throws InterruptedException {
        NewOwnerPage newOwnerPage = new NewOwnerPage();
        newOwnerPage.openPage()
                .getTitle();
        assertThat(newOwnerPage.getTitle()).isEqualTo("New Owner");
        sleep(10_000);
    }
    @Test(description = "LastName field validation")
    @Story("LastName field validation")
    @Severity(SeverityLevel.TRIVIAL)
    @TmsLink("vets.com")
    public void lastNameValidationTests(){
       // WebDriverManager.chromedriver().setup();
        String requiredLast = "Last name is required";
        String lastName = "lastName";
        String lastNamelongValidation = "Last name must be at least 2 characters long";
        VeterinariansPage veterinariansPage = new VeterinariansPage();
        veterinariansPage.openPage()
                .assertUrl(url());
        veterinariansPage.clickAddBtn()
                .setLastName("=");
        NewVeterPage newVeterPage = new NewVeterPage();
        assertThat(newVeterPage.helpBlockGetText(lastName)).isEqualTo(lastNamelongValidation);
        newVeterPage.clearLastName();
        assertThat(newVeterPage.helpBlockGetText(lastName)).isEqualTo(requiredLast);
    }
    @Test(description = "Adding an empty vet")
    @Severity(SeverityLevel.MINOR)
    @Story("Adding pet without name and vet")
    @TmsLink("vets.com")
    public void addEmptyVet(){
        VeterinariansPage veterinariansPage = new VeterinariansPage();
        veterinariansPage.openPage();
        int beforeSize = veterinariansPage.veterinariansList().size();
        NewVeterPage newVeterPage = veterinariansPage.clickAddBtn();
        veterinariansPage = newVeterPage.saveVetButtonClick();
        veterinariansPage.openPage();
        int after = veterinariansPage.veterinariansList().size();
        assertThat(beforeSize).isEqualTo(after);
    }
    @Test(description = "Returning to the home page")
    @Severity(SeverityLevel.MINOR)
    @Story("Returning to the home page")
    @TmsLink("vets.com")
    public void homeButtonTest() {
        VeterinariansPage veterinariansPage = new VeterinariansPage();
        veterinariansPage.openPage()
                .assertUrl(url());
    }

}



