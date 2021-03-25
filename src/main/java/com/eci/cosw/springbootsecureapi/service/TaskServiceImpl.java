package com.eci.cosw.springbootsecureapi.service;

import com.eci.cosw.springbootsecureapi.model.Task;
import com.eci.cosw.springbootsecureapi.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Service
public class TaskServiceImpl implements TaskService {

    private List<Task> tasks = new ArrayList<>();

    public TaskServiceImpl() {
    }


    @PostConstruct
    private void populateSampleData() {
        tasks.add(new Task("Do IETI Lab 8", "Juan Frasica", "In Progress", "24/05/2021"));
        tasks.add(new Task("Do IETI Lab 9", "Daniel López", "Ready", "17/03/2021"));
        tasks.add(new Task("Do project stuff", "María Paez", "Completed", "13/02/2021"));
    }


    @Override
    public List<Task> getTasks() {
        return tasks;
    }


}
