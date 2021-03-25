package ru.javawebinar.topjava.storage;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

public class MealsMapStorage implements Storage {

    private static final Logger log = getLogger(MealsMapStorage.class);
    private final ConcurrentMap<Integer, Meal> storage;

    public MealsMapStorage(Map<Integer, Meal> storage) {
        this.storage = new ConcurrentHashMap<>(storage);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void save(Meal meal) {
        int id = meal.getId();
        checkIfNotExist(id);
        storage.put(id, meal);
    }

    @Override
    public void update(Meal meal) {
        int id = meal.getId();
        checkIfExist(id);
        storage.put(id, meal);
    }

    @Override
    public Meal get(int id) {
        checkIfExist(id);
        return storage.get(id);
    }

    @Override
    public void delete(int id) {
        checkIfExist(id);
        storage.remove(id);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public List<Meal> getAll() {
        return storage.values().stream().sorted().collect(Collectors.toList());
    }

    protected boolean isExist(int id) {
        return storage.containsKey(id);
    }

    private void checkIfExist(int id) {
        if (!isExist(id)) {
            log.warn("Meal with id " + id + " not exist.");
            throw new IllegalArgumentException("Meal with id " + id + " not exist.");
        }
    }

    private void checkIfNotExist(int id) {
        if (isExist(id)) {
            log.warn("Meal with datetime " + id + " already exist.");
            throw new IllegalArgumentException("Meal with datetime " + id + " already exist.");
        }
    }
}
