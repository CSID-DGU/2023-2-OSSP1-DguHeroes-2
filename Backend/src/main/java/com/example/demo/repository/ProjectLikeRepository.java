package com.example.demo.repository;

import com.example.demo.domain.Project;
import com.example.demo.domain.ProjectLike;
import com.example.demo.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectLikeRepository extends JpaRepository<Project, Integer> {
    public ProjectLike insert(Project project, User user);

}
