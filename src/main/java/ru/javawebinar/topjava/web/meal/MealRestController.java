package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal save(Meal meal) {
        log.info("create {} for user {}", meal, authUserId());
        return service.save(meal, authUserId());
    }

    public Meal get(int id) {
        log.info("get {} for user {}", id, authUserId());
        return service.get(id, authUserId());
    }

    public void delete(int id) {
        log.info("delete {} for user {}", id, authUserId());
        service.delete(id, authUserId());
    }

    public void update(Meal meal) {
        log.info("update {} for user {}", meal, authUserId());
        service.save(meal, authUserId());
    }

    public List<Meal> getAll() {
        log.info("getAll for user {}", authUserId());
        return service.getAll();
    }

    public List<MealTo> getAllTos() {
        log.info("getAllTos for user {}", authUserId());
        return MealsUtil.getTos(service.getAll(), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }
}