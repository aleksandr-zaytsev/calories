package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealsDao implements Dao<Meal, Integer> {

    private final Map<Integer, Meal> dao = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter;

    private MealsDao() {

        idCounter = new AtomicInteger(0);
        dao.put(idCounter.incrementAndGet(), new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        dao.put(idCounter.incrementAndGet(), new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        dao.put(idCounter.incrementAndGet(), new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        dao.put(idCounter.incrementAndGet(), new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        dao.put(idCounter.incrementAndGet(), new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        dao.put(idCounter.incrementAndGet(), new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        dao.put(idCounter.incrementAndGet(), new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    private static class MealsDaoHolder {
        public static final MealsDao INSTANCE = new MealsDao();
    }

    public static MealsDao getInstance() {
        return MealsDaoHolder.INSTANCE;
    }

    @Override
    public void deleteAll() {
        dao.clear();
    }

    @Override
    public void deleteById(Integer id) {
        dao.remove(id);
    }

    @Override
    public Optional<Meal> findById(Integer id) {
        Meal mealFound = dao.get(id);
        return (mealFound == null) ? Optional.empty() : Optional.of(mealFound);
    }

    @Override
    public List<Meal> findAll() {

        return new ArrayList<>(dao.values());
    }

    @Override
    public void save(Meal entity) {
        Integer newId = idCounter.incrementAndGet();
        Meal newMeal = new Meal(idCounter.get(),entity.getDateTime(), entity.getDescription(), entity.getCalories());
        dao.put(newId, newMeal);
    }
}