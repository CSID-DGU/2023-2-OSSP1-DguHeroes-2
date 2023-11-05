package com.example.demo.service;

import com.example.demo.domain.DevelopmentStack;
import com.example.demo.domain.Project;
import com.example.demo.domain.Questionnaire;
import com.example.demo.domain.User;
import com.example.demo.repository.ApplyRepository;
import com.example.demo.repository.DevelopmentStackRepository;
import com.example.demo.repository.QuestionnaireRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class UserService {
    UserRepository user_rp;
    DevelopmentStackRepository develop_rp;
    QuestionnaireRepository questionnaire_rp;
    ApplyRepository apply_rp;
    public UserService(@Qualifier("userRepository")UserRepository user_rp,
                       @Qualifier("developmentStackRepository")DevelopmentStackRepository develop_rp,
                       @Qualifier("questionnaireRepository")QuestionnaireRepository questionnaire_rp,
                       @Qualifier ("applyRepository")ApplyRepository apply_rp)
    {
        this.user_rp = user_rp;
        this.develop_rp = develop_rp;
        this.questionnaire_rp = questionnaire_rp;
        this.apply_rp=apply_rp;
    }

    public User join(User user){
        User result_user = user_rp.save(user);
        return result_user;
    }
    public int duplicationCheckId(String id){return user_rp.duplicationCheckId(id);}

    public int duplicationCheckNickname(String nickname){return user_rp.duplicationCheckNickname(nickname);}

    public User findUserInfo(String id){
        return user_rp.findByid(id);
    }

    public int deleteUser(String id){
        if(user_rp.findByid(id)!=null){
            user_rp.deleteByid(id);
            return 1;
        }else {
            System.out.println("존재하지 않는 유저입니다.");
            return 0;
        }
    }

    public int login(String id, String pw){return user_rp.login(id,pw);}

    public Questionnaire findQuestionnaire(String developmentsStack){
        return questionnaire_rp.findQuestionnaire(developmentsStack);
    }

    public String findSessionId(HttpServletRequest request){
        HttpSession session = request.getSession();
        String user_id = (String)session.getAttribute("id");
        return user_id;
    }

    public void deleteSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
    }

    public List<Project> findManageProjectList(String user_id){
        List<Project> list = user_rp.findManageProjectList(user_id);
        return list;
    }

    public List<Project> findBelongingProjects(String user_id) {
        List<Project> list = user_rp.findBelongingProjects(user_id);
        return list;
    }

    public User getById(Integer user_id){
        return user_rp.getReferenceById(user_id);
    }


    public List<Project> findProjectList(String user_id) {
        List<Project> list = user_rp.findProjectList(user_id);
        return list;
    }

    public String findUserStackById(String user_id){
        return user_rp.findUserStackById(user_id);
    }

    public List<User> findUsersByStack(String stack){
        return user_rp.findUsersByStack(stack);
    }

    public int findGradeByUserId(User user){
        return user_rp.findGradeByUserId(user);
    }


    public User findUserById(String id){
        return user_rp.findUserById(id);
    }

    public int findIsAdminById(String id){
        return user_rp.findIsAdminById(id);
    }
}

