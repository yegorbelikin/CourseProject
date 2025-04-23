package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private DataHelper(){
    }

    private static final Faker faker = new Faker(new Locale("en"));

    public static String randomHolder(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String randomNumber() {
        return faker.numerify("################");

    }

    public static String randomCVV() {
        return faker.numerify("###");

    }

    public static CardNumber approvedCard() {
        return new CardNumber ("1111 2222 3333 4444");
    }

    public static CardNumber declinedCard() {
        return new CardNumber ("5555 6666 7777 8888");
    }

    public static String generateMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }


//    public static String generateDate(int shift) {
//        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
//    }



//    public static AuthInfo getAuthInfo() {
//        return new AuthInfo ("vasya", "qwerty123");
//    }



    @Value
    public static class CardNumber {
        String Number;
    }



}
