package SelenideUI;

import API.ApiTestPreconditions;
import SelenideUI.Pages.PetTypePage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("Petclinic")
@Feature("PetTypes")
public class PetTests {
    ApiTestPreconditions apiPrec = new ApiTestPreconditions();

    @Test(description = "Checking Pets page")
    @Story("Checking the URL")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("pettype.com")
    public void pageCheck(){
        PetTypePage petTypePage = new PetTypePage();
        petTypePage.openPage()
                .assertUrl(url());
    }

    @Test(description = "Adding a new Pet")
    @Story("Adding a new pet")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("pettype.com")
    public void petTypeAddTest() {
        String name = "Duck";
        apiPrec.setUp();
        apiPrec.petTypeadding();
        PetTypePage petTypePage = new PetTypePage();
        petTypePage.openPage();
        assertThat(petTypePage.typeList()).isEqualTo(name);
    }

    @Test(description = "Adding an empty Pet")
    @Severity(SeverityLevel.MINOR)
    @Story("Adding pet without name and type")
    @TmsLink("pettype.com")
    public void addingEmptyPet(){
        PetTypePage petTypePage = new PetTypePage();
        petTypePage.openPage();
        int beforeSize = petTypePage.petsList().size();
        petTypePage.addBtn();
        petTypePage.saveBtn();
        ElementsCollection after = petTypePage.petsList();
        assertThat(beforeSize).isEqualTo(after.size());
    }

    @Test(description = "Delete the new petType")
    @Severity(SeverityLevel.NORMAL)
    @Story("Deleting of the pettype")
    @TmsLink("pettype.com")
    public void petTypeDeleteTest() {
        PetTypePage petTypePage = new PetTypePage();
        petTypePage.openPage();
        ElementsCollection before = petTypePage.petsList();
        int beforeSize = before.size();
        ApiTestPreconditions apiPrec = new ApiTestPreconditions();
        apiPrec.setUp();
        apiPrec.petTypeadding();
        petTypePage.openPage();
        ElementsCollection afterAdding = petTypePage.petsList();
        assertThat(beforeSize+1).isEqualTo(afterAdding.size());
        petTypePage.deleteLast();
        refresh();
        ElementsCollection afterDeleting = petTypePage.petsList();
        assertThat(beforeSize).isEqualTo(afterDeleting.size());
    }

    @Test(description = "Returning to the home page")
    @Severity(SeverityLevel.MINOR)
    @Story("Returning to the home page")
    @TmsLink("pettype.com")
    public void homeButtonTest() {
        PetTypePage petTypePage = new PetTypePage();
        petTypePage.openPage()
                .homeBtn();
        assertThat(url()).isEqualTo(Configuration.baseUrl + "/welcome");
    }
}