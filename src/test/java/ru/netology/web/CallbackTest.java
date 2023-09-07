package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CallbackTest {
    @Test
    void shouldSubmitForm() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+71234567890");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".paragraph").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSubmitFormNameWithHyphen() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иванов Иван-Василий");
        $("[data-test-id=phone] input").setValue("+71234567890");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".paragraph").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldFailedTestEmptyFieldName() {
        open("http://localhost:9999");
        $("[data-test-id=phone] input").setValue("+71234567890");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_invalid").find(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldFailedTestNameInEnglish() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Ivanov Ivan");
        $("[data-test-id=phone] input").setValue("+71234567890");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_invalid").find(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldFailedTestNameWithNumber() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иванов Иван1");
        $("[data-test-id=phone] input").setValue("+71234567890");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_invalid").find(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldFailedTestNameWithSpecialSymbol() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иванов Иван!");
        $("[data-test-id=phone] input").setValue("+71234567890");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_invalid").find(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldFailedTestEmptyFieldNumber() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_invalid").find(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldFailedTestFieldNumberWithLetter() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+7ИвановИванИ");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_invalid").find(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldFailedTestFieldNumberWithoutPlus() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("71234567890");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_invalid").find(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldFailedTestFieldNumberMore11() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+712345678901");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_invalid").find(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldFailedTestFieldNumberLess11() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+7123456789");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_invalid").find(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}
