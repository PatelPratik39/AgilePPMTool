package com.project.ppmtool.web;

import com.project.ppmtool.domain.Project;
import com.project.ppmtool.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("")
    public ResponseEntity< ? > createNewProject(@Valid @RequestBody Project project, BindingResult result ){

        if(result.hasErrors()){
            return new ResponseEntity<String>("Object is Empty" , HttpStatus.BAD_REQUEST);
        }
        Project addProject = projectService.saveOrUpdateProject(project);
        return new ResponseEntity <>(project, HttpStatus.CREATED);
    }
}
