package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final Meal adminMeal1 = new Meal(START_SEQ + 2, LocalDateTime.parse("2021-04-29T08:46:02.000000"), "Завтрак админа", 1000);
    public static final Meal adminMeal2 = new Meal(START_SEQ + 3, LocalDateTime.parse("2021-04-29T13:46:02.000000"), "Обед админа", 800);
    public static final Meal adminMeal3 = new Meal(START_SEQ + 4, LocalDateTime.parse("2021-04-29T20:46:02.000000"), "Ужин админа", 200);
    public static final Meal userMeal1 = new Meal(START_SEQ + 5, LocalDateTime.parse("2021-04-29T08:46:02.000000"), "Завтрак юзера", 1000);
    public static final Meal userMeal2 = new Meal(START_SEQ + 6, LocalDateTime.parse("2021-04-29T13:46:02.000000"), "Обед юзера", 800);
    public static final Meal userMeal3 = new Meal(START_SEQ + 7, LocalDateTime.parse("2021-04-29T20:46:02.000000"), "Ужин юзера", 300);
    public static final Meal userMeal4 = new Meal(START_SEQ + 8, LocalDateTime.parse("2021-04-30T08:46:02.000000"), "Завтрак юзера", 1000);

    public static Meal getNewMeal() {
        return new Meal(null, LocalDateTime.parse("2021-04-29T10:46:02.000000"), "Перекус", 500);
    }

    public static Meal getUpdatedMeal() {
        Meal updated = new Meal(adminMeal1);
        updated.setDateTime(LocalDateTime.parse("2021-04-29T09:46:02.000000"));
        updated.setDescription("Updated meal");
        updated.setCalories(330);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
