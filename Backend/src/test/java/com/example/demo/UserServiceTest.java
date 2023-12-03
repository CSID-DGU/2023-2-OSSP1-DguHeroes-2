package com.example.demo;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.DevelopmentStackService;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.web.WebAppConfiguration;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@WebAppConfiguration
@Rollback(false)
public class UserServiceTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    DevelopmentStackService developmentStackService;

    @Test
    public void join(){
        User user = new User();
        user.setNickname("ST_nickname4");
        user.setId("ST_id4");
        user.setPassword("ST_pw4");
        //user.setProfile("test file");
        user.setIntroduce("ST_introduce4");

        DevelopmentStack developmentStack = new DevelopmentStack();
        developmentStack.setDevelopment_stack("웹 프론트엔드");
        developmentStack.setUser(user);
        developmentStack.setGrade(3);
        developmentStack.setVersion(2);

        int id_du_ck = userService.duplicationCheckId(user.getId());
        int nick_du_ck = userService.duplicationCheckNickname(user.getNickname());
        if(id_du_ck==1) {
            System.out.println("이미 존재하는 id 입니다."); return;
        } else if(nick_du_ck==1){
            System.out.println("이미 존재하는 nickname 입니다."); return;
        }else {
            userService.join(user);
            developmentStackService.insert(developmentStack);
        }
    }

    @Test
    public void findUser(){
        User user = userService.findUserInfo("ST_Id2");
        if(user==null) System.out.println("회원정보없음");
        else {
            System.out.println(user.getUser_id());
            System.out.println(user.getNickname());
            System.out.println(user.getPassword());
            System.out.println(user.getIntroduce());
        }
    }

    @Test
    public void deleteUser(){
        int delete_result = userService.deleteUser("ST_id3");
    }

    @Test
    public void login(){
        int login_result = userService.login("ST_id1","ST_pw1");
        if(login_result==1) System.out.println("로그인 성공");
        else System.out.println("로그인 실패");
    }

}
