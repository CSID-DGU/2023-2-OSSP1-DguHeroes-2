package com.example.demo.controller;

import com.example.demo.domain.*;
import com.example.demo.dto.HireInfo;
import com.example.demo.dto.JoinDTO;
import com.example.demo.dto.UserProjectList;
import com.example.demo.response.*;
import com.example.demo.service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@AllArgsConstructor
@RestController
public class UserController {

    private ResponseService responseService;
    private UserService userService;
    private ProjectService projectService;
    private InvitationService invitationService;
    private ProjectMemberService projectMemberService;
    private UserScoreService userScoreService;
    private ProjectLikeService projectLikeService;

    @Autowired
    private ApplyService applyService;

    // 회원가입
    @PostMapping("/user/join")
    public AdminResponse insert(@RequestBody JoinDTO joinDTO){
        System.out.println("구간1");
        System.out.println(joinDTO.getPassword());
        System.out.println(joinDTO.getNickname());
        System.out.println(joinDTO.getIntroduce());
        System.out.println(joinDTO.getGitid());
        System.out.println(joinDTO.getEmail());

        User saved_user = userService.join(joinDTO);

        Long id = saved_user.getId();

        CommonResponse commonResponse = new CommonResponse();
        if(saved_user!=null){
            commonResponse.setStatus("SUCCESS");
            commonResponse.setMessage("회원가입에 성공했습니다.");
        } else{
            commonResponse.setStatus("FAILED");
            commonResponse.setMessage("회원가입에 실패했습니다.");
        }

        return responseService.getAdminResponse(commonResponse, id);
    }

    // 로그인
    @PostMapping("/user/login") //ok
    public AdminResponse login(HttpServletRequest request, @RequestBody Map<String, String> loginData){
        String email = loginData.get("email");
        String password = loginData.get("password");
        Long id = userService.login(email, password);

        CommonResponse commonResponse = new CommonResponse();
        HttpSession session = request.getSession(); // Session이 있으면 가져오고 없으면 Session을 생성
        int isAdmin = -1;
        if (id > 0) {
            commonResponse.setStatus("SUCCESS");
            commonResponse.setMessage(null);
            session.setAttribute("id", id); // key:id, value:id
            System.out.println(session.getAttribute("id"));
        } else {
            commonResponse.setStatus("FAILED");
            commonResponse.setMessage("로그인 실패");
        }
        return responseService.getAdminResponse(commonResponse, id);
    }

