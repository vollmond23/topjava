package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Counter;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MealsListStorage;
import ru.javawebinar.topjava.storage.MealsMapStorage;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static Storage storage;

    @Override
    public void init() throws ServletException {
//        storage = new MealsMapStorage(MealsUtil.getMealsMap());
        storage = new MealsListStorage(MealsUtil.getMealsList());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        int calories = Integer.parseInt(request.getParameter("calories"));
        LocalDateTime dateTime = TimeUtil.parse(request.getParameter("date_time"));
        String description = request.getParameter("description");
        final boolean isCreate = (id == null || id.length() == 0);
        if (isCreate) {
            Meal meal = new Meal(Counter.get(), dateTime, description, calories);
            storage.save(meal);
            log.debug("New meal with id = " + meal.getId() + " was added");
        } else {
            storage.update(new Meal(Integer.parseInt(id), dateTime, description, calories));
            log.debug("Meal with id = " + id + " was edited");
        }
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("storage", MealsUtil.refreshMealsList(storage.getAll()));
            log.debug("redirect to meals");
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
        int id = Integer.parseInt(request.getParameter("id"));
        switch (action) {
            case "delete":
                storage.delete(id);
                log.debug("meal with id = " + id + " was deleted");
                response.sendRedirect("meals");
                return;
            case "edit":
                request.setAttribute("meal", storage.get(id));
                request.getRequestDispatcher("/edit.jsp").forward(request, response);
                break;
            default:
                throw new IllegalStateException("Action " + action + " is illegal");
        }
    }
}