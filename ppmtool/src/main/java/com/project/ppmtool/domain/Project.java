package com.project.ppmtool.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectName;
    private String projectIdentifier;
    private String description;
    private Date start_Date;
    private Date end_Date;

    private Date created_At;
    private Date updated_At;

    @PrePersist
    private void onCreate(){
        this.created_At = new Date();
    }
    @PreUpdate
    private void onUpdate(){
        this.updated_At = new Date();
    }

}
