package com.example.demo.service.recommendCalc;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Urs {
    // 프로젝트의 평균 userRoleScore를 계산해주는 메소드

    // 특정 프로젝트에서 특정 유저의 userRoleScore를 계산해주는 메소드
    static float calculateUrs(List<String[]> projectRequiredStack, int userId, String targetStack){
        float urs = 0;
        float distance=0;


        //구현: 유저(userId)가 사용할 줄 아는 기술스택 리스트를 USER_STACK 테이블에서 추출 -----------
        List<String[]> userAvailableStack =AccessDB.getUserAvailableStack(userId);

        List<String[]> intersection = new ArrayList<>();

        // 교집합 찾기
        for (String[] required : projectRequiredStack) {
            for (String[] available : userAvailableStack) {
                if (Arrays.equals(required, available)) {
                    intersection.add(required);
                    break;
                }
            }
        }

        System.out.println(projectRequiredStack.size());
        System.out.println(userAvailableStack.size());
        System.out.println(intersection.size());
        for (String[] element : intersection){
            System.out.println(element[0]);
            System.out.println(element[1]);
        }

        for(int i=0;i<intersection.size();i++){
            String[] element=intersection.get(i);
            if(targetStack.equals(element[0])){
                distance=0;
            }else{
                distance=GetDistance.getDistance(targetStack, element[0]);
            }
            float userStackScore=AccessDB.getUserStackScore(userId,element[1]);
            urs+=3/(3+distance)*userStackScore;
        }

        return urs;
    }

    // 두 리스트의 교집합을 반환하는 메소드
    public static <T> List<T> findIntersection(List<T> list1, List<T> list2) {
        List<T> intersection = new ArrayList<>();

        for (T element : list1) {
            if (list2.contains(element)) {
                intersection.add(element);
            }
        }

        return intersection;
    }
}