    // 로그아웃
    @PostMapping("/user/logout")
    public CommonResponse logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object user = session.getAttribute("id");
        CommonResponse commonResponse = new CommonResponse();
        if (user != null) {
            session.invalidate();
            commonResponse.setStatus("SUCCESS");
            commonResponse.setMessage(null);
        } else {
            commonResponse.setStatus("FAILED");
            commonResponse.setMessage("세션이 이미 비어있음");
        }
        return commonResponse;
    }

    // 유저 세부정보
    @GetMapping("/user/info")
    public <T> SingleResponse<User> findUserInfo(HttpServletRequest request) {
        String user_id = userService.findSessionId(request);
        User user = userService.findUserInfo(user_id);
        CommonResponse commonResponse = new CommonResponse();
        if (user != null) {
            commonResponse.setStatus("SUCCESS");
            commonResponse.setMessage(null);
        } else {
            commonResponse.setStatus("FAILED");
            commonResponse.setMessage("회원 정보 가져오기 실패");
        }
        return responseService.getSingleResponse(commonResponse, user);
    }


    // ??
    @GetMapping("/user/project/manage/list")
    public <T> ListResponse<Project> manageProjectList(HttpServletRequest request) {
        String user_id = userService.findSessionId(request);
        CommonResponse commonResponse = new CommonResponse();
        List<Project> list = userService.findManageProjectList(user_id);
        if (list != null) {
            commonResponse.setStatus("SUCCESS");
            commonResponse.setMessage(null);
        } else {
            commonResponse.setStatus("FAILED");
            commonResponse.setMessage("리스트 출력 실패");
        }
        return responseService.getListResponse(commonResponse, list);
    }

    // ??
    @GetMapping("/user/project/list")
    public<T> SingleResponse<UserProjectList> findProjectList(HttpServletRequest request) {
        String user_id = userService.findSessionId(request);
        CommonResponse commonResponse = new CommonResponse();
        List<Project> belongingProjects = userService.findBelongingProjects(user_id);
        List<Project> pendingProjects = applyService.findPendingProjects(user_id); //pending리스트 빈출력 성공
        List<Project> likedProjects = projectLikeService.findLikedProjects(user_id);
        List<Project> endProjects = projectService.findEndProjects(user_id);

        UserProjectList list = new UserProjectList();
        list.setBelonging_project(belongingProjects);
        list.setPending_project(pendingProjects);
        list.setLike_project(likedProjects);
        list.setEnd_project(endProjects);


        if (!belongingProjects.isEmpty()) {
            commonResponse.setStatus("SUCCESS");
            commonResponse.setMessage(null);
        } else {
            commonResponse.setStatus("FAILED");
            commonResponse.setMessage("리스트 출력 실패");
        }

        return responseService.getSingleResponse(commonResponse, list);
    }

    // 프로젝트에 다른 유저 초대 하기
    @PostMapping("/user/project/manage/invite")
    public CommonResponse invite(int project_id, Long user_id) {
        Project project = projectService.findByProjectId(project_id);
        User user = userService.getById(user_id.intValue());
        invitationService.insert(project, user);
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus("SUCCESS");
        commonResponse.setMessage("초대 성공");
        return commonResponse;
    }

    // 프로젝트 초대 수락/거부
    @GetMapping("/user/project/accept")
    public CommonResponse accept(HttpServletRequest request, int project_id, String status){
        String user_id = userService.findSessionId(request);
        User user = userService.findUserInfo(user_id);
        Project project = projectService.findByProjectId(project_id);


        CommonResponse commonResponse = new CommonResponse();
        if (status.equals("APPROVE")) {
            ProjectMember projectMember = ProjectMember.builder()
                    .project(project)
                    .position("MEMBER")
                    .build();
            projectMemberService.insert(projectMember);
            invitationService.updateState(user_id);
            commonResponse.setStatus("SUCCESS");
            commonResponse.setMessage("프로젝트 초대 수락됨");
        } else {
            commonResponse.setStatus("SUCCESS");
            commonResponse.setMessage("프로젝트 초대 거절됨");
        }
        return commonResponse;
    }

    //status +
    @PostMapping("/user/project/manage/hire")
    public CommonResponse hire(@RequestBody HireInfo hireInfo){
        int project_id = hireInfo.getProject_id();
        Long user_id = hireInfo.getUser_id();
        String state = hireInfo.getState();

        User user = userService.getById(user_id.intValue());
        Project project = projectService.findByProjectId(project_id);
        CommonResponse commonResponse = new CommonResponse();
        //멤버테이블에 저장
        if(state.equals("APPROVE")){
            ProjectMember projectMember = ProjectMember.builder()
                    .project(project)
                    .position("MEMBER")
                    .build();

            projectMemberService.insert(projectMember);
            //지원테이블의 상태를 "pending" 에서 "BELONG" 으로 변경
            applyService.updateState(project_id);
            commonResponse.setStatus("SUCCESS");
            commonResponse.setMessage("프로젝트 지원 수락됨");
        }else{
            commonResponse.setStatus("SUCCESS");
            commonResponse.setMessage("프로젝트 지원 거절됨");
        }
        return commonResponse;
    }


    // 프로젝트 관리페이지에서 팀장에게 지원한 유저 목록을 보여줌
    @GetMapping("/user/project/manage/apply")
    public<T> ListResponse<User> applyUserList(HttpServletRequest request,int project_id) {
        Project project = projectService.findByProjectId(project_id);
//      String user_id = userService.findSessionId(request);
//      User user = userService.findUserInfo(user_id);
        CommonResponse commonResponse = new CommonResponse();
        List<User> list = applyService.findApplyUsers(project_id);

        if(list!=null){
            commonResponse.setStatus("SUCCESS");
            commonResponse.setMessage(null);
        }else{
            commonResponse.setStatus("FAILED");
            commonResponse.setMessage("리스트 출력 실패");
        }
        return responseService.getListResponse(commonResponse, list);

    }

    @GetMapping("user/project/invite/list")
    public<T> ListResponse<Project> inviteProjectList(HttpServletRequest request){
        String user_id = userService.findSessionId(request);
        CommonResponse commonResponse = new CommonResponse();
        List<Project> list = invitationService.findInviteProjectList(user_id);

        if(list!=null){
            commonResponse.setStatus("SUCCESS");
            commonResponse.setMessage(null);
        }else{
            commonResponse.setStatus("FAILED");
            commonResponse.setMessage("리스트 출력 실패");
        }
        return responseService.getListResponse(commonResponse, list);
    }

}







