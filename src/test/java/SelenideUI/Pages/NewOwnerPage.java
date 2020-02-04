package SelenideUI.Pages;

import SelenideUI.TestBase;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.testng.Assert.assertEquals;

public class NewOwnerPage extends TestBase {
    TestBase testBase = new TestBase();

    public TestBase getTestBase() {
        return testBase;
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
        $(By.id("firstName")).setValue(firstName);
    }
    @Step("Deleting one letter")
    public void clearFName(){
        $(By.id("firstName")).sendKeys(Keys.BACK_SPACE);
    }
    @Step("Entering of the last name")
    public void setLastName(String lastName) {
        $(By.id("lastName")).setValue(lastName);
    }
    @Step("Deleting one letter")
    public void clearLastName() {
        $(By.id("lastName")).sendKeys(Keys.BACK_SPACE);
    }
    @Step("Entering of the address")
    public void setAddress(String address) {
        $(By.id("address")).setValue(address);
    }
    @Step("Deleting one letter")
    public void clearAddress() {
        $(By.id("address")).sendKeys(Keys.BACK_SPACE);
    }
    @Step("Entering of the city")
    public void setCity(String city) {
        $(By.id("city")).setValue(city);
    }
    @Step("Deleting one letter")
    public void clearCity() {
        $(By.id("city")).sendKeys(Keys.BACK_SPACE);
    }
    @Step("Entering of the phone number")
    public void setTelephone(String telephone) {
        $(By.id("telephone")).setValue(telephone);
    }
    @Step("Deleting one number")
    public void clearTelephone() {
        $(By.id("telephone")).sendKeys(Keys.BACK_SPACE);
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

    @Step("Getting Title")
    public String getTitle() {
        String h2 = $(By.cssSelector("h2")).getText();
        return h2;
    }

//    String submitBtn = "[type='submit']";
//    private String firstNameLocator = "firstName";
//    private String lastNameLocator = "lastName";
//    private String addressLocator = "address";
//    private String cityLocator = "city";
//    private String telephoneLocator = "telephone";
}
