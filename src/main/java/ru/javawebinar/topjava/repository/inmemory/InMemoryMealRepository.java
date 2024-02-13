package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> this.save(meal, meal.getUserId()));
    }

    @Override
    public Meal save(Meal meal, int authUserId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(authUserId);
            repository.put(meal.getId(), meal);
            return meal;
        }
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) ->
                meal.getUserId() == authUserId && oldMeal.getUserId() == authUserId ? meal : null);
    }

    @Override
    public boolean delete(int id, int authUserId) {
        Meal meal = repository.get(id);
        return (meal == null || meal.getUserId() == authUserId) && repository.remove(id) != null;
    }

    @Override
    public Meal get(int id, int authUserId) {
        Meal meal = repository.get(id);
        return (meal != null && meal.getUserId() != authUserId) ? null : meal;
    }

    @Override
    public List<Meal> getAll(int authUserId) {

        return repository.values().stream()
                .filter(meal -> meal.getUserId() == authUserId)
                .sorted(Comparator
                        .comparing(Meal::getDate)
                        .reversed()
                        .thenComparing(Meal::getTime))
                .collect(Collectors.toList());

    }
}

