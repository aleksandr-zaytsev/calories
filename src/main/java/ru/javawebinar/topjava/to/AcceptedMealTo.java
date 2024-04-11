package ru.javawebinar.topjava.to;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import java.util.Objects;

public class AcceptedMealTo extends BaseTo {
    @NotNull
    private final LocalDateTime dateTime;

    @NotBlank
    @Size(min = 2, max = 100)
    private final String description;

    @Range(min = 15, max = 10000)
    private final Integer calories;

    @ConstructorProperties({"id", "dateTime", "description", "calories"})
    public AcceptedMealTo(Integer id, LocalDateTime dateTime, String description, Integer calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCalories() {
        return calories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcceptedMealTo that = (AcceptedMealTo) o;
        return dateTime.equals(that.dateTime) && description.equals(that.description) && calories.equals(that.calories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, description, calories);
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
