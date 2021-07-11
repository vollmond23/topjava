package ru.javawebinar.topjava.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.util.List;
import java.util.Map;

import static ru.javawebinar.topjava.MealTestData.MEAL_MATCHER;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.admin;

@ActiveProfiles("datajpa")
public class DataJpaUserServiceTest extends UserServiceTest {
    @Test
    public void getWithMeals() {
        Map<User, List<Meal>> actual = userService.getWithMeals(ADMIN_ID);
        List<Meal> meals = mealService.getAll(ADMIN_ID);
        Assert.assertTrue(actual.containsKey(admin));
        MEAL_MATCHER.assertMatch(actual.get(admin), meals);
    }
}
