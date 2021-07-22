package ru.javawebinar.topjava.service.jpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;

import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;

@ActiveProfiles(Profiles.JPA)
public class JpaUserServiceTest extends UserServiceTest {
    @Test (expected = UnsupportedOperationException.class)
    public void getWithMeals() {
        userService.getWithMeals(ADMIN_ID);
    }
}
