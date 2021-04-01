package ru.javawebinar.topjava.storage;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

public class MealsMapStorage implements Storage {

    private final ConcurrentMap<Integer, Meal> storage;
    private static final AtomicInteger counter = new AtomicInteger();

    public MealsMapStorage() {
        this.storage = new ConcurrentHashMap<>();
    }

    @Override
    public void save(Meal meal) {
        int id = counter.getAndIncrement();
        storage.put(id, new Meal(id, meal.getDateTime(), meal.getDescription(), meal.getCalories()));
    }

    @Override
    public void update(Meal meal) {
        int id = meal.getId();
        storage.replace(id, new Meal(id, meal.getDateTime(), meal.getDescription(), meal.getCalories()));
    }

    @Override
    public Meal get(int id) {
        return storage.get(id);
    }

    @Override
    public void delete(int id) {
        storage.remove(id);
    }

    @Override
    public List<Meal> getAll() {
        return storage.values().stream().sorted(Comparator.comparing(Meal::getDateTime)).collect(Collectors.toList());
    }
}
