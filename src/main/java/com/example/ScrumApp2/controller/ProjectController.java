package com.example.ScrumApp2.controller;

import com.example.ScrumApp2.model.Project;
import com.example.ScrumApp2.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    @GetMapping(path = "/project")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping(path = "/project/{id}")
    public Optional<Project> getProjectbyId(@PathVariable("id") Long id) {
        return projectService.getProjectbyId(id);
    }

    @PostMapping(path = "/project")
    public Project createProject(@RequestBody Project newProject) {
        return projectService.createProject(newProject);
    }

    @PutMapping(path = "/project/{id}")
    public void updateProject(@RequestBody Project project, @PathVariable Long id) {
        projectService.updateProject(project,id);
    }

    @DeleteMapping(path = "/project/{id}")
    public void deleteProject(@PathVariable("id") Long id) {
      projectService.deleteProject(id);
    }
}
