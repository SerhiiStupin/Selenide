package SelenideUI;

import API.ApiTestPreconditions;
import SelenideUI.Pages.NewOwnerPage;
import SelenideUI.Pages.OwnersPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.filter;

public class OwnerTests {
    ApiTestPreconditions apiPrec = new ApiTestPreconditions();

    @Test(description = "Checking Owners page")
    @Story("Checking the URL")
    @Severity(SeverityLevel.MINOR)
    @TmsLink("owners.com")
    public void pageCheck(){
       OwnersPage ownersPage = new OwnersPage();
       ownersPage.openPage()
               .assertUrl(url());
    }

    @Test(description = "Creating a new owner")
    @Story("Creating a new owner")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("owners.com")
    public void addNewOwnerTestWithApi() {
        apiPrec.setUp();
        OwnersPage ownersPage = new OwnersPage();
        ownersPage.openPage();
        int before = ownersPage.ownersList().size();
        ownersPage.assertUrl(url());
        apiPrec.addOwner();
        ownersPage.openPage();
        refresh();
        int after = ownersPage.ownersList().size();
        assertThat(before + 1).isEqualTo(after);


        apiPrec.deleteOwner();
    }

    @Test(description = "Back button test")
    @Story("Clicking the back button test")
    @Severity(SeverityLevel.TRIVIAL)
    @TmsLink("owners.com")
    public void backButtonTest() {
        NewOwnerPage newOwnerPage = new NewOwnerPage();
        newOwnerPage.openPage()
                .clickBackButton()
                .assertUrl(url());
    }

    @Test(description = "FirstName field validation")
    @Story("FirstName field validation")
    @Severity(SeverityLevel.TRIVIAL)
    @TmsLink("owners.com")
    public void firstNameValidationTests() {
        String firstNameLongValidation = "First name must be at least 2 characters long";
        String requiredFirst = "First name is required";
        NewOwnerPage newOwnerPage = new NewOwnerPage();
        newOwnerPage.openPage()
                .setFirstName("w");
        assertThat(firstNameLongValidation).isEqualTo(newOwnerPage.helpBlock());
        newOwnerPage.clearFName();
        assertThat(requiredFirst).isEqualTo(newOwnerPage.helpBlock());
    }

    @Test(description = "LastName field validation")
    @Story("LastName field validation")
    @Severity(SeverityLevel.TRIVIAL)
    @TmsLink("owners.com")
    public void lastNameValidationTests() {
        String lastNamelongValidation = "Last name must be at least 2 characters long";
        String requiredLast = "Last name is required";
        NewOwnerPage newOwnerPage = new NewOwnerPage();
        newOwnerPage.openPage()
                .setLastName("p");
        assertThat(lastNamelongValidation).isEqualTo(newOwnerPage.helpBlock());
        newOwnerPage.clearLastName();
        assertThat(requiredLast).isEqualTo(newOwnerPage.helpBlock());
    }

    @Test(description = "Address field validation")
    @Story("Address field validation")
    @Severity(SeverityLevel.TRIVIAL)
    @TmsLink("owners.com")
    public void addressValidationTest() {
        String address = "Address is required";
        NewOwnerPage newOwnerPage = new NewOwnerPage();
        newOwnerPage.openPage()
                .setAddress("*");
        newOwnerPage.clearAddress();
        assertThat(address).isEqualTo(newOwnerPage.helpBlock());
    }

    @Test(description = "City field validation")
    @Story("City field validation")
    @Severity(SeverityLevel.TRIVIAL)
    @TmsLink("owners.com")
    public void cityValidationTest() {
        String city = "City is required";
        NewOwnerPage newOwnerPage = new NewOwnerPage();
        newOwnerPage.openPage()
                .setCity("-");
        newOwnerPage.clearCity();
        assertThat(city).isEqualTo(newOwnerPage.helpBlock());
    }

    @Test(description = "Telephone field validation")
    @Story("Telephone field validation")
    @Severity(SeverityLevel.TRIVIAL)
    @TmsLink("owners.com")
    public void telephoneTest() {
        String telephone ="Phone number only accept digits";
        String telephoneRequired = "Phone number is required";
        NewOwnerPage newOwnerPage = new NewOwnerPage();
        newOwnerPage.openPage()
                .setTelephone(" ");
        assertThat(telephone).isEqualTo(newOwnerPage.helpBlock());
        newOwnerPage.clearTelephone();
        assertThat(telephoneRequired).isEqualTo(newOwnerPage.helpBlock());
    }
}
