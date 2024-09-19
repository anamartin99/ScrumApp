package com.example.ScrumApp2.service;

import com.example.ScrumApp2.model.Task;
import com.example.ScrumApp2.repository.ITaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final ITaskRepository iTaskRepository;

    public TaskService(ITaskRepository iTaskRepository) {
        this.iTaskRepository = iTaskRepository;
    }
    public Task createTask(Task task) {
        return iTaskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return iTaskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return iTaskRepository.findById(id);
    }

    public Optional<Task> updateTask(Long id, Task task) {
        return iTaskRepository.findById(id)
                .map(existingTask -> {
                    task.setId(id);
                    return iTaskRepository.save(task);
                });
    }

    public boolean deleteTask(Long id) {
        return iTaskRepository.findById(id)
                .map(task -> {
                    iTaskRepository.delete(task);
                    return true;
                })
                .orElse(false);
    }
}