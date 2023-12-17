package com.example.demo.service.recommendCalc;

import java.util.*;

public class Recommend {

//    public void projectBaseSearch(int teamId, String stack){
//        float teamScore=AccessDB.getTeamScore(teamId);
//        List<String> projectRequiredStack =AccessDB.getProjRequiredStack(teamId);
//
//
//
//
//
//    }
//
//    public static void main(String[] args) {
//        float a = AccessDB.getTeamScore(2);
//        System.out.println("getTeamScore() 테스트 : " + a);
//
//        List<String> requiredStack = AccessDB.getProjRequiredStack(2);
//
//        System.out.println(requiredStack);
//
//
///*
//        float b = urs.calculateUrs(123,456,"Java");
//        System.out.println("calculateUrs() 테스트 : " + b);
//
//        System.out.println("\n--- getUsers() --------------------------------------------\n");
//
//        int result1[] = getUsers(20, 10, 1, "Java");
//
//        for(int i = 0; i < result1.length; i++){
//            System.out.println("최종 추천 리스트 > " + i + ", userId : " + Math.round(result1[i]));
//        }
//
//        System.out.println("\n--- getProjs() --------------------------------------------\n");
//
//        int result2[] = getProjs(20, 10, 1, "Java");
//
//        for(int i = 0; i < result2.length; i++){
//            System.out.println("최종 추천 리스트 > " + i + ", projectId : " + Math.round(result2[i]));
//        }
// */
//    }
//
//    // 프로젝트 팀장이 특정 기술 스택에 대하여 팀원을 추천 받는 메소드 (output: 추천 팀원들의 userId 배열)
//    static int[] getUsers(int sampleSize, int recommendSize, int projId, String targetStack){
//        Random random = new Random();   //테스트 데이터셋 생성을 위한 Random 객체 생성
//
//        float avgScore = 3 + random.nextFloat() * (10 - 3);    //PROJECT_INFO.avg_score(id=proj_id) 테스트 데이터셋 생성
//        int testSize = 100;    //테스트를 위한 targetStack 테이블의 데이터 수
//        float proficiency[][] = new float[testSize][2]; //<targetStack> 숙련도 테이블 데이터가 저장되는 배열
//        float gapsProjUsers[][] = new float[testSize][3]; //유저의 데이터와 |PROJECT_INFO.avg_score(id=proj_id) - <target_stack>.proficiency|값이 저장되는 배열
//        float selectedUsers[][] = new float[sampleSize][2]; //최종 상위 추천 유저의 데이터가 recommendSize만큼 저장되는 배열
//        int result[] = new int[recommendSize];   //getUsers() 함수 최종 출력값 (userId)
//
//        //구현 1: <targetStack 숙련도 테이블>에서 |PROJECT_INFO.avg_score(id=proj_id) - <target_stack>.proficiency|의 값이 작은 순으로 sampleSize만큼 추출 -------------
//        //1000부터 9999까지 겹치지 않는 testSize개의 수 생성
//        Set<Integer> uniqueNumbers = new HashSet<>();
//        while (uniqueNumbers.size() < testSize) {
//            int randomNumber = random.nextInt(9000) + 1000;
//            uniqueNumbers.add(randomNumber);
//        }
//
//        int i = 0;
//        //<targetStack>.proficiency 테스트 데이터셋 생성
//        for (int user_id : uniqueNumbers) {
//            proficiency[i][0] = user_id;
//            proficiency[i][1] = 3 + random.nextFloat() * (10 - 3);
//            System.out.println(i + ", userId : " + Math.round(proficiency[i][0]) + ", 숙련도 : " + proficiency[i][1]);
//            i+=1;
//        }
//        System.out.println("\n");
//
//        //|avgUrs - <targetStack>.proficiency|를 구하여 sampleSize만큼 샘플링
//        for(i = 0; i < testSize; i++){
//            gapsProjUsers[i][0] = proficiency[i][0];
//            gapsProjUsers[i][1] = Math.abs(avgScore-proficiency[i][1]);
//            gapsProjUsers[i][2] = proficiency[i][1];
//            System.out.println(i + ", userId : " + Math.round(gapsProjUsers[i][0]) + ", |avgUrs - 숙련도| : " + gapsProjUsers[i][1]);
//        }
//        System.out.println("\n");
//
//        Arrays.sort(gapsProjUsers, Comparator.comparingDouble(arr -> arr[1]));
//
//        for(i = 0; i < sampleSize; i++){
//            System.out.println("정렬됨 > " + i + ", userId : " + Math.round(gapsProjUsers[i][0]) + ", |avgUrs - 숙련도| : " + gapsProjUsers[i][1]);
//        }
//        System.out.println("\n");
//        //--------------------------------------------------------------------------------------------------------------------------------------------
//
//        //구현 2: 이전 단계에서 구한 상위 sampleSize개의 샘플들에 대해서 모두 calculateUrs()를 이용해 urs를 구함 ---------------------------------------------------------------------
//        for(i = 0; i < sampleSize; i++){
//            selectedUsers[i][0] = gapsProjUsers[i][0];
//            selectedUsers[i][1] = gapsProjUsers[i][2] + random.nextFloat() * 1.5f;   //원래는 calculateUrs() 함수를 사용하여 urs를 구해야 하지만 DB 완성 전까지는 이와 같은 방식으로 대체
//            System.out.println(i + ", user_id : " + Math.round(selectedUsers[i][0]) + ", urs : " + selectedUsers[i][1]);
//        }
//        System.out.println("\n");
//        //--------------------------------------------------------------------------------------------------------------------------------------------
//
//        //구현 3: |selectedUsers[][1]-PROJECT_INFO.avg_score(id=proj_id)|의 값이 작은 순으로 recommendSize만큼 추출 -----------------------------------------
//        for(i = 0; i < sampleSize; i++){
//            selectedUsers[i][1] = Math.abs(selectedUsers[i][1]-avgScore);
//        }
//
//        Arrays.sort(selectedUsers, Comparator.comparingDouble(arr -> arr[1]));
//
//        for(i = 0; i < recommendSize; i++){
//            result[i] = Math.round(selectedUsers[i][0]);
//            System.out.println("정렬됨 > " + i + ", user_id : " + Math.round(selectedUsers[i][0]) + ", |urs - avgUrs| : " + selectedUsers[i][1]);
//        }
//        System.out.println("\n");
//        //---------------------------------------------------------------------------------------------------------------------------------------------
//
//        return result;
//    }
//
//    // 유저가 특정 기술 스택에 대하여 프로젝트를 추천 받는 메소드 (output: 추천 팀원들의 projId 배열)
//    static int[] getProjs(int sampleSize, int recommendSize, int userId, String targetStack){
//        Random random = new Random();   //테스트 데이터셋 생성을 위한 Random 객체 생성
//
//        float proficiency = 3 + random.nextFloat() * (10 - 3);    //<targetStack 숙련도 테이블>.proficienct(id=proj_id) 테스트 데이터 생성
//        System.out.println("userId proficiency : " + proficiency + "\n");
//        int testSize = 50;    //테스트를 위한 targetStack 테이블의 데이터 수
//        float recruitingProjs[] = new float[testSize]; //PROJECT_SPEC에서 targetStack에 대해서 모집 중인 프로젝트들의 ProjId가 저장되는 배열
//        float gapsProjUsers[][] = new float[testSize][2]; //유저의 데이터와 |PROJECT_INFO.avg_score(id=proj_id) - <target_stack>.proficiency|값이 저장되는 배열
//        float selectedProjs[][] = new float[sampleSize][3]; //최종 상위 추천 유저의 데이터가 recommendSize만큼 저장되는 배열
//        int result[] = new int[recommendSize];   //getProjs() 함수 최종 출력값 (projectId)
//
//        //구현 1: PROJECT_SPEC에서 using_stack이 targetStack이면서 is_recruited가 0인 프로젝트의 projectId 추출 (중복 제거 필요) ---------------------------
//        //100부터 999까지 겹치지 않는 testSize개의 수 생성
//        Set<Integer> uniqueNumbers = new HashSet<>();
//        while (uniqueNumbers.size() < testSize) {
//            int randomNumber = random.nextInt(900) + 100;
//            uniqueNumbers.add(randomNumber);
//        }
//
//        int i = 0;
//        //PROJECT_SPEC에서 targetStack에 대해서 모집 중인 프로젝트들에 대한 테스트 데이터셋 생성
//        for (int proj_id : uniqueNumbers) {
//            recruitingProjs[i] = proj_id;
//            System.out.println(targetStack + " 테이블 > " + i + ", projId : " + Math.round(recruitingProjs[i]));
//            i+=1;
//        }
//        System.out.println("\n");
//        //-------------------------------------------------------------------------------------------------------------------------------------
//
//        //구현 2: recruitingProjs[]의 프로젝트들 중 |PROJECT_INFO.avg_score - <target_stack>.proficiency(id=user_id)|의 값이 작은 순으로 sampleSize만큼 추출--------------------
//        //|PROJECT_INFO.avg_score - <target_stack>.proficiency(id=user_id)|를 구하여 sampleSize만큼 샘플링
//        for(i = 0; i < testSize; i++){
//            gapsProjUsers[i][0] = recruitingProjs[i];
//            gapsProjUsers[i][1] = Math.abs(AccessDB.getTeamScore(Math.round(recruitingProjs[i])) - proficiency);
//            System.out.println(i + ", projectId : " + Math.round(gapsProjUsers[i][0]) + ", |avgUrs - 숙련도| : " + gapsProjUsers[i][1]);
//        }
//        System.out.println("\n");
//
//        Arrays.sort(gapsProjUsers, Comparator.comparingDouble(arr -> arr[1]));
//
//        for(i = 0; i < sampleSize; i++){
//            System.out.println("정렬됨 > " + i + ", projectId : " + Math.round(gapsProjUsers[i][0]) + ", |avgUrs - 숙련도| : " + gapsProjUsers[i][1]);
//        }
//        System.out.println("\n");
//
//        //-------------------------------------------------------------------------------------------------------------------------------------
//
//        //구현 3: 이전 단계에서 구한 상위 sampleSize개의 샘플들에 대해서 모두 calculateUrs()를 이용해 urs를 구함
//        for(i = 0; i < sampleSize; i++){
//            selectedProjs[i][0] = gapsProjUsers[i][0];
//            selectedProjs[i][1] = AccessDB.getTeamScore(Math.round(gapsProjUsers[i][0]));
//            selectedProjs[i][2] = proficiency + random.nextFloat() * 1.5f;   //원래는 calculateUrs() 함수를 사용하여 urs를 구해야 하지만 DB 완성 전까지는 이와 같은 방식으로 대체
//            System.out.println(i + ", projectId : " + Math.round(selectedProjs[i][0]) + ", urs : " + selectedProjs[i][1]);
//        }
//        System.out.println("\n");
//
//        //구현 4: |selectedUsers[][2]-selectedUsers[][1]|의 값이 작은 순으로 recommendSize만큼 추출 -----------------------------------------
//        for(i = 0; i < sampleSize; i++){
//            selectedProjs[i][1] = Math.abs(selectedProjs[i][2]-selectedProjs[i][1]);
//        }
//
//        Arrays.sort(selectedProjs, Comparator.comparingDouble(arr -> arr[1]));
//
//        for(i = 0; i < recommendSize; i++){
//            result[i] = Math.round(selectedProjs[i][0]);
//            System.out.println("정렬됨 > " + i + ", projectId : " + Math.round(selectedProjs[i][0]) + ", |urs - avgUrs| : " + selectedProjs[i][1]);
//        }
//        System.out.println("\n");
//        //---------------------------------------------------------------------------------------------------------------------------------------------
//
//        return result;
//    }
}
