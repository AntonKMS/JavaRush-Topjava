package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);

    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(meal,1));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.getUserId()!=userId){return null;}

        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());

        }
        // handle case: update, but not present in storage
        Meal old = repository.get(meal.getId());
        if(old != null && old.getUserId() != userId){return null;}
        repository.put(meal.getId(),meal);

        return meal;
    }

    @Override
    public boolean delete(int id,int userId)
    {
        Meal meal = repository.get(id);
        if(meal != null && meal.getUserId() != userId){return false;}

        return repository.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId)
    {
        Meal meal = repository.get(id);
        if(meal != null && meal.getUserId() != userId){return null;}

        return repository.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {

        return repository.values().stream().filter(meal -> meal.getUserId()== userId )
                .sorted((m1,m2)->m1.getDateTime().compareTo(m2.getDateTime())).collect(Collectors.toList());

    }

}

