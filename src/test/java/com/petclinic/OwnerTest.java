package com.petclinic;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class OwnerTest {
    static {
        Configuration.baseUrl = "http://localhost:8000/petclinic";
        //Configuration.timeout = 7000;
        //Configuration.browser = "firefox";
    }
    @Test
    public void addNewOwnerTest(){
        open(Configuration.baseUrl);
        open("/owners/add");
        $("h2").shouldHave(Condition.text("New Owner"));

        $(By.id("firstName")).setValue("Selenide");
        $(By.id("lastName")).setValue("User");
        $(By.id("address")).setValue("Street");
        $(By.id("city")).setValue("Dniptro");
        $(By.id("telephone")).setValue("1234567890");

        //$("[type='submit']").shouldBe(Condition.enabled).click();

        //$("h2").shouldHave(Condition.text("Owners"));
    }
    @Test
    public void addPageObjectTest(){
        open("/owners/add");
        NewOwnerPage newOwnerPage = new NewOwnerPage();
        newOwnerPage
                .setFirstName("11")
        .setLastName("Last")
        .setAddress("asd")
        .setCity("sdf")
        .setPhone("1234567890")
        .cliackAddBtn();
    }
}
