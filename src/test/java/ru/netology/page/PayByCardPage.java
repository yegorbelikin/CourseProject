package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.netology.data.DataHelper;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PayByCardPage {

    private final SelenideElement cardNumber = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement cardMonth = $("[placeholder='08']");
    private final SelenideElement cardYear = $("[placeholder='22']");
    private final SelenideElement cardHolder = $("div:nth-child(3) .input__control");
    private final SelenideElement cardCVV = $("[placeholder='999']");
    private final SelenideElement sendButton = $("div:nth-child(4) button");
    private final SelenideElement successTitle = $(byText("Операция одобрена Банком."));
    private final SelenideElement errorField = $(".input_invalid .input__sub");
    private final SelenideElement errorBankRefused = $(byText("Ошибка! Банк отказал в проведении операции."));



    public void approveHolder (DataHelper.Holder holder) {
        cardNumber.setValue(holder.getNumber());
        cardMonth.setValue(holder.getMonth());
        cardYear.setValue(holder.getYear());
        cardHolder.setValue(holder.getHolder());
        cardCVV.setValue(holder.getCVV());
        sendButton.click();

    }


    public void findErrorInField (String errorText){
        errorField.shouldHave(Condition.text(errorText), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);;
    }

    public void findErrorInBank (){
        errorBankRefused.shouldHave(Condition.text("Ошибка! Банк отказал в проведении операции."), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);;
    }

    public void successSend (){
        successTitle.shouldHave(Condition.text("Операция одобрена Банком."), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);;
    }



}


