package SelenideUI;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.assertj.core.util.Lists.list;

public class VeterinariansPage{

    @Step("Getting a list of vets by id")
    public ElementsCollection veterinariansList(){
        ElementsCollection list = $$(By.xpath("//*[@id='vets']/tbody/tr"));
        return list;
    }
    @Step("Getting a list of all Vets")
    public ElementsCollection getVetsList(){
        ElementsCollection vets = null;

        ElementsCollection vetsList = $$(By.xpath("//tbody/tr/td[1]"));

        vetsList.texts();
//        for (ElementsCollection vet : vetsList) {
//            vets.add(vet.getText());
//        }
        return vetsList;
    }
//    public List<Veterinarian> getVetsList() {
//        List<Veterinarian> veterinariansList = new ArrayList<>();
//        WebElement vetTable = driver.findElement(By.xpath("//tbody/tr/td[1]"));
//
//        List<WebElement> vetsInList = vetTable.findElements(By.xpath("//tbody/tr"));
//        for (WebElement webElement : vetsInList) {
//            veterinariansList.add(getVet(webElement));
//        }
//        return veterinariansList;
//    }

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
