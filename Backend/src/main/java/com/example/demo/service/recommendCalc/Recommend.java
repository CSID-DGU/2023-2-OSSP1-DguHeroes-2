package com.example.demo.service.recommendCalc;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static java.lang.Math.abs;

@Transactional
@Service
public class Recommend {

    public static List<Integer> userBaseSearch(int userId, String stack){
        ArrayList<Integer> projIds=AccessDB.getAllProjectId();
        List<float[]> projs=new ArrayList<>();


        for(Integer projId : projIds){
            List<String[]>  projectRequiredStack =AccessDB.getProjRequiredStack(projId);
            for(String[] projStack : projectRequiredStack){
                //System.out.println(projStack[0]);
                if(projStack[0].equals(stack)){
                    //System.out.println(projStack[0]);
                    //System.out.println(projId);
                    //System.out.println(AccessDB.checkIsRecruiting(projId, stack));
                    if(AccessDB.checkIsRecruiting(projId, stack)) {
                        float[] container=new float[2];
                        container[0]=Float.valueOf(projId);
                        float score=AccessDB.getTeamScore(projId);
                        float urs=Urs.calculateUrs(projectRequiredStack,userId,stack);
                        container[1]= Math.abs(score-urs);
                        projs.add(container);
                    }
                }
            }
        }
        //projs 오름차순 정렬 후
        projs.sort(Comparator.comparingDouble(element -> element[1]));

        for (float[] row : projs) {
            System.out.println(Arrays.toString(row));
        }


        List<Integer> userList = new ArrayList<>();

        //개수에 따라 반환 10개 이상이면 10개로, 그 이하면 개수대로 int로 바꿔서 반환
        int limit = Math.min(10, projs.size());
        List<float[]> result = projs.subList(0, limit);

        // element[0] 값을 int로 변환하여 List<Integer>에 저장
        List<Integer> intValues = new ArrayList<>();
        for (float[] element : result) {
            intValues.add((int) element[0]);
        }

        return intValues;
    }

    public static List<Integer> projectBaseSearch(int teamId, String stack){
        float teamScore=AccessDB.getTeamScore(teamId);
        List<String[]>  projectRequiredStack =AccessDB.getProjRequiredStack(teamId);

        String dbStack=AccessDB.findStackForDB(stack);

        ArrayList<Integer> intArray = AccessDB.getStackUserId(dbStack);

        // int[]를 float[][]로 변환
        float[][] scoreStorage = new float[intArray.size()][2];
        for (int i = 0; i < intArray.size(); i++) {
            scoreStorage[i][0] = (float) intArray.get(i); // 명시적인 형 변환
        }

        int i=0;
        for(Integer value : intArray){
            int userId=value;
            float userUrs=Urs.calculateUrs(projectRequiredStack,userId,stack);
            scoreStorage[i][1]= abs(userUrs-teamScore);
            i++;
        }

        // scoreStorage 배열을 scoreStorage[j][1]을 기준으로 오름차순 정렬
        Arrays.sort(scoreStorage, Comparator.comparingDouble(arr -> arr[1]));

        for (float[] row : scoreStorage) {
            System.out.println(Arrays.toString(row));
        }

        List<Integer> userList = new ArrayList<>();
        if(intArray.size()<10){
            for (int j = 0; j < intArray.size(); j++) {
                userList.add((int) scoreStorage[j][0]);
            }
        }else{
            for (int j = 0; j < 10; j++) {
                userList.add((int) scoreStorage[j][0]);
            }
        }

        return userList;
    }
/*
    public static void main(String[] args) {
        float a = AccessDB.getTeamScore(3);
        System.out.println("getTeamScore() test : " + a);

        List<String[]> requiredStack = AccessDB.getProjRequiredStack(3);

        for (String[] dataArray : requiredStack) {
            for (String data : dataArray) {
                System.out.print(data + " ");
            }
            System.out.println(); // 각 배열마다 줄바꿈
        }

        String dbStack=AccessDB.findStackForDB("React");
        System.out.println(dbStack);
        ArrayList<Integer> intArray = AccessDB.getStackUserId(dbStack);
        for (Integer value : intArray) {
            System.out.print(value + " ");
        }
        System.out.println();

        float c=Urs.calculateUrs(requiredStack,38,"React");
        System.out.println(c);

        List<Integer> result= projectBaseSearch(3,"React");
        for (Integer value : result) {
            System.out.print(value + " ");
        }
        System.out.println();

        List<Integer> intValue= userBaseSearch(38,"Java");
        for (Integer value : intValue) {
            System.out.print(value + " ");
        }
        System.out.println();

    }

 */
}