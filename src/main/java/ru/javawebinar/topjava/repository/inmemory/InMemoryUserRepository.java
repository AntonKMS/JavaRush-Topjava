package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger();
    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);

        return repository.remove(id) != null;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        if(user.isNew()){
            user.setId(counter.incrementAndGet());
            repository.put(user.getId(),user);
            return user;
            }

        return repository.computeIfPresent(user.getId(),(id,oldId)->user);
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);

        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        List<User> userList = new ArrayList<>(repository.values());
        Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {

                return u1.getName().compareTo(u2.getName());
            }
        });

        return userList;
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        List<User> userList = new ArrayList<>(repository.values());
        for(User user:userList){
            if(user.getEmail().equals(email))
                return user;
        }

        return null;
    }
}
