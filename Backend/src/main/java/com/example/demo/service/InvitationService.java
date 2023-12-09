package com.example.demo.service;

import com.example.demo.domain.Invitation;
import com.example.demo.domain.Project;
import com.example.demo.domain.User;
import com.example.demo.repository.InvitationRepository;
import com.example.demo.repository.ProjectLikeRepository;

import javax.persistence.TypedQuery;
import java.util.List;

public class InvitationService {
    InvitationRepository invitation_rp;
    public InvitationService(InvitationRepository invitationRepository){
        invitation_rp = invitationRepository;
    }

    public void insert(Project project, User user){
        invitation_rp.insert(project, user);
    }

    public Long findIdByUser_id(String user_id){
        return invitation_rp.findIdByUser_id(user_id);
    }

    public void updateState(String user_id){
        Long invitation_id = invitation_rp.findIdByUser_id(user_id);
        invitation_rp.updateState(invitation_id);
    }

    public List<Project> findInviteProjectList(String userId) {
        List<Project> list = invitation_rp.findInviteProjectList(userId);
        return list;

    }
}
