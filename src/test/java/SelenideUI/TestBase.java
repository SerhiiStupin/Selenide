package SelenideUI;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestBase {
    static {
        WebDriverManager.chromedriver().setup();
        Configuration.baseUrl = "http://192.168.0.106:8000/petclinic/";
    }
}
