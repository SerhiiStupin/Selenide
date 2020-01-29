package SelenideUI;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.testng.Assert.assertEquals;

public class NewOwnerPage {
    String firstNameLocator = "firstName";
    String lastNameLocator = "lastName";
    String addressLocator = "address";
    String cityLocator = "city";
    String telephoneLocator = "telephone";
    String submitBtn = "[type='submit']";

    static {
        Configuration.baseUrl = "http://localhost:8000/petclinic";
    }
    @Step("Opening NewOwners page")
    public NewOwnerPage openPage() {
        open("/owners/add");
        return this;
    }
    @Step("Checking the current URL")
    public void assertUrl(String url){
        String currentUrl = url();
        assertEquals(currentUrl, url);
    }

    @Step("Entering of the first name")
    public void setFirstName(String firstName) {
        $(By.id(firstNameLocator)).setValue(firstName);
    }
    @Step("Deleting one letter")
    public void clearFName(){
        $(By.id(firstNameLocator)).sendKeys(Keys.BACK_SPACE);
    }
    @Step("Entering of the last name")
    public void setLastName(String lastName) {
        $(By.id(lastNameLocator)).setValue(lastName);
    }
    @Step("Deleting one letter")
    public void clearLastName() {
        $(By.id(lastNameLocator)).sendKeys(Keys.BACK_SPACE);
    }
    @Step("Entering of the address")
    public void setAddress(String address) {
        $(By.id(addressLocator)).setValue(address);
    }
    @Step("Deleting one letter")
    public void clearAddress() {
        $(By.id(addressLocator)).sendKeys(Keys.BACK_SPACE);
    }
    @Step("Entering of the city")
    public void setCity(String city) {
        $(By.id(cityLocator)).setValue(city);
    }
    @Step("Deleting one letter")
    public void clearCity() {
        $(By.id(cityLocator)).sendKeys(Keys.BACK_SPACE);
    }
    @Step("Entering of the phone neumber")
    public void setTelephone(String telephone) {
        $(By.id(telephoneLocator)).setValue(telephone);
    }
    @Step("Deleting one number")
    public void clearTelephone() {
        $(By.id(telephoneLocator)).sendKeys(Keys.BACK_SPACE);
    }
    @Step("Add button click")
    public OwnersPage clickAddOwnerButton() {
        $(By.xpath("//*[text()='Add Owner']")).click();
        return new OwnersPage();
    }
    @Step("Back button click")
    public OwnersPage clickBackButton() {
        $(By.xpath("//*[text()='Back']")).click();
        return new OwnersPage();
    }
    @Step("Getting help-block text")
    public Object helpBlock(){
        return $(By.xpath("//*[@class='help-block']")).getText();
    }
}
