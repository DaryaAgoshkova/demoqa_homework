package com.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class PracticeForm {
    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browser = "chrome";
        Configuration.browserSize = "760x840";
    }


    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Daria");
        $("#lastName").setValue("Agoshkova");
        $("#userEmail").setValue("daryatester@gmail.com");
        $("[for='gender-radio-2']").click();
        $("#userNumber").setValue("9110276307");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(5);
        $(".react-datepicker__year-select").selectOptionContainingText("1988");
        $(".react-datepicker__day--005").click();
        $("#subjectsInput").setValue("Computer Science").pressEnter();
        $("#subjectsInput").setValue("English").pressEnter();
        $("#subjectsInput").setValue("Arts").pressEnter();
        $("[for='hobbies-checkbox-2']").click();
        $("[for='hobbies-checkbox-3']").click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/Picture.jpg"));
        $("#currentAddress").setValue("Russian Federation, Saint Petersburg, Leo Tolstoy, 7");
        $("#state").click();
        $(byText("NCR")).click();
        $("#city").click();
        $(byText("Gurgaon")).click();
        $("#submit").click();

        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text("Daria Agoshkova"),
                text("daryatester@gmail.com"),
                text("Female"),
                text("9110276307"),
                text("05 June,1988"),
                text("Computer Science, English, Arts"),
                text("Reading, Music"),
                text("Picture.jpg"),
                text("Russian Federation, Saint Petersburg, Leo Tolstoy, 7"),
                text("NCR Gurgaon")
        );
    }
}
