package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.ValidationUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal save(Meal meal) {
        ValidationUtil.checkNew(meal);
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

    public void update(Meal meal, int id) {
        ValidationUtil.assureIdConsistent(meal, id);
        log.info("update {} for user {}", meal, authUserId());
        service.save(meal, authUserId());
    }

    public List<Meal> getAll() {
        log.info("getAll for user {}", authUserId());
        return service.getAll();
    }

    public List<MealTo> getAllTos() {
        log.info("getAllTos for user {}", authUserId());
        return MealsUtil.getTos(getAll(), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public List<MealTo> getAllTosFiltered(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        if (startDate == null) startDate = LocalDate.MIN;
        if (endDate == null) endDate = LocalDate.MAX;
        LocalTime finalStartTime = startTime == null ? LocalTime.MIN : startTime;
        LocalTime finalEndTime = endTime == null ? LocalTime.MAX : endTime;
        log.info("getAllTos filtered by date & time for user {}", authUserId());
        return MealsUtil.filterByPredicate(service.getAllFiltered(startDate, endDate), MealsUtil.DEFAULT_CALORIES_PER_DAY,
                meal -> DateTimeUtil.isBetweenHalfOpen(meal.getTime(), finalStartTime, finalEndTime));
    }
}