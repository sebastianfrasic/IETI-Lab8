package com.eci.cosw.springbootsecureapi.service;

import com.eci.cosw.springbootsecureapi.model.Task;
import com.eci.cosw.springbootsecureapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Santiago Carrillo
 * 8/21/17.
 */
@Service
public class UserServiceImpl
        implements UserService {

    public List<User> users = new ArrayList<>();


    @Autowired
    public UserServiceImpl() {

    }

    @PostConstruct
    private void populateSampleData() {
        users.add(new User("1", "Juan Frasica", "juan@mail.com", "s3b4stian"));
        users.add(new User("2", "Daniel López", "daniel@mail.com", "daniel123"));
        users.add(new User("3", "María Paez", "maria@mail.com", "m4ria12345"));
    }


    @Override
    public List<User> getUsers() throws Exception {
        if (users.isEmpty()) {
            throw new Exception("There are no users");
        } else {
            return users;
        }
    }

    @Override
    public User getUser(String id) throws Exception {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        throw new Exception("That user doesn´t exists");
    }

    @Override
    public User createUser(User user) throws Exception {
        for (User u : users) {
            if (u.getId().equals(user.getId())) {
                throw new Exception("User already registered");
            }
        }
        users.add(user);
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = users.stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
        return user;
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) throws ServletException {
        User user = findUserByEmail(email);
        if (user == null) {
            throw new ServletException("User email not found.");
        }
        return user.getPassword().equals(password) ? user : null;
    }

    @Override
    public void addTask(String userId, Task task) throws Exception {
        boolean userExisting = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(userId)) {
                users.get(i).addTask(task);
                userExisting = true;
                break;
            }
        }
        if (!userExisting) {
            throw new Exception("That user doesn´t exists");
        }

    }

}
