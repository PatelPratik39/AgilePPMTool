package com.project.ppmtool.web;

import com.project.ppmtool.domain.Project;
import com.project.ppmtool.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;


@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("")
    public ResponseEntity< ? > createNewProject(@Valid @RequestBody Project project, BindingResult result ){
/**
 * below logic will send specific errors using a key value pair using HashMap function and iterating using for loop
 * over FieldError class methods
 */


        if(result.hasErrors()){
            Map <String , String> errorMap = new HashMap <>();
            for(FieldError error: result.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity< Map <String , String> >(errorMap, HttpStatus.BAD_REQUEST);
        }

        Project addProject = projectService.saveOrUpdateProject(project);
        return new ResponseEntity <>(project, HttpStatus.CREATED);
    }
}
