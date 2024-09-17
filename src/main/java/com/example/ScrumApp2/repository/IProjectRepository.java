package com.example.ScrumApp2.repository;

import com.example.ScrumApp2.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProjectRepository extends JpaRepository<Project,Long> {
}
