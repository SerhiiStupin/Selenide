package SelenideUI;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.testng.Assert.assertEquals;

public class OwnersPage {
    static {
        Configuration.baseUrl = "http://localhost:8000/petclinic";
    }
    @Step("Checking the current URL")
    public void assertUrl(String url){
        String currentUrl = url();
        assertEquals(currentUrl, url);
    }
    @Step("Search of the added owner name")
    public WebElement addedOwnerSearch() {

        return $(By.xpath("//*[text()='John Snow']"));
    }

    @Step("Opening Owners page")
    public OwnersPage openPage() {
        open("/owners");
        return this;
    }

    public List<String> getOwnersNames() {
        List<String> owners = new ArrayList<>();

        ElementsCollection elements = Selenide.elements(".ownerFullName>a");
        for (WebElement fullName : elements) {
            owners.add(fullName.getText());
        }

        return owners;
    }

//    public List<Owner> getOwnersList() {
//        List<Owner> owners = new ArrayList<>();
//        SelenideElement ownersTable = $(By.xpath("//*[@class='table-responsive']"));
//
//
//        List<WebElement> ownersList = ownersTable.findElements(By.xpath(".//tbody/tr"));
//        for (WebElement userRow : ownersList) {
//            owners.add(createOwner(userRow));
//        }
//        return owners;
//    }

    //    public NewOwnerPage clickAddOwnerBtn() {
//        WebElement addOwnerBtn = driver.findElement(By.xpath("//*[text()='Add Owner']"));
//        addOwnerBtn.click();
//        return new NewOwnerPage(driver);
//    }
    @Step("Add new owner button click")
    public NewOwnerPage clickAddOwnerBtn() {
        $(By.xpath("//*[text()='Add Owner']")).click();
        return new NewOwnerPage();
    }
//    @Step("New owner data setting")
//    private Owner createOwner(WebElement userRow) {
//        com.auto.PageObjectTests.Owner owner = new com.auto.PageObjectTests.Owner();
//        String fullName = userRow.findElement(By.xpath("./td/a")).getText();
//        String[] fullNameArray = fullName.split(" ");
//        if (fullNameArray.length > 1) {
//            owner.setFirstName(fullNameArray[0]);
//            owner.setLastName(fullNameArray[1]);
//        } else {
//            owner.setFirstName(fullNameArray[0]);
//        }
//        owner.setAddress(userRow.findElement(By.xpath("./td[2]")).getText());
//        owner.setCity(userRow.findElement(By.xpath("./td[3]")).getText());
//        owner.setTelephone(userRow.findElement(By.xpath("./td[4]")).getText());
//
//        String pets = userRow.findElement(By.xpath("./td[5]")).getText();
//        if(!pets.isEmpty()) {
//            owner.setPets(pets);
//        }
//
//        return (Owner) owner;
//    }
    @Step("Getting a list of owners")
    public ElementsCollection ownersList(){
        return $$(By.xpath("//tbody/tr"));
    }

    @Step("Click on owner's name")
    public OwnerInformationPage openOwnerInfo(String fullName) {
        $(By.xpath("//a[text()='"+fullName+"']")).click();
        return new OwnerInformationPage();
    }
    @Step("New owner button click")
    public void newOwner(){
        $(By.xpath("//tr[last()]/td[1]/a")).click();
    }
}
