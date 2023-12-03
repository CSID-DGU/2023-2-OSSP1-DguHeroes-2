package com.example.demo.repository;

import com.example.demo.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    public Project insert(Project project);
    public Project findByProjectId(int projectId);
    public int delete(int project_id);


    public List<Project> findAllProjectList();


    public int getLike_count(int project_id);

    public int getVisited_number(int project_id);

    public List<Project> findPopularProject();

    public List<Project> findEndProjects(String user_id);


}

