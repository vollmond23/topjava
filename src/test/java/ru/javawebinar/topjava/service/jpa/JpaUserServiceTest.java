package ru.javawebinar.topjava.service.jpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserServiceTest;

import java.util.List;

import static ru.javawebinar.topjava.MealTestData.MEAL_MATCHER;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles("jpa")
public class JpaUserServiceTest extends UserServiceTest {
    @Test (expected = AssertionError.class)
    public void getWithMeals() {
        User user = userService.getWithMeals(ADMIN_ID);
        List<Meal> meals = mealService.getAll(ADMIN_ID);
        USER_MATCHER.assertMatch(user, admin);
        MEAL_MATCHER.assertMatch(user.getMeals(), meals);
    }
}
