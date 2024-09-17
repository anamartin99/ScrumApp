package com.example.ScrumApp2.service;

import com.example.ScrumApp2.model.Project;
import com.example.ScrumApp2.repository.IProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    public ProjectService(IProjectRepository iProjectRepository) {
        this.iProjectRepository = iProjectRepository;
    }

    IProjectRepository iProjectRepository;
    public Project createProject(Project newProject){
        return iProjectRepository.save(newProject);
    }

    public List<Project> getAllProjects() {
        return (List<Project>) iProjectRepository.findAll();
    }

    public Optional<Project> getProjectbyId(Long id){
        Project project = iProjectRepository.findById(id).orElseThrow();
        return Optional.of(project);
    }

    public void updateProject(Project project, long id) {
        project.setId(id);
        iProjectRepository.save(project);
    }

    public String deleteProject(long id) {
        iProjectRepository.deleteById(id);
        return "Project deleted";
    }

}
