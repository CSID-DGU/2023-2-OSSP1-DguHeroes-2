package com.example.demo.repository;

import com.example.demo.domain.Invitation;
import com.example.demo.domain.Project;
import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvitationRepository extends JpaRepository<Invitation, Integer> {

    public void insert(Project project, User user);

    public void updateState(Long invitation_id);

    public Long findIdByUser_id(String user_id);

    public Invitation findById(Long invitation_id);

    public List<Project> findInviteProjectList(String userId);
}
