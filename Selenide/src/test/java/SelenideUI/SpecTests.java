package SelenideUI;

import Owner.ApiTestPreconditions;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("Petclinic")
@Feature("Specialties")
public class SpecTests {

    @Test(description = "Checking Spec page")
    @Story("Checking the URL")
    @Severity(SeverityLevel.MINOR)
    @TmsLink("specialty.com")
    public void pageCheck(){
        SpecPage specPage = new SpecPage();
        specPage.openPage()
                .assertUrl(url());
    }

    @Test(description = "Adding a new Spec")
    @Story("Adding a new Spec")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("specialty.com")
    public void specialtyAddingTest() {
        String spec = "lor";
        ApiTestPreconditions apiPrec = new ApiTestPreconditions();
        apiPrec.setUp();
        apiPrec.specCreationPrec();
        SpecPage specPage = new SpecPage();
        specPage.openPage();
        refresh();
        assertThat(specPage.specList()).isEqualTo(spec);
    }

    @Test(description = "Adding an empty Spec")
    @Severity(SeverityLevel.MINOR)
    @Story("Adding pet without name and specialty")
    @TmsLink("specialty.com")
    public void addingEmptySpec(){
        SpecPage specPage = new SpecPage();
        specPage.openPage();
        ElementsCollection before = specPage.specialists();
        int beforeSize = before.size();
        specPage.addBtn();
        specPage.saveBtn();
        ElementsCollection after = specPage.specialists();
        assertThat(beforeSize).isEqualTo(after.size());
    }

    @Test(description = "Delete the new Spec")
    @Severity(SeverityLevel.NORMAL)
    @Story("Deleting of the Spec")
    @TmsLink("specialty.com")
    public void specDeleteTest() {
        String spec = "lor";
        SpecPage specPage = new SpecPage();
        ElementsCollection before = specPage.specialists();
        int beforeSize = before.size();
        ApiTestPreconditions apiPrec = new ApiTestPreconditions();
        apiPrec.setUp();
        apiPrec.specCreationPrec();
        specPage.openPage();
        assertThat(specPage.specList()).isEqualTo(spec);
        ElementsCollection afterAdding = specPage.specialists();
        assertThat(beforeSize+1).isEqualTo(afterAdding.size());
        specPage.deleteLast();
        refresh();
        ElementsCollection afterDeleting = specPage.specialists();
        assertThat(beforeSize).isEqualTo(afterDeleting.size());
    }

    @Test(description = "Returning to the home page")
    @Severity(SeverityLevel.MINOR)
    @Story("Returning to the home page")
    @TmsLink("specialty.com")
    public void homeButtonTest() {
        SpecPage specPage = new SpecPage();
        specPage.openPage()
                .homeBtn();
        assertThat(url()).isEqualTo(Configuration.baseUrl + "/welcome");
    }
}