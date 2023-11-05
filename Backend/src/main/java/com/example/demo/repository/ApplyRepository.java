package com.example.demo.repository;

import com.example.demo.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyRepository extends JpaRepository<Apply, Integer> {
    public void insert(Project project, User user);

    public void updateState(Long apply_id);

    public Long findIdByProject_id(int project_id);

    public Apply findById(Long apply_id);

    public List<Project> findPendingProjects(String user_id);

    public List<User> findApplyUsers(int projectId);
}
