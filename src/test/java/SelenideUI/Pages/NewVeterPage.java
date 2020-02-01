package SelenideUI.Pages;

import SelenideUI.Objects.Veterinarian;
import SelenideUI.TestBase;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.Keys.BACK_SPACE;

public class NewVeterPage extends TestBase {
    TestBase testBase = new TestBase();
    public TestBase getTestBase() {
        return testBase;
    }

    @Step("Setting vet's data")
    public void createVet(Veterinarian veterinarian) {
        setFirstName(veterinarian.getFirstName());
        setLastName(veterinarian.getLastName());
        specTypeList(veterinarian.getType());
    }
    @Step("Setting first name")
    public void setFirstName(String firstName){
        $(By.xpath("//*[@id='firstName']")).setValue(firstName);
    }
    @Step("Deleting one letter")
    public void clearFirstName(){
        $(By.xpath("//*[@id='firstName']")).sendKeys(Keys.BACK_SPACE);
    }
    @Step("Entering of the last name")
    public void setLastName(String lastName){
        $(By.xpath("//*[@id='lastName']")).setValue(lastName);
    }
    @Step("Deleting one letter")
    public void clearLastName(){
        $(By.xpath("//*[@id='lastName']")).sendKeys(BACK_SPACE);
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
        return $(By.xpath("//*[@id='" + name + "']/following-sibling::span[2]")).getText();
    }
}
