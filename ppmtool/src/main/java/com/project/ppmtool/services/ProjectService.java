package com.project.ppmtool.services;

import com.project.ppmtool.domain.Project;
import com.project.ppmtool.exception.ProjectIdException;
import com.project.ppmtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject( Project project){
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
//            // Check if the project identifier already exists
//            Project existingProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());
//            if (existingProject != null && (project.getId() == null || !existingProject.getId().equals(project.getId()))) {
//                throw new ProjectIdException("Project ID '" + project.getProjectIdentifier() + "' already exists");
//            }
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Project ID" + project.getProjectIdentifier().toUpperCase() + " already Exists");
        }
    }
    public Project findByProjectByIdentifier(String projectId){
        return projectRepository.findByProjectIdentifier(projectId.toUpperCase());
    }

}
