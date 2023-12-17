package com.example.demo.service.recommendCalc;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Urs {

    static List<String> stacks = Arrays.asList(
            ".NET", "ASP.NET", "Angular", "AngularJS", "Apache Spark", "Blazor", "C", "C#",
            "C++", "Dart", "Django", "Electron", "Express", "Flask", "Flutter", "Go",
            "HTML/CSS", "Java", "JavaScript", "jQuery", "Keras", "Kotlin", "Laravel", "Lua",
            "NestJS", "Next.js", "Node.js", "OpenGL", "Opencv", "PHP", "Pandas", "PyTorch",
            "Python", "Qt", "R", "RabbitMQ", "React", "React Native", "Ruby", "Ruby on Rails",
            "Rust", "scikit-Learn", "SpringBoot", "Svelte", "Swift", "SwiftUI", "TensorFlow",
            "Torch", "TypeScript", "Vue.js"
    );

//    static float getTeamScore(int projId){
//        float avgScore = 0;
//
//        try (Connection connection = DriverManager.getConnection(jdbcUrl, DBusername, DBpassword)) {
//            // 프로젝트 ID와 projId가 일치하는 데이터의 개수와 user_role_score의 합을 조회하는 SQL 쿼리
//            String sql = "SELECT COUNT(*) AS data_count, SUM(user_role_score) AS score_sum " +
//                    "FROM project_member " +
//                    "WHERE project_id = ?";
//
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                preparedStatement.setInt(1, projId);
//
//                try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                    if (resultSet.next()) {
//                        float dataCount = resultSet.getFloat("data_count");
//                        float scoreSum = resultSet.getFloat("score_sum");
//
//                        System.out.println("데이터 개수: " + dataCount);
//                        System.out.println("user_role_score 합계: " + scoreSum);
//
//                        avgScore=scoreSum/dataCount;
//
//                        return avgScore;
//
//                    } else {
//                        System.out.println("일치하는 데이터가 없습니다.");
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }

//    static List<String> getUserAvailableStack(int userId){
//        String stacks_user="";
//        List<String> requiredStack= new ArrayList<>();
//
//        try (Connection connection = DriverManager.getConnection(jdbcUrl, DBusername, DBpassword)) {
//            // 프로젝트 ID와 projId가 일치하는 데이터의 개수와 user_role_score의 합을 조회하는 SQL 쿼리
//            String sql = "SELECT stacks FROM user WHERE id = ?";
//
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                preparedStatement.setInt(1, userId);
//
//                try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                    while (resultSet.next()) {
//                        // stacks 값을 가져오기
//                        stacks_user = resultSet.getString("stacks");
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        for(int i=0;i<50;i++){
//            if(stacks_user.length()==50){
//                if(stacks_user.charAt(i)=='8'){
//                    requiredStack.add(stacks.get(i));
//                }
//            }
//        }
//
//        return requiredStack;
//    }
//
//    static List<String> getProjRequiredStack(int projId){
//        String stacks_front="";
//        String stacks_back="";
//        String stacks_etc="";
//        List<String> requiredStack= new ArrayList<>();
//
//        try (Connection connection = DriverManager.getConnection(jdbcUrl, DBusername, DBpassword)) {
//            // 프로젝트 ID와 projId가 일치하는 데이터의 개수와 user_role_score의 합을 조회하는 SQL 쿼리
//            String sql = "SELECT stacks FROM project_front WHERE project_id = ?";
//
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                preparedStatement.setInt(1, projId);
//
//                try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                    while (resultSet.next()) {
//                        // stacks 값을 가져오기
//                        stacks_front = resultSet.getString("stacks");
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        try (Connection connection = DriverManager.getConnection(jdbcUrl, DBusername, DBpassword)) {
//            // 프로젝트 ID와 projId가 일치하는 데이터의 개수와 user_role_score의 합을 조회하는 SQL 쿼리
//            String sql = "SELECT stacks FROM project_back WHERE project_id = ?";
//
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                preparedStatement.setInt(1, projId);
//
//                try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                    while (resultSet.next()) {
//                        // stacks 값을 가져오기
//                        stacks_back = resultSet.getString("stacks");
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        try (Connection connection = DriverManager.getConnection(jdbcUrl, DBusername, DBpassword)) {
//            // 프로젝트 ID와 projId가 일치하는 데이터의 개수와 user_role_score의 합을 조회하는 SQL 쿼리
//            String sql = "SELECT stacks FROM project_etc WHERE project_id = ?";
//
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                preparedStatement.setInt(1, projId);
//
//                try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                    while (resultSet.next()) {
//                        // stacks 값을 가져오기
//                        stacks_etc = resultSet.getString("stacks");
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        for(int i=0;i<50;i++){
//            if(stacks_front.length()==50){
//                if(stacks_front.charAt(i)=='8'){
//                    requiredStack.add(stacks.get(i));
//                }
//            }
//
//            if(stacks_back.length()==50){
//                if(stacks_back.charAt(i)=='8'){
//                    requiredStack.add(stacks.get(i));
//                }
//            }
//
//            if(stacks_etc.length()==50){
//                if(stacks_etc.charAt(i)=='8'){
//                    requiredStack.add(stacks.get(i));
//                }
//            }
//        }
//
//        return requiredStack;
//    }
//
//    static float getUserStackScore(int userId, String stack){
//        float stackscore=0;
//
//        try (Connection connection = DriverManager.getConnection(jdbcUrl, DBusername, DBpassword)) {
//            // 프로젝트 ID와 projId가 일치하는 데이터의 개수와 user_role_score의 합을 조회하는 SQL 쿼리
//            String sql = "SELECT score FROM "+ stack +" WHERE userId = ?";
//
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                preparedStatement.setInt(1, userId);
//
//                try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                    while (resultSet.next()) {
//                        // stacks 값을 가져오기
//                        stacks_front = resultSet.getString("stacks");
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return stackscore;
//    }
}
