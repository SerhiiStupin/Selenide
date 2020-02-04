package RemoteWeb;

import SelenideUI.Pages.NewVeterPage;
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
            driver.get("http://192.168.0.106:8000/petclinic/owners/add");
            sleep(10_000);

            WebElement h2 = driver.findElement(By.cssSelector("h2"));
            String text = h2.getText();
            assertThat(text).isEqualTo("New Owner");
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
}
//        //WebDriverManager.chromedriver().setup();
//        Configuration.remote = "http://localhost:4444/wd/hub";
//        Configuration.browserCapabilities = new DesiredCapabilities();
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setBrowserName("chrome");
//        //capabilities.setVersion("79.0");
//        capabilities.setCapability("enableVNC", true);
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--disable-gpu");
//        options.addArguments("--disable-extensions");
////        capabilities.setCapability("enableVideo", false);
////
//        RemoteWebDriver driver = new RemoteWebDriver(
//                URI.create("http://localhost:4444/wd/hub").toURL(),
//                capabilities
//        );
//        Dimension d = new Dimension(1382,744);
////Resize the current window to the given dimension
//        driver.manage().window().setSize(d);
//    }
//    @Test(description = "Returning to the home page")
//    @Severity(SeverityLevel.MINOR)
//    @Story("Returning to the home page")
//    @TmsLink("vets.com")
//    public void homeButtonTest() {
//        VeterinariansPage veterinariansPage = new VeterinariansPage();
//        veterinariansPage.openPage()
//                .assertUrl(url());
//    }
//
//    @Test(description = "LastName field validation")
//    @Story("LastName field validation")
//    @Severity(SeverityLevel.TRIVIAL)
//    @TmsLink("vets.com")
//    public void lastNameValidationTests(){
//       // WebDriverManager.chromedriver().setup();
//        String requiredLast = "Last name is required";
//        String lastName = "lastName";
//        String lastNamelongValidation = "Last name must be at least 2 characters long";
//        VeterinariansPage veterinariansPage = new VeterinariansPage();
//        veterinariansPage.openPage()
//                .assertUrl(url());
//        veterinariansPage.clickAddBtn()
//                .setLastName("=");
//        NewVeterPage newVeterPage = new NewVeterPage();
//        assertThat(newVeterPage.helpBlockGetText(lastName)).isEqualTo(lastNamelongValidation);
//        newVeterPage.clearLastName();
//        assertThat(newVeterPage.helpBlockGetText(lastName)).isEqualTo(requiredLast);
//    }



//    @Test
//    public void addNewOwnerTest() {
////        driver.get("http://localhost:8000/petclinic/owners/add");
//        //open(Configuration.baseUrl);
//        //open("/owners/add");
//        open("http://localhost:8000/petclinic/owners/add");
//        $("h2").shouldHave(Condition.text("New Owner"));
//
//        $(By.id("firstName")).setValue("Selenide");
//        $(By.id("lastName")).setValue("User");
//        $(By.id("address")).setValue("Street");
//        $(By.id("city")).setValue("Dnipro");
//        $(By.id("telephone")).setValue("1234567890");
//    }
//}
