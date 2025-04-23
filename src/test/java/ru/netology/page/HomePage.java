package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HomePage {


    public final SelenideElement cardButton = $$("button").find(Condition.exactText("Купить"));
    public final SelenideElement payByCardTitle = $(byText("Оплата по карте"));
    public final SelenideElement cardNumber = $("[placeholder='0000 0000 0000 0000']");
    public final SelenideElement cardMonth = $("[placeholder='08']");
    public final SelenideElement cardYear = $("[placeholder='22']");
    public final SelenideElement cardHolder = $$(".input__top").findBy(Condition.text("Владелец"));
    public final SelenideElement cardCVV = $("[placeholder='999']");


    public void homePage () {
        cardButton.click();
        payByCardTitle.shouldBe(Condition.visible);
    }

    public void validInfo () {
        cardNumber.setValue("1111");

    }


}
