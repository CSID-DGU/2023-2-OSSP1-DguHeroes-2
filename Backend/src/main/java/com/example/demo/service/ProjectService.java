package com.example.demo.service;

import com.example.demo.domain.Project;
import com.example.demo.domain.User;
import com.example.demo.repository.ApplyRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.response.CommonResponse;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.*;

@Transactional
@Service
public class ProjectService {
    ProjectRepository project_rp;
    ApplyRepository apply_rp;
    UserService userService;

    public ProjectService(ProjectRepository projectRepository) {
        project_rp = projectRepository;
    }

    public Project findByProjectId(int projectId) {
        Project project = project_rp.findByProjectId(projectId);
        return project;
    }


    public Project insert(Project project) {
        return project_rp.insert(project);
    }

    public int delete(int project_id) {
        return project_rp.delete(project_id);
    }

    public void modify(Project project) {
        Optional<Project> projectOptional = project_rp.findById(project.getId());
        if (projectOptional.isPresent()) {
            Project project1 = projectOptional.get();
            // update only present fields
            if (project.getContent() != null) project1.setContent(project.getContent());
            if (project.getTitle() != null) project1.setTitle(project.getTitle());
            if (project.getProject_type() != null) project1.setProject_type(project.getProject_type());
            project_rp.save(project1);
        }
    }
    public List<Project> findAll() {
        return project_rp.findAll();
    }

    public List<Project> getPopularProjects() {
        List<Project> popularProject = project_rp.findPopularProject();
        return popularProject;

    }

    public List<Project> getRecentProjects() {
        List<Project> allProjects = project_rp.findAll();
        allProjects.sort(Comparator.comparing(Project::getCreatedAt).reversed());

        int maxCount = Math.min(4, allProjects.size()); // 최대 4개의 요소만 선택
        return allProjects.subList(0, maxCount);
    }

    // (기존 프로젝트의) 매칭 알고리즘 순서도
    // 1. 모든 프로젝트를 순회하면서, 각 프로젝트에 대해 사용자의 개발 스택과 등급이 일치하는 프로젝트를 temp_list에 추출한다.
    // 2. (case1) temp_list의 크기가 4이하인 경우 temp_list의 모든 요소를 recommended_project_list에 추가한다.
    // (case2) temp_list의 크기가 4초과인 경우 무작위로 4개의 프로젝트를 선택하여 recommended_project_list에 추가한다.
    // 3. 결과적으로 recommended_project_list를 반환

    public List<Project> getRecommendProject(HttpServletRequest request){
//        List<Project> temp_list = new ArrayList<>();
//        HttpSession session = request.getSession();
//        String id = (String)session.getAttribute("id");
//        User user = userService.findUserById(id);
//        DevelopmentStack development_stack = userService.develop_rp.findDevelopmentStack(user.getId());
//        String stack = development_stack.getDevelopment_stack();
//        int grade = development_stack.getGrade();
//        List<Project> list = project_rp.findAllProjectList();
//        for(Project project : list){
//            List<ProjectStack> stack_list = project.getProject_stacks();
//            for(ProjectStack project_stack : stack_list){
//                if(project_stack.getDevelopment_stack().equals(development_stack) && project_stack.getRequire_grade() == grade){
//                    temp_list.add(project);
//                }
//            }
//        }
//
//        List<Project> recommended_project_list = new ArrayList<>();
//
//        if(temp_list.size()<=4){
//            for(int i=0;i<temp_list.size();i++){
//                recommended_project_list.add(temp_list.get(i));
//            }
//        }
//
//        while(temp_list.size()>4){
//            List<Integer> numbers = new ArrayList<>();
//            for (int i = 1; i <= temp_list.size(); i++) {
//                numbers.add(i);
//            }
//
//            List<Integer> randomNumbers = new ArrayList<>();
//            Random random = new Random();
//            for (int i = 0; i < 4; i++) {
//                int index = random.nextInt(numbers.size());
//                randomNumbers.add(numbers.remove(index));
//            }
//            for(Integer num : randomNumbers){
//                recommended_project_list.add(temp_list.get(num));
//            }
//        }
//        CommonResponse commonResponse = new CommonResponse();
//        commonResponse.setStatus("SUCCESS");
//        commonResponse.setMessage(null);
//        return recommended_project_list;
        return null;
    }
    public List<Project> findEndProjects(String user_id) {
        List<Project> list = project_rp.findEndProjects(user_id);
        return list;
    }


    public List<Project> findAllProjectList() {
        List<Project> list = project_rp.findAllProjectList();
        return list;
    }
}


