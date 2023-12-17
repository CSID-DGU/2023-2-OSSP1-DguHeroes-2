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

    // User 숙련도값 갱신 요청 -> 수정 필요
    @PostMapping("user/request/newscore")
    public CommonResponse requestScore(@RequestBody Long user_id) {

        String github_id = userScoreService.findGithubId(user_id);
        // 프론트와 통신 user_id 받아서 숙련도 갱신 요청 보내기
        userScoreService.postRequestUserScore(github_id);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus("SUCCESS");
        commonResponse.setMessage("숙련도 값 갱신 요청 성공");
        return commonResponse;
    }
}
