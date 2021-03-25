package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class MealTo implements Comparable<MealTo> {

    private final int id;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private boolean excess;

    public MealTo(int id, LocalDateTime dateTime, String description, int calories, boolean excess) {
        Objects.requireNonNull(dateTime);
        Objects.requireNonNull(description);
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExcess() {
        return excess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MealTo mealTo = (MealTo) o;

        if (id != mealTo.id) return false;
        if (calories != mealTo.calories) return false;
        if (excess != mealTo.excess) return false;
        if (!dateTime.equals(mealTo.dateTime)) return false;
        return description.equals(mealTo.description);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + dateTime.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + calories;
        result = 31 * result + (excess ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                '}';
    }

    @Override
    public int compareTo(MealTo mealTo) {
        return dateTime.compareTo(mealTo.getDateTime());
    }
}
