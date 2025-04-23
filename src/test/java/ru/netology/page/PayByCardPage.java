package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PayByCardPage {
    private final SelenideElement payByCardTitle = $(byText("Оплата по карте"));
    public final SelenideElement cardNumber = $$("input__inner").find(Condition.exactText("Номер карты"));
    public final SelenideElement cardMonth = $$("input__inner").find(Condition.exactText("Месяц"));
    public final SelenideElement cardYear = $$("input__inner").find(Condition.exactText("Год"));
    public final SelenideElement cardHolder = $$("input__inner").find(Condition.exactText("Владелец"));
    public final SelenideElement cardCVV = $$("input__inner").find(Condition.exactText("CVC/CVV"));


    public PayByCardPage() {
        payByCardTitle.shouldHave(text("Оплата по карте")).shouldBe(Condition.visible);
    }



//    pageTitle.shouldHave(text("Личный кабинет")).shouldBe(Condition.visible);
}
