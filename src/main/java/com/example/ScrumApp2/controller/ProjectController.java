package com.example.ScrumApp2.controller;

import com.example.ScrumApp2.model.Project;
import com.example.ScrumApp2.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping(path = "/{id}")
    public Optional<Project> getProjectbyId(@PathVariable("id") Long id) {
        return projectService.getProjectbyId(id);
    }

    @PostMapping
    public Project createProject(@RequestBody Project newProject) {
        return projectService.createProject(newProject);
    }

    @PutMapping(path = "/{id}")
    public void updateProject(@RequestBody Project project, @PathVariable Long id) {
        projectService.updateProject(project,id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteProject(@PathVariable Long id) {
      projectService.deleteProject(id);
        return null;
    }
}