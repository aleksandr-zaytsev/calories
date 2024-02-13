package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public List<Meal> getAll(int authUserId) {
        return repository.getAll(authUserId);
    }

    public Meal get(int id, int authUserId) {
        return ifNullThrowNotFound(repository.get(id, authUserId), id, authUserId);
    }

    public Meal create(Meal meal, int authUserId) {
        return ifNullThrowNotFound(repository.save(meal, authUserId), meal.getId(), authUserId);
    }

    public void delete(int id, int authUserId) {
        if (!repository.delete(id, authUserId)) {
            throw new NotFoundException(
                    String.format("Meal with id=%d and userId=%d not found", id, authUserId));
        }
    }

    public void update(Meal meal, int authUserId) {
        ifNullThrowNotFound(repository.save(meal, authUserId), meal.getId(), authUserId);
    }

    private Meal ifNullThrowNotFound(Meal meal, int id, int authUserId) {
        if (meal != null) {
            return meal;
        }
        throw new NotFoundException(
                String.format("Meal with id=%d and userId=%d not found", id, authUserId));
    }
}