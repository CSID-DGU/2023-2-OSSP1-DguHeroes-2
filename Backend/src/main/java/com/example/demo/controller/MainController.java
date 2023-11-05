
package com.example.demo.controller;

import com.example.demo.Main.MainInfo;
import com.example.demo.domain.Project;
import com.example.demo.response.CommonResponse;
import com.example.demo.response.ResponseService;
import com.example.demo.response.SingleResponse;
import com.example.demo.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
//@RequestMapping("/api/projects")
public class MainController {

    private ResponseService responseService;
    private UserService userService;
    private DevelopmentStackService developmentStackService;
    private ProjectService projectService;
    private InvitationService invitationService;
    private QuestionnaireService questionnaireService;
    private MemberService memberService;
    private com.example.demo.Main.MainInfo MainInfo;

    public MainController(ResponseService responseService, UserService userService, DevelopmentStackService developmentStackService, ProjectService projectService, InvitationService invitationService, MemberService memberService, QuestionnaireService questionnaireService) {
        this.responseService = responseService;
        this.userService = userService;
        this.developmentStackService = developmentStackService;
        this.projectService = projectService;
        this.invitationService = invitationService;
        this.memberService = memberService;
        this.questionnaireService = questionnaireService;
        this.responseService = responseService;
    }
    @GetMapping("/main/info")

    public SingleResponse <MainInfo> getMainInfo(HttpServletRequest request) {
        List<Project> recommendedProjects = projectService.getRecommendProject(request);
        List<Project> popularProjects = projectService.getPopularProjects();
        List<Project> recentProjects = projectService.getRecentProjects();
        CommonResponse commonResponse = new CommonResponse();

        MainInfo mainInfo = new MainInfo();
        mainInfo.setRecommend(recommendedProjects);
        mainInfo.setPopular(popularProjects);
        mainInfo.setRecent(recentProjects);

        return responseService.getSingleResponse(commonResponse, MainInfo);
    }
}
