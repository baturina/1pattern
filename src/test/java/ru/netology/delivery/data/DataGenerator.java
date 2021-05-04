package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private static final Faker faker = new Faker(new Locale("ru"));

    private DataGenerator() {
    }

    public static String generateDate(int plusDays) {
        return LocalDate.now().plusDays(plusDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        return faker.address().city();
    }

    public static String generateName() {

        return faker.name().fullName();
    }

    public static String generatePhone() {

        return faker.phoneNumber().phoneNumber();
    }
}

