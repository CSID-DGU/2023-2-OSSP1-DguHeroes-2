package com.example.demo.dto;

import com.example.demo.domain.Project;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserProjectList {
    private List<Project> belonging_project;
    private List<Project> pending_project;
    private List<Project> like_project;
    private List<Project> end_project;
}
