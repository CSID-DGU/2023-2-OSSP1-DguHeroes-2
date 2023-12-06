/*package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.InvitationRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class MemberService {
    MemberRepository member_rp;
    public MemberService(MemberRepository memberRepository){
        member_rp = memberRepository;
    }

    public void insert(Member member){
        member_rp.insert(member);
    }
}*/
package com.example.demo.service;

import com.example.demo.domain.ProjectMember;
import com.example.demo.repository.ProjectMemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectMemberService {
    ProjectMemberRepository member_rp;
    public ProjectMemberService(ProjectMemberRepository projectMemberRepository){
        member_rp = projectMemberRepository;
    }

    @Transactional
    public void insert(ProjectMember projectMember){
        member_rp.insert(projectMember);
    }

    public List<ProjectMember> findAll(){
        return member_rp.findAll();
    }
}
