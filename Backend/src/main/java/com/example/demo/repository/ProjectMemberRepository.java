package com.example.demo.repository;

import com.example.demo.domain.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Integer> {
    public void insert(ProjectMember projectMember);
}