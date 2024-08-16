package com.project.ppmtool.web;

import com.project.ppmtool.domain.Project;
import com.project.ppmtool.services.MapValidationErrorService;
import com.project.ppmtool.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;


@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity< ? > createNewProject(@Valid @RequestBody Project project, BindingResult result ){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        Project addProject = projectService.saveOrUpdateProject(project);
        return new ResponseEntity <>(project, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectId(@PathVariable String projectId){
        Project project = projectService.findByProjectByIdentifier(projectId);
        return new ResponseEntity<Project>(project,HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<?> getAllProjects() {
        return projectService.findAllProjects();
    }

//    @PutMapping("/{projectId}")
//    public ResponseEntity<?> updateProject(@PathVariable String projectId){
//        Project project = projectService.findByProjectByIdentifier(projectId);
//        project.setProjectName("Updated Name");
//        project.setDescription("Updated Description");
//        Project updatedProject = projectService.saveOrUpdateProject(project);
//        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
//    }

    @PutMapping("/{projectId}")
    public ResponseEntity<?> updateProject(@PathVariable String projectId, @Valid @RequestBody Project project) {
        project.setProjectIdentifier(projectId.toUpperCase());
        System.out.println("Incoming Project: " + project);
        Project updatedProject = projectService.saveOrUpdateProject(project);
        System.out.println("Updated Project: " + updatedProject);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }



    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId){
        projectService.deleteProjectByIdentifier(projectId);
        return new ResponseEntity<String>("Project with ID: " + projectId + " was Deleted Successfully!!!!", HttpStatus.OK);
    }
}
