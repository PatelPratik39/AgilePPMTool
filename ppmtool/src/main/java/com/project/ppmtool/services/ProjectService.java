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



        public Project saveOrUpdateProject (Project project){
            // Convert the project identifier to uppercase to ensure consistency
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

            // Check if the project already exists
            Project existingProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());

            if (existingProject != null && (project.getId() == null || !existingProject.getId().equals(project.getId()))) {
                throw new ProjectIdException("Project ID '" + project.getProjectIdentifier() + "' already exists");
            }

            // Ensure the correct ID is used for updating
            if (existingProject != null) {
                project.setId(existingProject.getId());
            }

            System.out.println("Before Save: " + project);
            Project savedProject = projectRepository.save(project);
            System.out.println("After Save: " + savedProject);

            return savedProject;
        }

    public Project findByProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Project ID" + projectId + " does not Exists");
        }
        return project;
    }
//    using an Iterable collection to iterate over all project list
    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier (String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project == null){
            throw new ProjectIdException("Project ID is not valid : "+ projectId);
        }
        projectRepository.delete(project);
    }

}
