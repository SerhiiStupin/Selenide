package SelenideUI;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import static com.codeborne.selenide.Selenide.$;

public class NewVeterPage {
    @Step("Setting vet's data")
    public void createVet(Veterinarian veterinarian) {
        setFirstName(veterinarian.getFirstName());
        setLastName(veterinarian.getLastName());
        specTypeList(veterinarian.getType());
    }
    @Step("Setting first name")
    public void setFirstName(String firstName){
        WebElement nameField = firstNameLocator();
        nameField.clear();
        nameField.sendKeys(firstName);
    }
    @Step("Deleting one letter")
    public void clearFirstName(){
        WebElement nameField = firstNameLocator();
        nameField.sendKeys(Keys.BACK_SPACE);
    }
    @Step("Entering of the last name")
    public void setLastName(String lastName){
        WebElement lastNameField = lastNameLocator();
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }
    @Step("Deleting one letter")
    public void clearLastName(){
        WebElement nameField = lastNameLocator();
        nameField.sendKeys(Keys.BACK_SPACE);
    }
    @Step("Getting one of the vet specialties")
    public void specTypeList(int itemSelect){
        $(By.xpath("//*[@id='specialties']")).click();
        $(By.xpath("//*[@value='"+itemSelect+": Object']")).click();
    }
    @Step("Save button click")
    public VeterinariansPage saveVetButtonClick(){
        $(By.xpath("//*[text()='Save Vet']")).click();
        return new VeterinariansPage();
    }
    @Step("Getting validation error text")
    public String helpBlockGetText(String name){
        SelenideElement actualError = $(By.xpath("//*[@id='" + name + "']/following-sibling::span[2]"));
        return actualError.getText();
    }
    @Step("Getting first name")
    private WebElement firstNameLocator(){
        return $(By.xpath("//*[@id='firstName']"));
    }
    @Step("Getting last name")
    private WebElement lastNameLocator(){
        return $(By.xpath("//*[@id='lastName']"));
    }
}
