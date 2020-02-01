package SelenideUI;

import com.codeborne.selenide.Configuration;

public class TestBase {
    static {
        Configuration.baseUrl = "http://localhost:8000/petclinic";
    }
}
