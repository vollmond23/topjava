package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface Storage {

    void save(Meal meal);

    void update(Meal meal);

    Meal get(int id);

    void delete(int id);

    List<Meal> getAll();
}