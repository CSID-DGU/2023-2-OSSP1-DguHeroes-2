package com.example.demo.service;

import com.example.demo.domain.Project;
import com.example.demo.domain.User;
import com.example.demo.repository.ApplyRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ApplyService {
    ApplyRepository apply_rp;
    public ApplyService(@Qualifier("applyRepository")ApplyRepository apply_rp){
        this.apply_rp=apply_rp;
    }

    public void insert(Project project, User user){
        apply_rp.insert(project,user);
    }

    public Long findIdByProject_id(int project_id){
        return apply_rp.findIdByProject_id(project_id);
    }

    public void updateState(int project_id){
        Long apply_id = apply_rp.findIdByProject_id(project_id);
        apply_rp.updateState(apply_id);
    }

    public List<User> findApplyUsers(int project_id) {
        List<User> list = apply_rp.findApplyUsers(project_id);
        return list;
    }

    public List<Project> findPendingProjects(String user_id) {
        List<Project> list = apply_rp.findPendingProjects(user_id);
        return list;
    }
}
