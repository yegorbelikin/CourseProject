package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.netology.data.DataHelper;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PayByCardPage {

    public final SelenideElement cardNumber = $("[placeholder='0000 0000 0000 0000']");
    public final SelenideElement cardMonth = $("[placeholder='08']");
    public final SelenideElement cardYear = $("[placeholder='22']");
    public final SelenideElement cardHolder = $("div:nth-child(3) .input__control");
    public final SelenideElement cardCVV = $("[placeholder='999']");
    public final SelenideElement sendButton = $("div:nth-child(4) button");


    public void approveHolder (DataHelper.Holder holder) {
        cardNumber.setValue(holder.getNumber());
        cardMonth.setValue(holder.getMonth());
        cardYear.setValue(holder.getYear());
        cardHolder.setValue(holder.getHolder());
        cardCVV.setValue(holder.getCVV());
        sendButton.click();
        //        shouldBe(Condition.visible);
    }

}



//    pageTitle.shouldHave(text("Личный кабинет")).shouldBe(Condition.visible);
//    public void approveHolder () {
//
//        var approvedNumber = DataHelper.approvedCard();
//        var randomCVV = DataHelper.randomCVV();
//        var randomMonth = DataHelper.generateMonth();
//        var randomYear = DataHelper.generateYear();
//        var randomHolder = DataHelper.randomHolder("en");
//
//        cardNumber.setValue(approvedNumber.getNumber());
//        cardMonth.setValue(randomMonth);
//        cardYear.setValue(randomYear);
//        cardHolder.setValue(randomHolder);
//        cardCVV.setValue(randomCVV);
//        sendButton.click();
//
//    }