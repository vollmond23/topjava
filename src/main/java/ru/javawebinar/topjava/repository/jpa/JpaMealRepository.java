package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        User user = em.getReference(User.class, userId);
        if (meal.isNew()) {
            meal.setUser(user);
            em.persist(meal);
            return meal;
        } else {
            if (userId == meal.getUser().getId()) {
                return em.merge(meal);
            } else {
                throw new NotFoundException("");
            }
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        User user = em.find(User.class, userId);
        return em.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .setParameter("user_id", user.getId())
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        User user = em.find(User.class, userId);
        Meal meal = null;
        try {
            meal = (Meal) em.createNamedQuery(Meal.GET)
                    .setParameter("id", id)
                    .setParameter("user_id", userId)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NotFoundException(e.getMessage());
        }
        return meal;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class)
                .setParameter("user_id", userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return em.createNamedQuery(Meal.BETWEEN_HALF_OPEN, Meal.class)
                .setParameter("start", startDateTime)
                .setParameter("end", endDateTime)
                .setParameter("user_id", userId)
                .getResultList();
    }
}