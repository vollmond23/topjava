package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface Storage {

    void clear();

    void save(Meal meal);

    void update(Meal meal);

    Meal get(int id);

    void delete(int id);

    int size();

    List<Meal> getAll();
}