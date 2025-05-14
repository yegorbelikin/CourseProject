package ru.netology.test;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.HomePage;
import ru.netology.page.PayByCardPage;


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
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.validHolder("en"));
        payByCardPage.successSend();
        var approvedInOrder = SQLHelper.searchApprovedInOrder();
        int expectedOrder = 1;
        Assertions.assertEquals(expectedOrder, approvedInOrder);
    }

                                    //    1.3 Сценарии заполнения поля Номер карты невалидными данными


    @Test
    void sendWithCardNumberLat() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithCardNumberLat("en"));
        payByCardPage.findErrorInNumber("Неверный формат");

    }

    @Test
    void sendWithCardNumberKir() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithCardNumberKir("en"));
        payByCardPage.findErrorInNumber("Неверный формат");
    }

    @Test
    void sendWithCardNumberSpecial() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithCardNumberSpecial("en"));
        payByCardPage.findErrorInNumber("Неверный формат");
    }

    @Test
    void sendWithCardNumberThreeDigits() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithCardNumberThreeDigits("en"));
        payByCardPage.findErrorInNumber("Неверный формат");
    }

    @Test
    void sendWithCardNumberSeventeenDigits() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithCardNumberSeventeenDigits("en"));
        payByCardPage.successSend();
    }

    @Test
    void sendWithCardNumberRandomDigits() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithCardNumberRandomDigits("en"));
        payByCardPage.findErrorInBank();
    }

    @Test
    void sendWithoutCardNumber() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithoutCardNumber("en"));
        payByCardPage.findErrorInNumber("Неверный формат");
    }

    @Test
    void sendWithCardNumberDeclinedCard() {
        SQLHelper.cleanDatabase();
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.declinedHolder("en"));
        payByCardPage.findErrorInBank();
        var declinedInOrder = SQLHelper.searchDeclinedInOrder();
        int expectedOrder = 1;
        Assertions.assertEquals(expectedOrder, declinedInOrder);
    }

                                        //1.4 Сценарии заполнения поля Месяц невалидными данными




    @Test
    void sendWithMonthLat() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithMonthLat("en"));
        payByCardPage.findErrorInMonth("Неверный формат");
    }

    @Test
    void sendWithMonthKir() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithMonthKir("en"));
        payByCardPage.findErrorInMonth("Неверный формат");
    }

    @Test
    void sendWithMonthSpecial() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithMonthSpecial("en"));
        payByCardPage.findErrorInMonth("Неверный формат");
    }

    @Test
    void sendWithMonthOneDigit() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithMonthOneDigit("en"));
        payByCardPage.findErrorInMonth("Неверный формат");
    }

    @Test
    void sendWithMonthThreeDigits() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithMonthThreeDigits("en"));
        payByCardPage.successSend();
    }

    @Test
    void sendWithoutMonth() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithoutMonth("en"));
        payByCardPage.findErrorInMonth("Неверный формат");
    }

    @Test
    void sendWithMonthLater() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithMonthLater("en"));
        payByCardPage.findErrorInMonth("Неверно указан срок действия карты");
    }

    @Test
    void sendWithMonthEarly() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithMonthEarly("en"));
        payByCardPage.findErrorInMonth("Неверно указан срок действия карты");
    }

                                //    1.5 Сценарии заполнения поля Год невалидными данными


    @Test
    void sendWithYearLat() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithYearLat("en"));
        payByCardPage.findErrorInYear("Неверный формат");
    }

    @Test
    void sendWithYearKir() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithYearKir("en"));
        payByCardPage.findErrorInYear("Неверный формат");
    }

    @Test
    void sendWithYearSpecial() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithYearSpecial("en"));
        payByCardPage.findErrorInYear("Неверный формат");
    }

    @Test
    void sendWithYearOneDigit() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithYearOneDigit("en"));
        payByCardPage.findErrorInYear("Неверный формат");
    }

    @Test
    void sendWithYearThreeDigits() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithYearThreeDigits("en"));
        payByCardPage.successSend();
    }

    @Test
    void sendWithoutYear() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithoutYear("en"));
        payByCardPage.findErrorInYear("Неверный формат");
    }


    @Test
    void sendWithYearEarly() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithYearEarly("en"));
        payByCardPage.findErrorInYear("Истёк срок действия карты");
    }


                                //1.6 Сценарии заполнения поля Владелец невалидными данными

    @Test
    void sendWithHolderKir() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithHolderKir("en"));
        payByCardPage.findErrorInHolder("Неверный формат");
    }

    @Test
    void sendWithHolderSpecial() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithHolderSpecial("en"));
        payByCardPage.findErrorInHolder("Неверный формат");
    }

    @Test
    void sendWithoutHolder() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithoutHolder("en"));
        payByCardPage.findErrorInHolder("Поле обязательно для заполнения");
    }

    @Test
    void sendWithHolderOneWord() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithHolderOneWord("en"));
        payByCardPage.findErrorInHolder("Неверный формат");
    }

    @Test
    void sendWithHolderFiveWords() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithHolderFiveWords("en"));
        payByCardPage.findErrorInHolder("Неверный формат");
    }

    @Test
    void sendWithHolderHyphen() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithHolderHyphen("en"));
        payByCardPage.findErrorInHolder("Неверный формат");
    }

                            //    1.7 Сценарии заполнения поля CVV невалидными данными

    @Test
    void sendWithCVVLat() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithCVVLat("en"));
        payByCardPage.findErrorInCVV("Неверный формат");
    }

    @Test
    void sendWithCVVKir() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithCVVKir("en"));
        payByCardPage.findErrorInCVV("Неверный формат");
    }

    @Test
    void sendWithCVVSpecial() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithCVVSpecial("en"));
        payByCardPage.findErrorInCVV("Неверный формат");
    }

    @Test
    void sendWithCVVOneDigit() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithCVVOneDigit("en"));
        payByCardPage.findErrorInCVV("Неверный формат");
    }

    @Test
    void sendWithCVVFourDigit() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithCVVFourDigits("en"));
        payByCardPage.successSend();
    }

    @Test
    void sendWithoutCVV() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithoutCVV("en"));
        payByCardPage.findErrorInCVV("Неверный формат");
    }

                        //    1.8 Сценарии заполнения полей Месяц и Год невалидными данными

    @Test
    void sendWithYearAndMonthEarly() {
        var payByCardPage = homePage.payByCard();
        payByCardPage.approveHolder(DataHelper.holderWithYearAndMonthEarly("en"));
        payByCardPage.findErrorInMonth("Истёк срок действия карты");
    }


}
