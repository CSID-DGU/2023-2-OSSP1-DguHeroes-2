package com.example.demo.controller.userRecommendCalc;

import com.example.demo.response.CommonResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@RestController
public class UserRecommendController {

    @GetMapping("/user/project/manage/recommend")
    public void recommendUser(@RequestBody Long project_id, String stackName) {


    }
}
