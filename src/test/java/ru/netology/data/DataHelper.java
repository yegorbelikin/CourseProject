package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class DataHelper {
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
        return new Holder(approvedCard().Number, generateMonth(), generateYear(), randomHolder(locale), randomCVV());
    }

    public static Holder holderWithCardNumberLat(String locale) {
        return new Holder(randomHolderName("en"), generateMonth(), generateYear(), randomHolder(locale), randomCVV());
    }

    public static Holder holderWithCardNumberKir(String locale) {
        return new Holder(randomHolderName("ru"), generateMonth(), generateYear(), randomHolder(locale), randomCVV());
    }

    public static Holder holderWithCardNumberSpecial(String locale) {
        return new Holder("@#$", generateMonth(), generateYear(), randomHolder(locale), randomCVV());
    }

    public static Holder holderWithCardNumberThreeDigits(String locale) {
        return new Holder("123", generateMonth(), generateYear(), randomHolder(locale), randomCVV());
    }

    public static Holder holderWithCardNumberSeventeenDigits(String locale) {
        return new Holder(approvedCard().getNumber() + "1", generateMonth(), generateYear(), randomHolder(locale), randomCVV());
    }

    public static Holder holderWithCardNumberRandomDigits(String locale) {
        return new Holder(randomNumber(), generateMonth(), generateYear(), randomHolder(locale), randomCVV());
    }

    public static Holder holderWithoutCardNumber(String locale) {
        return new Holder(null, generateMonth(), generateYear(), randomHolder(locale), randomCVV());
    }

    public static Holder declinedHolder(String locale) {
        return new Holder(declinedCard().Number, generateMonth(), generateYear(), randomHolder(locale), randomCVV());
    }

    public static Holder holderWithMonthLat(String locale) {
        return new Holder(approvedCard().Number, randomHolderName("en"), generateYear(),  randomHolder(locale), randomCVV());
    }

    public static Holder holderWithMonthKir(String locale) {
        return new Holder(approvedCard().Number, randomHolderName("ru"), generateYear(),  randomHolder(locale), randomCVV());
    }

    public static Holder holderWithMonthSpecial(String locale) {
        return new Holder(approvedCard().Number, "#$", generateYear(),  randomHolder(locale), randomCVV());
    }

    public static Holder holderWithMonthOneDigit(String locale) {
        return new Holder(approvedCard().Number, "1", generateYear(),  randomHolder(locale), randomCVV());
    }

    public static Holder holderWithMonthThreeDigits(String locale) {
        return new Holder(approvedCard().Number, "123", generateYear(),  randomHolder(locale), randomCVV());
    }

    public static Holder holderWithoutMonth(String locale) {
        return new Holder(approvedCard().Number, null, generateYear(),  randomHolder(locale), randomCVV());
    }

    public static Holder holderWithMonthLater(String locale) {
        return new Holder(approvedCard().Number, "13", generateYear(),  randomHolder(locale), randomCVV());
    }

    public static Holder holderWithMonthEarly(String locale) {
        return new Holder(approvedCard().Number, generateEarlyMonth(1), generateYear(),  randomHolder(locale), randomCVV());
    }

    public static Holder holderWithYearLat(String locale) {
        return new Holder(approvedCard().Number, generateMonth(), randomHolderName("en"),  randomHolder(locale), randomCVV());
    }

    public static Holder holderWithYearKir(String locale) {
        return new Holder(approvedCard().Number, generateMonth(), randomHolderName("ru"), randomHolder(locale), randomCVV());
    }

    public static Holder holderWithYearSpecial(String locale) {
        return new Holder(approvedCard().Number, generateMonth(), "#$", randomHolder(locale), randomCVV());
    }

    public static Holder holderWithYearOneDigit(String locale) {
        return new Holder(approvedCard().Number, generateMonth(), "1", randomHolder(locale), randomCVV());
    }

    public static Holder holderWithYearThreeDigits(String locale) {
        return new Holder(approvedCard().Number, generateMonth(), generateYear() + "1", randomHolder(locale), randomCVV());
    }

    public static Holder holderWithoutYear(String locale) {
        return new Holder(approvedCard().Number, generateMonth(), null, randomHolder(locale), randomCVV());
    }

    public static Holder holderWithYearEarly(String locale) {
        return new Holder(approvedCard().Number, generateMonth(), generateEarlyYear(2), randomHolder(locale), randomCVV());
    }

    public static Holder holderWithHolderKir(String locale) {
        return new Holder(approvedCard().Number, generateMonth(), generateYear(), randomHolderName("ru"), randomCVV());
    }

    public static Holder holderWithHolderSpecial(String locale) {
        return new Holder(approvedCard().Number, generateMonth(), generateYear(), "!@#$%", randomCVV());
    }

    public static Holder holderWithoutHolder(String locale) {
        return new Holder(approvedCard().Number, generateMonth(), generateYear(), null, randomCVV());
    }

    public static Holder holderWithHolderOneWord(String locale) {
        return new Holder(approvedCard().Number, generateMonth(), generateYear(), randomHolderName("en"), randomCVV());
    }

    public static Holder holderWithHolderFiveWords(String locale) {
        return new Holder(approvedCard().Number, generateMonth(), generateYear(), randomHolderWithFiveWords("en"), randomCVV());
    }

    public static Holder holderWithHolderHyphen(String locale) {
        return new Holder(approvedCard().Number, generateMonth(), generateYear(), randomHolderWithHyphen("en"), randomCVV());
    }

    public static Holder holderWithCVVLat(String locale) {
        return new Holder(approvedCard().Number, generateMonth(), generateYear(),  randomHolder(locale), randomHolderName("en"));
    }

    public static Holder holderWithCVVKir(String locale) {
        return new Holder(approvedCard().Number, generateMonth(), generateYear(),  randomHolder(locale), randomHolderName("ru"));
    }

    public static Holder holderWithCVVSpecial(String locale) {
        return new Holder(approvedCard().Number, generateMonth(), generateYear(),  randomHolder(locale), "@#$");
    }

    public static Holder holderWithCVVOneDigit(String locale) {
        return new Holder(approvedCard().Number, generateMonth(), generateYear(),  randomHolder(locale), "2");
    }

    public static Holder holderWithCVVFourDigits(String locale) {
        return new Holder(approvedCard().Number, generateMonth(), generateYear(),  randomHolder(locale), randomCVV() + "1");
    }

    public static Holder holderWithoutCVV(String locale) {
        return new Holder(approvedCard().Number, generateMonth(), generateYear(),  randomHolder(locale), null);
    }

    public static Holder holderWithYearAndMonthEarly(String locale) {
        return new Holder(approvedCard().Number, generateLaterMonth(1), generateEarlyYear(1),  randomHolder(locale), randomCVV());
    }



    @Value
    public static class CardNumber {
        String Number;
    }

    @Value
    public static class Holder {
        String Number;
        String Month;
        String Year;
        String Holder;
        String CVV;

    }








}
