package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

import javax.transaction.Transactional;

import static ru.javawebinar.topjava.MealTestData.MEAL_MATCHER;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(profiles = Profiles.DATAJPA)
@Transactional
public class DataJpaUserServiceTest extends AbstractUserServiceTest {
    @Test
    public void getByIdWithMeals() {
        User userWithMeals = service.getByIdWithMeals(USER_ID);
        USER_MATCHER.assertMatch(userWithMeals, UserTestData.userWithMeals);
        MEAL_MATCHER.assertMatch(userWithMeals.getMeals(), UserTestData.userWithMeals.getMeals());

        User guest = service.getByIdWithMeals(GUEST_ID);
        USER_MATCHER.assertMatch(guest, UserTestData.guest);
        MEAL_MATCHER.assertMatch(guest.getMeals(), UserTestData.guest.getMeals());
    }
}