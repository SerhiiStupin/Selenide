package SelenideUI;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestBase {
    static {
        WebDriverManager.chromedriver().setup();
        Configuration.baseUrl = "http://localhost:8000/petclinic/";
    }
}
