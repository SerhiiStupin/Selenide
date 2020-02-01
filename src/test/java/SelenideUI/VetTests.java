package SelenideUI;

import API.ApiTestPreconditions;
import SelenideUI.Pages.NewVeterPage;
import SelenideUI.Pages.VeterinariansPage;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("PetClinic")
@Feature("Veterinarians")
public class VetTests {
    ApiTestPreconditions apiPrec = new ApiTestPreconditions();

    @Test(description = "Creating a new vet")
    @Story("Creating a new vet")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("vets.com")
    public void addVetTest() {
        apiPrec.setUp();
        apiPrec.vetCreationPrec();
        VeterinariansPage veterinariansPage = new VeterinariansPage();
        veterinariansPage.openPage();
        $(byText("i Bolit"));
    }

    @Test(description = "Adding an empty vet")
    @Severity(SeverityLevel.MINOR)
    @Story("Adding pet without name and vet")
    @TmsLink("vets.com")
    public void addEmptyVet(){
        VeterinariansPage veterinariansPage = new VeterinariansPage();
        veterinariansPage.openPage();
        int beforeSize = veterinariansPage.veterinariansList().size();
        NewVeterPage newVeterPage = veterinariansPage.clickAddBtn();
        veterinariansPage = newVeterPage.saveVetButtonClick();
        veterinariansPage.openPage();
        int after = veterinariansPage.veterinariansList().size();
        assertThat(beforeSize).isEqualTo(after);
    }

    @Test(description = "FirstName field validation")
    @Story("FirstName field validation")
    @Severity(SeverityLevel.TRIVIAL)
    @TmsLink("vets.com")
    public void firstNameValidationTests() {
        String firstName = "firstName";
        String firstNameLongValidation = "First name must be at least 2 characters long";
        String requiredFirst = "First name is required";
        VeterinariansPage veterinariansPage = new VeterinariansPage();
        veterinariansPage.openPage()
                .clickAddBtn()
                .setFirstName("*");
        NewVeterPage newVeterPage = new NewVeterPage();
        assertThat(newVeterPage.helpBlockGetText(firstName)).isEqualTo(firstNameLongValidation);
        newVeterPage.clearFirstName();
        assertThat(newVeterPage.helpBlockGetText(firstName)).isEqualTo(requiredFirst);
    }

    @Test(description = "LastName field validation")
    @Story("LastName field validation")
    @Severity(SeverityLevel.TRIVIAL)
    @TmsLink("vets.com")
    public void lastNameValidationTests(){
        String requiredLast = "Last name is required";
        String lastName = "lastName";
        String lastNamelongValidation = "Last name must be at least 2 characters long";
        VeterinariansPage veterinariansPage = new VeterinariansPage();
        veterinariansPage.openPage()
                .clickAddBtn()
                .setLastName("=");
        NewVeterPage newVeterPage = new NewVeterPage();
        assertThat(newVeterPage.helpBlockGetText(lastName)).isEqualTo(lastNamelongValidation);
        newVeterPage.clearLastName();
        assertThat(newVeterPage.helpBlockGetText(lastName)).isEqualTo(requiredLast);
    }

    @Test(description = "Adding vet without type")
    @Story("Adding vet without type")
    @Severity(SeverityLevel.TRIVIAL)
    @TmsLink("vets.com")
    public void createVetWithoutType(){
        VeterinariansPage veterinariansPage = new VeterinariansPage();
        veterinariansPage.openPage();
        ElementsCollection before = veterinariansPage.veterinariansList();
        int beforeSize = before.size();
        NewVeterPage newVeterPage = veterinariansPage.clickAddBtn();
        newVeterPage.setFirstName("Sponge");
        newVeterPage.setLastName("Bob");
        veterinariansPage = newVeterPage.saveVetButtonClick();
        ElementsCollection after = veterinariansPage.veterinariansList();
        veterinariansPage.openPage();
        assertThat(beforeSize+1).isEqualTo(after.size());
    }

    @Test(description = "Returning to the home page")
    @Severity(SeverityLevel.MINOR)
    @Story("Returning to the home page")
    @TmsLink("vets.com")
    public void homeButtonTest() {
        VeterinariansPage veterinariansPage = new VeterinariansPage();
        veterinariansPage.openPage()
                .assertUrl(url());
    }
}