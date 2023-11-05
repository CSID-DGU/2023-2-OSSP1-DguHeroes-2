package com.example.demo.service;

import com.example.demo.domain.Project;
import com.example.demo.domain.User;
import com.example.demo.repository.ProjectLikeRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ProjectLikeService {
    ProjectLikeRepository projectLike_rp;
    UserService userService;
    ProjectService projectService;
    ProjectLikeService projectLikeService;

    public ProjectLikeService(ProjectLikeRepository projectLikeRepository){
        projectLike_rp = projectLikeRepository;
    }

    public void projectLike(HttpServletRequest request, int projectId){
        String user_id = userService.findSessionId(request);
        User user = userService.findUserInfo(user_id);
        Project project = projectService.findByProjectId(projectId);
        projectLike_rp.insert(project, user);
    }


    public List<Project> findLikedProjects(String user_id) {
        List<Project> project = projectLikeService.findLikedProjects(user_id);
        return null;
    }
}
