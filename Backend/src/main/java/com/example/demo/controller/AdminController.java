package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.domain.Project;
import com.example.demo.domain.Questionnaire;
import com.example.demo.service.MemberService;
import com.example.demo.service.ProjectService;
import com.example.demo.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class AdminController {
    @Autowired
    QuestionnaireService questionnaireService;
    @Autowired
    MemberService memberService;
    @Autowired
    ProjectService projectService;


    @GetMapping("/admin/questionnaire/list")
    public List<Questionnaire> findQuestionnaire() {
        return questionnaireService.findAll();
    }

    @PatchMapping("/admin/questionnaire/update")
    public String updateQuestionnaire(Questionnaire questionnaire) {
        questionnaireService.update(questionnaire);
        return "success";
    }

    @PatchMapping("/admin/questionnaire/modify")
    public String modifyQuestionnaire(Questionnaire questionnaire) {
        questionnaireService.modify(questionnaire);
        return "success";
    }

    @GetMapping("/user/project/manage/member")
    public List<Member> findMember() {
        return memberService.findAll();
    }

    @PatchMapping("/user/project/manage/content")
    public String modify(Project project) {
        projectService.modify(project);
        return "success";
    }
}