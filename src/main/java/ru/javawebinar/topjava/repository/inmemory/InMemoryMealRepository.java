package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
        // handle case: update, but not present in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal.getUserId() == authUserId ? meal : null);
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
    public Collection<Meal> getAll(int authUserId) {

        return repository.values().stream()
                .filter(meal -> meal.getUserId() == authUserId)
                .sorted(Comparator
                        .comparing(Meal::getDate)
                        .reversed()
                        .thenComparing(Meal::getTime))
                .collect(Collectors.toCollection(ArrayList::new));

    }
}

