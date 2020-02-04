package SelenideUI;

import com.codeborne.selenide.Configuration;



public class TestBase {
    static {
        Configuration.baseUrl = "http://192.168.0.106:8000/petclinic";
    }
}
