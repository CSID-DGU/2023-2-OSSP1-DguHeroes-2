package com.example.demo.controller.userScoreCalc;

import com.example.demo.dto.UserScoreDTO;
import com.example.demo.response.CommonResponse;
import com.example.demo.service.UserScoreService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserScoreController {

    private UserScoreService userScoreService;

    @PostMapping("/user/put/newscore")
    public CommonResponse getUserScore(@RequestBody List<UserScoreDTO> userScoreDTOList){

        // 여기서 받고 디비에 갱신
        userScoreService.putNewUserScore(userScoreDTOList);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus("SUCCESS");
        commonResponse.setMessage("유저 점수 수신 성공");
        return commonResponse;
    }

}
