package SelenideUI.Pages;

import SelenideUI.Objects.Veterinarian;
import SelenideUI.TestBase;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.testng.Assert.assertEquals;

public class VeterinariansPage extends TestBase {
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
    public VeterinariansPage openPage() {
        open("http://localhost:8000/petclinic/vets");
        return this;
    }

    @Step("Getting a list of vets by id")
    public ElementsCollection veterinariansList(){
        ElementsCollection list = $$(By.xpath("//*[@id='vets']/tbody/tr"));
        return list;
    }
    @Step("Getting a list of all Vets")
    public ElementsCollection getVetsList() {
        return $$(By.xpath("//tbody/tr/td[1]"));
    }

    @Step("Add button click")
    public NewVeterPage clickAddBtn(){
        $(By.xpath("//*[text()='Add Vet']")).click();
        return new NewVeterPage();
    }

    @Step("Getting new vet data and return to Vets page")
    private Veterinarian getVet(SelenideElement specData){
        Veterinarian veterinarian = new Veterinarian();
        String name = specData.find(By.xpath("//tbody/tr/td[1]")).getText();
        String specialties = specData.find(By.xpath("//tbody/tr/td[2]")).getText();

        return veterinarian;
    }
    @Step("Home button click")
    public void homeBtn(){
        $(By.xpath("//*[@class='btn btn-default'][text()='Home']")).click();
    }
}
