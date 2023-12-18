package com.example.demo.controller.userRecommendCalc;

import com.example.demo.response.CommonResponse;
import com.example.demo.service.recommendCalc.Recommend;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class UserRecommendController {

    @GetMapping("/user/project/manage/recommend")
    public List<Long> recommendUser(@RequestBody Long project_id, String stackName) {
        int proj_id=(int)project_id.longValue();

        List<Integer> result= Recommend.projectBaseSearch(proj_id,stackName);

        List<Long> resultAsLong = result.stream()
                .map(Long::valueOf)
                .collect(Collectors.toList());

        return resultAsLong;
    }

    public List<Long> recommendTeam(@RequestBody Long user_id, String stackName) {
        int userId=(int)user_id.longValue();

        List<Integer> result= Recommend.userBaseSearch(userId,stackName);

        List<Long> resultAsLong = result.stream()
                .map(Long::valueOf)
                .collect(Collectors.toList());

        return resultAsLong;
    }

}