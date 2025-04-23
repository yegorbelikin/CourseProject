package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.HomePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MarrakechTest {
    HomePage homePage;


    @BeforeEach
    void setUp() {
        homePage = Selenide.open("http://localhost:8080", HomePage.class);
        homePage.homePage();
    }




    @Test
    void successfulLogin() {
        homePage.cardNumber.setValue("25");
        homePage.cardMonth.setValue("12");
        homePage.cardYear.setValue("25");
        homePage.cardHolder.setValue("ivan");
        homePage.cardCVV.setValue("123");




    }

}
