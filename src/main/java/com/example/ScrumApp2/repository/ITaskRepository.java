package com.example.ScrumApp2.repository;

import com.example.ScrumApp2.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskRepository extends JpaRepository<Task, Long> {
}