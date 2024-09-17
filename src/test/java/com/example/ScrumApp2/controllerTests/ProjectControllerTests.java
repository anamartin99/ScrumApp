package com.example.ScrumApp2.controllerTests;

import com.example.ScrumApp2.controller.ProjectController;
import com.example.ScrumApp2.model.Project;
import com.example.ScrumApp2.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ProjectControllerTests {

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    private Project project1;
    private Project project2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        project1 = new Project();
        project1.setId(1L);
        project1.setName("Project 1");

        project2 = new Project();
        project2.setId(2L);
        project2.setName("Project 2");
    }

    @Test
    void testGetAllProject() {
        List<Project> projectList = new ArrayList<>(Arrays.asList(project1, project2));

        when(projectService.getAllProjects()).thenReturn(projectList);

        List<Project> result = projectController.getAllProjects();
        assertEquals(2, result.size());
        verify(projectService, times(1)).getAllProjects();
    }

    @Test
    void testGetProjectById() {
        when(projectService.getProjectbyId(1L)).thenReturn(Optional.of(project1));

        Optional<Project> result = projectController.getProjectbyId(1L);

        assertTrue(result.isPresent());
        assertEquals(project1.getId(), result.get().getId());
        assertEquals(project1.getName(), result.get().getName());
        verify(projectService, times(1)).getProjectbyId(1L);
    }

    @Test
    void testCreateProject() {
        when(projectService.createProject(any(Project.class))).thenReturn(project1);

        Project result = projectController.createProject(project1);
        assertEquals("Project 1", result.getName());
        verify(projectService, times(1)).createProject(any(Project.class));
    }

    @Test
    void testUpdateProject() {
        doNothing().when(projectService).updateProject(any(Project.class), eq(1L));

        projectController.updateProject(project1, 1L);

        verify(projectService,times(1)).updateProject(any(Project.class), eq(1L));
    }

    @Test
    void testDeleteProject() {

      projectController.deleteProject(1L);
        verify(projectService, times(1)).deleteProject(1L);
    }
}
