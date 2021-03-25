package ru.javawebinar.topjava.storage;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

public class MealsListStorage implements Storage {

    private static final Logger log = getLogger(MealsListStorage.class);
    private final List<Meal> storage;

    public MealsListStorage(List<Meal> storage) {
        this.storage = new CopyOnWriteArrayList<>(storage);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void save(Meal meal) {
        checkIfNotExist(getSearchKey(meal.getId()));
        storage.add(meal);
    }

    @Override
    public void update(Meal meal) {
        int index = getSearchKey(meal.getId());
        checkIfExist(index);
        storage.set(index, meal);
    }

    @Override
    public Meal get(int id) {
        int searchKey = getSearchKey(id);
        checkIfExist(searchKey);
        return storage.get(searchKey);
    }

    @Override
    public void delete(int id) {
        int searchKey = getSearchKey(id);
        checkIfExist(searchKey);
        storage.remove(searchKey);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public List<Meal> getAll() {
        return storage.stream().sorted().collect(Collectors.toList());
    }

    protected int getSearchKey(int id) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    protected boolean isExist(int searchKey) {
        return searchKey != -1;
        }


    private void checkIfExist(int searchKey) {
        if (!isExist(searchKey)) {
            int id = storage.get(searchKey).getId();
            log.warn("Meal with id " + id + " not exist.");
            throw new IllegalArgumentException("Meal with id " + id + " not exist.");
        }
    }

    private void checkIfNotExist(int searchKey) {
        if (isExist(searchKey)) {
            int id = storage.get(searchKey).getId();
            log.warn("Meal with id " + id + " already exist.");
            throw new IllegalArgumentException("Meal with id " + id + " already exist.");
        }
    }
}
