package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;
import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class MealTestData {
    public static final int MEAL_ID1 = START_SEQ + 3;
    public static final int MEAL_ID2 = START_SEQ + 4;
    public static final int MEAL_ID3 = START_SEQ + 5;
    public static final int MEAL_ID4 = START_SEQ + 6;
    public static final int MEAL_ID5 = START_SEQ + 7;
    public static final int MEAL_ID6 = START_SEQ + 8;
    public static final int MEAL_ID7 = START_SEQ + 9;

    public static final int NOT_EXIST_ID = 10;

    public static final int USER_ID = 100000;
    public static final int ANOTHER_USER_ID = 100001;

    public static final Meal meal1 = new Meal(MEAL_ID1, LocalDateTime.of(2020, 1, 30, 10, 0), "Завтрак", 500);
    public static final Meal meal2 = new Meal(MEAL_ID2, LocalDateTime.of(2020, 1, 30, 13, 0), "Обед", 1000);
    public static final Meal meal3 = new Meal(MEAL_ID3, LocalDateTime.of(2020, 1, 30, 20, 0), "Ужин", 500);
    public static final Meal meal4 = new Meal(MEAL_ID4, LocalDateTime.of(2020, 1, 31, 0, 0), "Еда на граничное значение", 100);
    public static final Meal meal5 = new Meal(MEAL_ID5, LocalDateTime.of(2020, 1, 31, 10, 0), "Завтрак", 1000);
    public static final Meal meal6 = new Meal(MEAL_ID6, LocalDateTime.of(2020, 1, 31, 13, 0), "Обед", 500);
    public static final Meal meal7 = new Meal(MEAL_ID7, LocalDateTime.of(2020, 1, 31, 20, 0), "Ужин", 410);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS), "newMeal", DEFAULT_CALORIES_PER_DAY);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(meal1);
        updated.setDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS));
        updated.setDescription("UpdatedDescription");
        updated.setCalories(3000);
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
