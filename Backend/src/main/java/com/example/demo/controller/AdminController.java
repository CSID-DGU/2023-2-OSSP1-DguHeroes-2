package com.example.demo.controller;


import com.example.demo.domain.Project;
import com.example.demo.service.ProjectMemberService;
import com.example.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AdminController {

    @Autowired
    ProjectMemberService memberService;
    @Autowired
    ProjectService projectService;




    @PatchMapping("/user/project/manage/content")
    public String modify(Project project) {
        projectService.modify(project);
        return "success";
    }
}