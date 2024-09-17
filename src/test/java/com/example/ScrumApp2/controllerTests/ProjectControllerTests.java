package com.example.ScrumApp2.controllerTests;

import com.example.ScrumApp2.controller.ProjectController;
import com.example.ScrumApp2.model.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ProjectControllerTests {
    @Mock
    private ProjectController projectController;

    @InjectMocks
    private ProjectController projectControllerMock;

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
        ArrayList<Project> projectList = new ArrayList<>(Arrays.asList(project1, project2));

        when(projectController.getAllProjects()).thenReturn(projectList);

        ArrayList<Project> result = projectControllerMock.getAllProject();
        assertEquals(2, result.size());
        verify(projectController, times(1)).getAllProject();
    }

    @Test
    void testGetProjectById() {
        when(projectController.getProjectbyId(1L)).thenReturn(project1);

        Optional<Project> result = projectControllerMock.getProjectbyId(1L);
        assertEquals(project1.getId(), result.getId());
        assertEquals(project1.getName(), result.getName());
        verify(projectController, times(1)).getProjectById(1L);
    }

    @Test
    void testCreateProject() {
        when(projectController.createProject(any(Project.class))).thenReturn(project1);

        Project result = projectControllerMock.createProject(project1);
        assertEquals("Project 1", result.getName());
        verify(projectController, times(1)).createProject(any(Project.class));
    }

    @Test
    void testUpdateProject() {
        doNothing().when(projectController).updateProject(any(Project.class), eq(1L));

        projectControllerMock.updateProject(project1, 1L);

        verify(projectController, times(1)).updateProject(any(Project.class), eq(1L));
    }

    @Test
    void testDeleteProject() {
        when(projectController.deleteProject(1L)).thenReturn("Project deleted");

        String result = projectControllerMock.deleteProject(1L);
        assertEquals("Project deleted", result);
        verify(projectController, times(1)).deleteProject(1L);
    }
}
