package com.example.demo.controller;

import com.example.demo.domain.Project;
import com.example.demo.domain.ProjectStack;
import com.example.demo.domain.User;
import com.example.demo.response.CommonResponse;
import com.example.demo.response.ListResponse;
import com.example.demo.response.ResponseService;
import com.example.demo.response.SingleResponse;
import com.example.demo.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ProjectController {
    private ProjectService projectService;
    private ProjectLikeService projectLikeService;
    private UserService userService;
    private ApplyService applyService;

    private ProjectStackService projectStackService;

    private ResponseService responseService;

    public ProjectController(ProjectService projectService,
                             ProjectLikeService projectLikeService,
                             UserService userService,
                             ApplyService applyService,
                             ProjectStackService projectStackService,
                             ResponseService responseService)
    {
        this.projectService = projectService;
        this.projectStackService = projectStackService;
        this.userService = userService;
        this.applyService = applyService;
        this.projectStackService = projectStackService;
        this.responseService = responseService;
    }





    @PostMapping("/project/like")
    public String projectLike(HttpServletRequest request, int projectId){
        projectLikeService.projectLike(request, projectId);
        return null;
    }
    @GetMapping("/project/list")
    public<T> ListResponse<Project> findProjectList() {
        CommonResponse commonResponse = new CommonResponse();
        List<Project> list = projectService.findAllProjectList();
        if(list!=null){
            commonResponse.setStatus("SUCCESS");
            commonResponse.setMessage(null);
        }else{
            commonResponse.setStatus("FAILED");
            commonResponse.setMessage("리스트 출력 실패");
        }
        return responseService.getListResponse(commonResponse, list);
    }

    @PostMapping("/project/create")
    public SingleResponse<Project> projectCreate(@RequestBody Project project){
        CommonResponse commonResponse = new CommonResponse();
        List<ProjectStack> projectStackList = project.getProject_stacks();
        Project saved_project = projectService.insert(project);
        for(ProjectStack stack : projectStackList){
            stack.setProject(saved_project);
            projectStackService.insert(stack);
        }

        if(saved_project!=null){
            commonResponse.setStatus("SUCCESS");
            commonResponse.setMessage(null);
        }else{
            commonResponse.setStatus("FAILED");
            commonResponse.setMessage("프로젝트 생성 실패");
        }
        return responseService.getSingleResponse(commonResponse,project);
    }

    @PostMapping("/project/delete")
    public void projectDelete(int project_id){
        projectService.delete(project_id);
    }

    @GetMapping("/project/details")
    public<T> SingleResponse<Project> findProject(@RequestParam int project_id)throws JsonProcessingException {
        Project project = projectService.findByProjectId(project_id);
        CommonResponse commonResponse = new CommonResponse();
        if(project!=null){
            commonResponse.setStatus("SUCCESS");
            commonResponse.setMessage(null);
        }else{
            commonResponse.setStatus("FAILED");
            commonResponse.setMessage("프로젝트 상세내용 불러오기 실패");
        }
        return responseService.getSingleResponse(commonResponse,project);
    }

    @PostMapping("/project/apply")
    public CommonResponse apply(HttpServletRequest request, int project_id){
        Project project = new Project();
        project = projectService.findByProjectId(project_id);
        String user_id = userService.findSessionId(request);
        User user = userService.findUserInfo(user_id);
        applyService.insert(project, user);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus("SUCCESS");
        commonResponse.setMessage("지원 성공");
        return commonResponse;

    }
}
