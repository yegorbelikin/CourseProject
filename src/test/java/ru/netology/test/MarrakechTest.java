package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;


import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.HomePage;
import ru.netology.page.PayByCardPage;

import java.time.Duration;

import static ru.netology.data.DataHelper.validHolder;

public class MarrakechTest {
    HomePage homePage;
    PayByCardPage payByCardPage;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        homePage = Selenide.open("http://localhost:8080", HomePage.class);
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

                                          //1.2 Заполнение формы валидными данными

    @Test
    void successfulSend() {
        SQLHelper.cleanDatabase();
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(holder);
        payByCardPage.successTitle.shouldHave(Condition.text("Операция одобрена Банком."), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
        var approvedInOrder = SQLHelper.searchApprovedInOrder();
        int expectedOrder = 1;
        Assertions.assertEquals(expectedOrder, approvedInOrder);
    }

                                    //    1.3 Сценарии заполнения поля Номер карты невалидными данными


    @Test
    void sendWithCardNumberLat() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutCard(holder);
        payByCardPage.cardNumber.setValue(DataHelper.randomHolderName("en"));
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);

    }

    @Test
    void sendWithCardNumberKir() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutCard(holder);
        payByCardPage.cardNumber.setValue("НомерКарты");
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithCardNumberSpecial() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutCard(holder);
        payByCardPage.cardNumber.setValue("@#$%");
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithCardNumberThreeDigits() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutCard(holder);
        payByCardPage.cardNumber.setValue("123");
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithCardNumberSeventeenDigits() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutCard(holder);
        payByCardPage.cardNumber.setValue(DataHelper.approvedCard().getNumber() + "1");
        payByCardPage.sendButton.click();
        payByCardPage.successTitle.shouldHave(Condition.text("Операция одобрена Банком."), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithCardNumberRandomDigits() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutCard(holder);
        payByCardPage.cardNumber.setValue(DataHelper.randomNumber());
        payByCardPage.sendButton.click();
        payByCardPage.errorBankRefused.shouldHave(Condition.text("Ошибка! Банк отказал в проведении операции."), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithoutCardNumber() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutCard(holder);
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithCardNumberDeclinedCard() {
        SQLHelper.cleanDatabase();
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutCard(holder);
        payByCardPage.cardNumber.setValue(DataHelper.declinedCard().getNumber());
        payByCardPage.sendButton.click();
        payByCardPage.errorBankRefused.shouldHave(Condition.text("Ошибка! Банк отказал в проведении операции."), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
        var declinedInOrder = SQLHelper.searchDeclinedInOrder();
        int expectedOrder = 1;
        Assertions.assertEquals(expectedOrder, declinedInOrder);
    }

                                        //1.4 Сценарии заполнения поля Месяц невалидными данными




    @Test
    void sendWithMonthLat() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutMonth(holder);
        payByCardPage.cardMonth.setValue(DataHelper.randomHolderName("en"));
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithMonthKir() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutMonth(holder);
        payByCardPage.cardMonth.setValue("Месяц");
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithMonthSpecial() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutMonth(holder);
        payByCardPage.cardMonth.setValue("@#");
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithMonthOneDigit() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutMonth(holder);
        payByCardPage.cardMonth.setValue("1");
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithMonthThreeDigits() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutMonth(holder);
        payByCardPage.cardMonth.setValue("123");
        payByCardPage.sendButton.click();
        payByCardPage.successTitle.shouldHave(Condition.text("Операция одобрена Банком."), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithoutMonth() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutMonth(holder);
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithMonthLater() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutMonth(holder);
        payByCardPage.cardMonth.setValue("13");
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Неверно указан срок действия карты"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithMonthEarly() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutMonth(holder);
        payByCardPage.cardMonth.setValue(DataHelper.generateEarlyMonth(1));
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Неверно указан срок действия карты"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

                                //    1.5 Сценарии заполнения поля Год невалидными данными


    @Test
    void sendWithYearLat() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutYear(holder);
        payByCardPage.cardYear.setValue(DataHelper.randomHolderName("en"));
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithYearKir() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutYear(holder);
        payByCardPage.cardYear.setValue("Год");
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithYearSpecial() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutYear(holder);
        payByCardPage.cardYear.setValue("#$");
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithYearOneDigit() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutYear(holder);
        payByCardPage.cardYear.setValue("1");
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithYearThreeDigits() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutYear(holder);
        payByCardPage.cardYear.setValue(DataHelper.generateYear() + "1");
        payByCardPage.sendButton.click();
        payByCardPage.successTitle.shouldHave(Condition.text("Операция одобрена Банком."), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithoutYear() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutYear(holder);
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }


    @Test
    void sendWithYearEarly() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutYear(holder);
        payByCardPage.cardYear.setValue(DataHelper.generateEarlyYear(2));
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Истёк срок действия карты"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }


                                //1.6 Сценарии заполнения поля Владелец невалидными данными

    @Test
    void sendWithHolderKir() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutHolder(holder);
        payByCardPage.cardHolder.setValue("Владелец Карты");
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithHolderSpecial() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutHolder(holder);
        payByCardPage.cardHolder.setValue("!@#$%");
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithoutHolder() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutHolder(holder);
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Поле обязательно для заполнения"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithHolderOneWord() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutHolder(holder);
        payByCardPage.cardHolder.setValue(DataHelper.randomHolderName("en"));
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithHolderFiveWords() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutHolder(holder);
        payByCardPage.cardHolder.setValue(DataHelper.randomHolderWithFiveWords("en"));
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithHolderHyphen() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutHolder(holder);
        payByCardPage.cardHolder.setValue(DataHelper.randomHolderWithHyphen("en"));
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

                            //    1.7 Сценарии заполнения поля CVV невалидными данными

    @Test
    void sendWithCVVLat() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutCVV(holder);
        payByCardPage.cardCVV.setValue(DataHelper.randomHolderName("en"));
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithCVVKir() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutCVV(holder);
        payByCardPage.cardCVV.setValue("СВВ");
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithCVVSpecial() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutCVV(holder);
        payByCardPage.cardCVV.setValue("!@#");
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithCVVOneDigit() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutCVV(holder);
        payByCardPage.cardCVV.setValue("1");
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithCVVFourDigit() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutCVV(holder);
        payByCardPage.cardCVV.setValue(DataHelper.randomCVV() + "1");
        payByCardPage.sendButton.click();
        payByCardPage.successTitle.shouldHave(Condition.text("Операция одобрена Банком."), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void sendWithoutCVV() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutCVV(holder);
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

                        //    1.8 Сценарии заполнения полей Месяц и Год невалидными данными

    @Test
    void sendWithYearAndMonthEarly() {
        var holder = validHolder("en");
        var payByCardPage = homePage.payByCard();
        payByCardPage.holderWithoutYearAndMonth(holder);
        payByCardPage.cardYear.setValue(DataHelper.generateEarlyYear(1));
        payByCardPage.cardMonth.setValue(DataHelper.generateLaterMonth(1));
        payByCardPage.sendButton.click();
        payByCardPage.errorField.shouldHave(Condition.text("Истёк срок действия карты"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }


}
