package com.example.demo.service.recommendCalc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AccessDB {

    private static String jdbcUrl;
    private static String DBusername;
    private static String DBpassword;

    @Value("${spring.datasource.url}")
    public void setJdbcUrl(String jdbcUrl){
        AccessDB.jdbcUrl=jdbcUrl;
    }

    @Value("${spring.datasource.username}")
    public void setDBusername(String dBusername){
        AccessDB.DBusername=dBusername;
    }

    @Value("${spring.datasource.password}")
    public void setDBpassword(String dBpassword){
        AccessDB.DBpassword=dBpassword;
    }

    @PostConstruct
    static float getTeamScore(int projId){
        float avgScore = 0;

        try (Connection connection = DriverManager.getConnection(jdbcUrl, DBusername, DBpassword)) {
            // 프로젝트 ID와 projId가 일치하는 데이터의 개수와 user_role_score의 합을 조회하는 SQL 쿼리
            String sql = "SELECT COUNT(*) AS data_count, SUM(user_role_score) AS score_sum " +
                    "FROM project_member " +
                    "WHERE project_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, projId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        float dataCount = resultSet.getFloat("data_count");
                        float scoreSum = resultSet.getFloat("score_sum");

                        System.out.println("데이터 개수: " + dataCount);
                        System.out.println("user_role_score 합계: " + scoreSum);

                        avgScore=scoreSum/dataCount;

                        return avgScore;

                    } else {
                        System.out.println("일치하는 데이터가 없습니다.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    static List<String> stacks = Arrays.asList(
            ".NET", "ASP.NET", "Angular", "AngularJS", "Apache Spark", "Blazor", "C", "C#",
            "C++", "Dart", "Django", "Electron", "Express", "Flask", "Flutter", "Go",
            "HTML/CSS", "Java", "JavaScript", "jQuery", "Keras", "Kotlin", "Laravel", "Lua",
            "NestJS", "Next.js", "Node.js", "OpenGL", "Opencv", "PHP", "Pandas", "PyTorch",
            "Python", "Qt", "R", "RabbitMQ", "React", "React Native", "Ruby", "Ruby on Rails",
            "Rust", "scikit-Learn", "SpringBoot", "Svelte", "Swift", "SwiftUI", "TensorFlow",
            "Torch", "TypeScript", "Vue.js"
    );

    static List<String> stacksForDB = Arrays.asList(
            "dot_net", "asp_net", "angular", "angular_js", "apache_spark", "blazor", "c", "c_sharp",
            "cpp", "dart", "django", "electron", "express", "flask", "flutter", "go",
            "html_css", "java", "java_script", "jquery", "keras", "kotlin", "laravel", "lua",
            "nest_js", "next_js", "node_js", "open_gl", "open_cv", "php", "pandas", "py_torch",
            "python", "qt", "r", "rabbit_mq", "react", "react_native", "ruby", "ruby_on_rails",
            "rust", "scikit_Learn", "spring_boot", "svelte", "swift", "swift_ui", "tensor_flow",
            "torch", "type_script", "vue_js"
    );

    public static String findStackForDB(String stack) {
        // 입력으로 주어진 stack이 stacks에 있는지 확인
        int index = stacks.indexOf(stack);

        if (index != -1 && index < stacksForDB.size()) {
            // 해당하는 index의 stacksForDB의 값을 반환
            return stacksForDB.get(index);
        } else {
            // 해당하는 stack이 stacks에 없거나 index가 범위를 벗어나면 null을 반환
            return null;
        }
    }

    static List<String[]> getUserAvailableStack(int userId){
        String stacks_user="";

        List<String[]> requiredStack=new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(jdbcUrl, DBusername, DBpassword)) {
            // 프로젝트 ID와 projId가 일치하는 데이터의 개수와 user_role_score의 합을 조회하는 SQL 쿼리
            String sql = "SELECT stacks FROM user WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, userId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        // stacks 값을 가져오기
                        stacks_user = resultSet.getString("stacks");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(int i=0;i<50;i++){
            if(stacks_user.length()==50){
                if(stacks_user.charAt(i)=='8'){
                    String[] data= new String[2];
                    data[0]=stacks.get(i);
                    data[1]=stacksForDB.get(i);
                    requiredStack.add(data);
                }
            }
        }

        return requiredStack;
    }

    static List<String[]> getProjRequiredStack(int projId){
        String stacks_front="";
        String stacks_back="";
        String stacks_etc="";

        List<String[]> requiredStack=new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(jdbcUrl, DBusername, DBpassword)) {
            // 프로젝트 ID와 projId가 일치하는 데이터의 개수와 user_role_score의 합을 조회하는 SQL 쿼리
            String sql = "SELECT stacks FROM project_front WHERE project_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, projId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        // stacks 값을 가져오기
                        stacks_front = resultSet.getString("stacks");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(jdbcUrl, DBusername, DBpassword)) {
            // 프로젝트 ID와 projId가 일치하는 데이터의 개수와 user_role_score의 합을 조회하는 SQL 쿼리
            String sql = "SELECT stacks FROM project_back WHERE project_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, projId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        // stacks 값을 가져오기
                        stacks_back = resultSet.getString("stacks");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(jdbcUrl, DBusername, DBpassword)) {
            // 프로젝트 ID와 projId가 일치하는 데이터의 개수와 user_role_score의 합을 조회하는 SQL 쿼리
            String sql = "SELECT stacks FROM project_etc WHERE project_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, projId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        // stacks 값을 가져오기
                        stacks_etc = resultSet.getString("stacks");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(int i=0;i<50;i++){
            if(stacks_front.length()==50){
                if(stacks_front.charAt(i)=='8'){
                    String[] data= new String[2];
                    data[0]=stacks.get(i);
                    data[1]=stacksForDB.get(i);
                    requiredStack.add(data);
                }
            }

            if(stacks_back.length()==50){
                if(stacks_back.charAt(i)=='8'){
                    String[] data= new String[2];
                    data[0]=stacks.get(i);
                    data[1]=stacksForDB.get(i);
                    requiredStack.add(data);
                }
            }

            if(stacks_etc.length()==50){
                if(stacks_etc.charAt(i)=='8'){
                    String[] data= new String[2];
                    data[0]=stacks.get(i);
                    data[1]=stacksForDB.get(i);
                    requiredStack.add(data);
                }
            }
        }

        return requiredStack;
    }

    static float getUserStackScore(int userId, String stack){
        float stackscore=0;

        try (Connection connection = DriverManager.getConnection(jdbcUrl, DBusername, DBpassword)) {
            // 프로젝트 ID와 projId가 일치하는 데이터의 개수와 user_role_score의 합을 조회하는 SQL 쿼리
            String sql = "SELECT score FROM "+ stack +" WHERE user_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, userId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        stackscore=resultSet.getFloat("score");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stackscore;
    }

    static ArrayList<Integer>  getStackUserId(String stack){
        ArrayList<Integer> userId = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(jdbcUrl, DBusername, DBpassword)) {
            // 프로젝트 ID와 projId가 일치하는 데이터의 개수와 user_role_score의 합을 조회하는 SQL 쿼리
            String sql = "SELECT user_id FROM "+ stack;

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        userId.add(resultSet.getInt("user_id"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId;
    }
}
