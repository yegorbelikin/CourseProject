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
    public final SelenideElement successTitle = $(byText("Операция одобрена Банком."));
    public final SelenideElement errorField = $(".input_invalid .input__sub");
    public final SelenideElement errorBankRefused = $(byText("Ошибка! Банк отказал в проведении операции."));
//    public final SelenideElement errorMonth = $(".input_invalid .input__sub");
//    public final SelenideElement errorYear = $(".input_invalid .input__sub");
//    public final SelenideElement errorHolder = $(".input_invalid .input__sub");


    public void approveHolder (DataHelper.Holder holder) {
        cardNumber.setValue(holder.getNumber());
        cardMonth.setValue(holder.getMonth());
        cardYear.setValue(holder.getYear());
        cardHolder.setValue(holder.getHolder());
        cardCVV.setValue(holder.getCVV());
        sendButton.click();

    }

    public void holderWithoutCard (DataHelper.Holder holder) {
        cardMonth.setValue(holder.getMonth());
        cardYear.setValue(holder.getYear());
        cardHolder.setValue(holder.getHolder());
        cardCVV.setValue(holder.getCVV());
    }

    public void holderWithoutMonth (DataHelper.Holder holder) {
        cardNumber.setValue(holder.getNumber());
        cardYear.setValue(holder.getYear());
        cardHolder.setValue(holder.getHolder());
        cardCVV.setValue(holder.getCVV());
    }

    public void holderWithoutYear (DataHelper.Holder holder) {
        cardNumber.setValue(holder.getNumber());
        cardMonth.setValue(holder.getMonth());
        cardHolder.setValue(holder.getHolder());
        cardCVV.setValue(holder.getCVV());
    }

    public void holderWithoutHolder (DataHelper.Holder holder) {
        cardNumber.setValue(holder.getNumber());
        cardMonth.setValue(holder.getMonth());
        cardYear.setValue(holder.getYear());
        cardCVV.setValue(holder.getCVV());
    }

    public void holderWithoutCVV (DataHelper.Holder holder) {
        cardNumber.setValue(holder.getNumber());
        cardMonth.setValue(holder.getMonth());
        cardYear.setValue(holder.getYear());
        cardHolder.setValue(holder.getHolder());
    }

    public void holderWithoutYearAndMonth (DataHelper.Holder holder) {
        cardNumber.setValue(holder.getNumber());
        cardHolder.setValue(holder.getHolder());
        cardCVV.setValue(holder.getCVV());
    }
}


