package SelenideUI.Pages;


import SelenideUI.TestBase;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.testng.Assert.assertEquals;

public class SpecPage extends TestBase {
    TestBase testBase = new TestBase();

    public TestBase getTestBase() {
        return testBase;
    }
    @Step("Checking the current URL")
    public void assertUrl(String url){
        String currentUrl = url();
        assertEquals(currentUrl, url);
    }
    @Step("Opening Owners page")
    public SpecPage openPage() {
        open("/specialties");
        return this;
    }
    @Step("Getting a list of specialties")
    public ElementsCollection specialists(){
        return $$(By.xpath("//tbody/tr"));
    }
    @Step("Setting a spacielty name")
    public void setName (String name){
        $(By.xpath("//*[@id='name']")).setValue(name);
    }
    @Step("Save button click")
    public void saveBtn(){
       $(By.xpath("//*[text()='Save']")).click();
    }
    @Step("Add button click")
    public void addBtn(){
        $(By.xpath("//div/button[2]")).click();
    }
    @Step("Home button click")
    public void homeBtn(){
        $(By.xpath("//div/div/button[1]")).click();
    }
    @Step("Deleting of the last item")
    public void deleteLast(){
        $(By.xpath("//tbody/tr[last()]/td/button[text()='Delete']")).click();
    }
    @Step("Getting the specialty name")
    public String specList() {
        return $(By.xpath("//tr[last()]/td/input")).getAttribute("ng-reflect-model");
    }
}
