package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HomePage {


    public final SelenideElement buyButton = $$("button").find(Condition.exactText("Купить"));
    public final SelenideElement payByCardTitle = $(byText("Оплата по карте"));



    public PayByCardPage payByCard () {
        buyButton.click();
        payByCardTitle.shouldBe(Condition.visible);
        return new PayByCardPage();
    }




}
