package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.HomePage;
import ru.netology.page.PayByCardPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static ru.netology.data.DataHelper.validHolder;

public class MarrakechTest {
    HomePage homePage;
    PayByCardPage payByCardPage;

//    DataHelper.Holder holder = DataHelper.validHolder("en");






    @BeforeEach
    void setUp() {
        homePage = Selenide.open("http://localhost:8080", HomePage.class);
        homePage.payByCard();

    }

    @Test
    void successfulSend() {
        var holder = validHolder("en");
        payByCardPage.approveHolder(holder);

    }



    @Test
    void successfulSend1() {
        var approvedNumber = DataHelper.approvedCard();
        var randomCVV = DataHelper.randomCVV();
        var cardMonth = DataHelper.generateMonth();
        var cardYear = DataHelper.generateYear();
        var cardHolder = DataHelper.randomHolder("en");

        payByCardPage.cardNumber.setValue(approvedNumber.getNumber());
        payByCardPage.cardMonth.setValue(cardMonth);
        payByCardPage.cardYear.setValue(cardYear);
        payByCardPage.cardHolder.setValue(cardHolder);
        payByCardPage.cardCVV.setValue(randomCVV);
        payByCardPage.sendButton.click();




    }




}
