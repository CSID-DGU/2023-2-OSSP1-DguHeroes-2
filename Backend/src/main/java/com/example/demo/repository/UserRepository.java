package com.example.demo.repository;

import com.example.demo.domain.Project;
import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User save(User user);
    public User findByid(String id);
    public int deleteByid(String id);
    public Long login(String email, String password);
    public int duplicationCheckId(String id);
    public int duplicationCheckNickname(String nickname);
    public List<Project> findManageProjectList(String user_id);
    public List<Project> findBelongingProjects(String user_id);
    public List<Project> findProjectList(String user_id);
    public User findUserById(String id);
}
