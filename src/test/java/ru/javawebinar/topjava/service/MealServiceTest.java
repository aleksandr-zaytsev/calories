package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        assertMatch(service.get(MEAL_ID1, USER_ID), meal1);
    }

    @Test
    public void delete() {
        service.delete(MEAL_ID1, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(MEAL_ID1, USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> meals = service.getBetweenInclusive(LocalDate.of(2020, 1, 31),
                LocalDate.of(2020, 1, 31), USER_ID);
        assertMatch(meals, meal7, meal6, meal5, meal4);
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, meal7, meal6, meal5, meal4, meal3, meal2, meal1);
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(updated.getId(), USER_ID), getUpdated());
    }

    @Test
    public void create() {
        Meal createdMeal = service.create(getNew(), USER_ID);
        Meal newMeal = getNew();
        newMeal.setId(createdMeal.getId());
        assertMatch(createdMeal, newMeal);
        assertMatch(service.get(createdMeal.getId(), USER_ID), newMeal);
    }

    @Test
    public void itShouldNotGetSomeoneElseMeal() {
        assertThrows(NotFoundException.class, () -> service.get(MEAL_ID1, ADMIN_ID));
    }

    @Test
    public void itShouldNotDeleteSomeoneElseMeal() {
        assertThrows(NotFoundException.class, () -> service.delete(MEAL_ID1, ADMIN_ID));
    }

    @Test
    public void itShouldNotUpdateAnotherMeal() {
        assertThrows(NotFoundException.class, () -> service.update(getUpdated(), ADMIN_ID));
    }

    @Test
    public void itShouldNotGetNonExistMeal() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_EXIST_ID, USER_ID));
    }

    @Test
    public void itShouldNotDeleteNonExistMeal() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_EXIST_ID, USER_ID));
    }

    @Test
    public void itShouldNotUpdateNonExistMeal() {
        Meal updatedMeal = getUpdated();
        updatedMeal.setId(NOT_EXIST_ID);
        assertThrows(NotFoundException.class, () -> service.update(updatedMeal, USER_ID));
    }

    @Test
    public void itShouldNotCreateDuplicateDateTimeMeal() {
        Meal duplicateMeal = new Meal(null, meal1.getDateTime(), "Some description", 100);
        assertThrows(DuplicateKeyException.class, () -> service.create(duplicateMeal, USER_ID));
    }
}