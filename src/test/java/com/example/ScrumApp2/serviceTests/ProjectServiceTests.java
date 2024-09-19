package com.example.ScrumApp2.serviceTests;

import com.example.ScrumApp2.model.Project;
import com.example.ScrumApp2.repository.IProjectRepository;
import com.example.ScrumApp2.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ProjectServiceTests {
    @Mock
    private IProjectRepository iProjectRepository;

    @InjectMocks
    private ProjectService projectService;
    private Project project1 = new Project();
    private Project project2 = new Project();

    private List<Project> projectList = new ArrayList<>();
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        project1 = new Project();
        project1.setId(1L);
        project1.setName("Project 1");

        project2 = new Project();
        project2.setId(2L);
        project2.setName("Project 2");

        projectList.add(project1);
        projectList.add(project2);
    }
    @Test
    void createProject() {
        when(iProjectRepository.save(any(Project.class))).thenReturn(project1);

        Project result = projectService.createProject(project1);
        assertEquals(1,result.getId());
        assertEquals("Project 1", result.getName());
    }

    @Test
    void getAllProjects(){
        when(iProjectRepository.findAll()).thenReturn(projectList);
        List<Project> result = projectService.getAllProjects();

        assertEquals(2, result.size());
        assertEquals("Project 1", result.get(0).getName());
        assertEquals("Project 2", result.get(1).getName());
    }

    @Test
    void getProjectsById(){
        when(iProjectRepository.findById(anyLong())).thenReturn(Optional.of(project1));

        Optional<Project> result = projectService.getProjectbyId(1L);

        assertEquals(1, result.get().getId());
        assertEquals("Project 1", result.get().getName());
    }

    @Test
    void updateProjects(){
        when(iProjectRepository.save(any(Project.class))).thenReturn(project1);
        Project update = project1;
        update.setName("Project one");

        projectService.updateProject(update, 1);
        assertEquals(1, update.getId());

        verify(iProjectRepository, times(1)).save(update);
    }

    @Test
    void deleteProjects(){
        when(iProjectRepository.findById(2L)).thenReturn(Optional.of(project2));
        projectService.deleteProject(2);
        verify(iProjectRepository, times(1)).deleteById(2L);
    }
}