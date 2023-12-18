package com.example.demo.service;

import com.example.demo.domain.Project;
import com.example.demo.domain.User;
import com.example.demo.dto.JoinDTO;
import com.example.demo.repository.ApplyRepository;
import com.example.demo.repository.UserJPARepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class UserService {
    UserRepository user_rp;

    @Autowired
    UserJPARepository userJPARepository;

    ApplyRepository apply_rp;
    public UserService(@Qualifier("userRepository")UserRepository user_rp,
                       @Qualifier ("applyRepository")ApplyRepository apply_rp)
    {
        this.user_rp = user_rp;

        this.apply_rp=apply_rp;
    }

    public User join(JoinDTO joinDTO){
        System.out.println("구간2");
        User user = new User();
        user.setEmail(joinDTO.getEmail());
        user.setIntroduce(joinDTO.getIntroduce());
        user.setNickname(joinDTO.getNickname());
        user.setPassword(joinDTO.getPassword());
        user.setGithubId(joinDTO.getGitid());

        return userJPARepository.save(user);
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

    // 로그인
    public Long login(String email, String password){
        return user_rp.login(email, password);
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


    public User findUserById(String id){
        return user_rp.findUserById(id);
    }

}

