package com.petclinic;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class NewOwnerPage {
    public NewOwnerPage setFirstName(String name){
        $(By.id("firstName")).setValue(name);
        return this;
    }
    public NewOwnerPage setLastName(String name){
        $(By.id("lastName")).setValue(name);
        return this;
    }
public NewOwnerPage setAddress(String name){
        $(By.id("address")).setValue(name);
        return this;
    }
public NewOwnerPage setCity(String name){
        $(By.id("city")).setValue(name);
        return this;
    }
public NewOwnerPage setPhone(String name){
        $(By.id("telephone")).setValue(name);
        return this;
    }
        public void cliackAddBtn(){
            $("[type='submit']").shouldBe(Condition.enabled).click();
    }
}
//
//    $(By.id("firstName")).setValue("Selenide");
//        $(By.id("lastName")).setValue("User");
//        $(By.id("address")).setValue("Street");
//        $(By.id("city")).setValue("Dniptro");
//        $(By.id("telephone")).setValue("1234567890");