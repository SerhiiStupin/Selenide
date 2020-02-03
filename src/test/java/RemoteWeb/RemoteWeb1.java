package RemoteWeb;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URI;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RemoteWeb1 {
    @BeforeClass
    public void setUp() throws MalformedURLException {
        //WebDriverManager.chromedriver().setup();
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browserCapabilities = new DesiredCapabilities();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("79.0");
        capabilities.setCapability("enableVNC", true);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
//        capabilities.setCapability("enableVideo", false);
//
//        RemoteWebDriver driver = new RemoteWebDriver(
//                URI.create("http://localhost:4444/wd/hub").toURL(),
//                capabilities
//        );
    }
    @Test
    public void addNewOwnerTest() {
//        driver.get("http://localhost:8000/petclinic/owners/add");
        //open(Configuration.baseUrl);
        //open("/owners/add");
        open("http://localhost:8000/petclinic/owners/add");
        $("h2").shouldHave(Condition.text("New Owner"));

        $(By.id("firstName")).setValue("Selenide");
        $(By.id("lastName")).setValue("User");
        $(By.id("address")).setValue("Street");
        $(By.id("city")).setValue("Dnipro");
        $(By.id("telephone")).setValue("1234567890");
    }
}
