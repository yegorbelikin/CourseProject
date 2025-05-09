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

    public static String randomHolderName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().firstName();
    }

    public static String randomHolderWithHyphen(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().lastName() + "-" + faker.name().firstName();
    }

    public static String randomHolderWithFiveWords(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().lastName() + " " + faker.name().firstName() + " " + faker.name().firstName() + " " + faker.name().firstName() + " " + faker.name().firstName();
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

    public static String generateEarlyYear(int shift) {
        return LocalDate.now().minusYears(shift).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateEarlyMonth(int shift) {
        return LocalDate.now().minusMonths(shift).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateLaterMonth(int shift) {
        return LocalDate.now().plusMonths(shift).format(DateTimeFormatter.ofPattern("MM"));
    }


    public static Holder validHolder(String locale) {
        return new Holder(approvedCard().Number, generateYear(), generateMonth(), randomHolder(locale), randomCVV());
    }

    public static Holder declinedHolder(String locale) {
        return new Holder(declinedCard().Number, generateYear(), generateMonth(), randomHolder(locale), randomCVV());
    }

    @Value
    public static class CardNumber {
        String Number;
    }

    @Value
    public static class Holder {
        String Number;
        String Year;
        String Month;
        String Holder;
        String CVV;

    }








}
