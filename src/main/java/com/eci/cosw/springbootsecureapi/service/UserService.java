package com.eci.cosw.springbootsecureapi.service;

import com.eci.cosw.springbootsecureapi.model.Task;
import com.eci.cosw.springbootsecureapi.model.User;

import javax.servlet.ServletException;
import java.util.List;

/**
 * @author Santiago Carrillo
 * 8/21/17.
 */
public interface UserService {
    List<User> getUsers() throws Exception;

    User getUser(String id) throws Exception;

    User createUser(User user) throws Exception;

    User findUserByEmail(String email);

    User findUserByEmailAndPassword(String email, String password) throws ServletException;

    void addTask(String userId, Task task) throws Exception;
}