package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Counter;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MealsUtil {
    private static final List<Meal> MEALS_LIST = Arrays.asList(
            new Meal(Counter.get(), LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new Meal(Counter.get(), LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(Counter.get(), LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(Counter.get(), LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new Meal(Counter.get(), LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(Counter.get(), LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(Counter.get(), LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410),
            new Meal(Counter.get(), LocalDateTime.of(2020, Month.FEBRUARY, 1, 8, 0), "Завтрак", 1000),
            new Meal(Counter.get(), LocalDateTime.of(2020, Month.FEBRUARY, 1, 13, 30), "Обед", 500),
            new Meal(Counter.get(), LocalDateTime.of(2020, Month.FEBRUARY, 1, 19, 0), "Ужин", 410)
    );
    private static final int CALORIES_PER_DAY = 2000;

    public static void main(String[] args) {
        List<MealTo> mealsTo = filteredByStreams(getMealsList(), LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);
    }

    public static List<MealTo> filteredByStreams(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
//                      Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
                );

        return meals.stream()
                .filter(meal -> TimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime))
                .map(meal -> createTo(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    private static MealTo createTo(Meal meal, boolean excess) {
        return new MealTo(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }

    public static List<Meal> getMealsList() {
        return MEALS_LIST;
    }

    public static Map<Integer, Meal> getMealsMap() {
        return getMealsList()
                .stream()
                .collect(Collectors.toMap(Meal::getId, meal -> meal));
    }

    public static List<MealTo> refreshMealsList(List<Meal> meals) {
        return filteredByStreams(meals, LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY);
    }
}
