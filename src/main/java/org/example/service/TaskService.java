package org.example.service;

import org.example.models.Task;
import org.example.supportEnum.Category;
import org.example.supportEnum.Status;

import java.util.List;

public class TaskService {


    private org.example.dao.TaskDAO taskDAO;

    public TaskService() {

        this.taskDAO = new org.example.dao.TaskDAO();
    }

    public void update(Task task){
        taskDAO.update(task);
    }

    public void save2(Task task) {

        if (!taskDAO.existById(task.getId())) {//true
            taskDAO.save(task);
        } else { //false -- задача уже существует
            throw new RuntimeException("Exists task");
        }
    }

    public void save3(Task task) {
        //тернарный оператор
    }

    public Task findById(Long id) {
        return taskDAO.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Long findCountTaskByUserId(Long id) {
        return taskDAO.findCountTaskByUserId(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    public List<Task> findByCategory(Category category) {

        List<Task> result = taskDAO.findByCategory(category);

        if (!result.isEmpty()) {
            return result;
        } else {
            throw new RuntimeException("Not found");
        }
    }
    public List<Task> findByStatus(Status status) {
        List<Task> result = taskDAO.findByStatus(status);

        if (!result.isEmpty()) {
            return result;
        } else {
            throw new RuntimeException("Not found");
        }
    }
    public void deleteById(Long id) {
        taskDAO.deleteById(id);
    }

}

