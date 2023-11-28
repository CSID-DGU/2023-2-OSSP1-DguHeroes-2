import java.util.*;
public class recommend {
    public static void main(String[] args) {
        float a = urs.getTeamScore(123);
        System.out.println("getTeamScore() 테스트 : " + a);

        float b = urs.calculateUrs(123,456,"Java");
        System.out.println("calculateUrs() 테스트 : " + b);

        int result[] = getUsers(20, 10, 1, "Java");

        for(int i = 0; i < result.length; i++){
            System.out.println("최종 추천 리스트 > " + i + ", userId : " + Math.round(result[i]));
        }
    }

    static int[] getUsers(int sampleSize, int recommendSize, int projId, String targetStack){
        Random random = new Random();   //테스트 데이터셋 생성을 위한 Random 객체 생성

        //구현 1: <targetStack 숙련도 테이블>에서 |PROJECT_INFO.avg_score(id=proj_id) - <target_stack>.proficiency|의 값이 작은 순으로 sampleSize만큼 추출 -------------
        float avgScore = 3 + random.nextFloat() * (10 - 3);    //PROJECT_INFO.avg_score(id=proj_id) 테스트 데이터셋 생성
        int testSize = 100;    //테스트를 위한 targetStack 테이블의 데이터 수
        float proficiency[][] = new float[testSize][2]; //<targetStack> 숙련도 테이블 데이터가 저장되는 배열
        float gapsProjUsers[][] = new float[testSize][3]; //유저의 데이터와 |PROJECT_INFO.avg_score(id=proj_id) - <target_stack>.proficiency|값이 저장되는 배열
        float selectedUsers[][] = new float[sampleSize][2]; //최종 상위 추천 유저의 데이터가 recommendSize만큼 저장되는 배열
        int result[] = new int[recommendSize];   //getUsers() 함수 최종 출력값 (userId)

        //1000부터 9999까지 겹치지 않는 testSize개의 수 생성
        Set<Integer> uniqueNumbers = new HashSet<>();
        while (uniqueNumbers.size() < testSize) {
            int randomNumber = random.nextInt(9000) + 1000;
            uniqueNumbers.add(randomNumber);
        }

        int i = 0;
        //<targetStack>.proficiency 테스트 데이터셋 생성
        for (int user_id : uniqueNumbers) {
            proficiency[i][0] = user_id;
            proficiency[i][1] = 3 + random.nextFloat() * (10 - 3);
            System.out.println(i + ", user_id : " + Math.round(proficiency[i][0]) + ", 숙련도 : " + proficiency[i][1]);
            i+=1;
        }
        System.out.println("\n");

        //|avgUrs - <targetStack>.proficiency|를 구하여 sampleSize만큼 샘플링
        for(i = 0; i < testSize; i++){
            gapsProjUsers[i][0] = proficiency[i][0];
            gapsProjUsers[i][1] = Math.abs(avgScore-proficiency[i][1]);
            gapsProjUsers[i][2] = proficiency[i][1];
            System.out.println(i + ", user_id : " + Math.round(gapsProjUsers[i][0]) + ", |avgUrs - 숙련도| : " + gapsProjUsers[i][1]);
        }
        System.out.println("\n");

        Arrays.sort(gapsProjUsers, Comparator.comparingDouble(arr -> arr[1]));

        for(i = 0; i < sampleSize; i++){
            System.out.println("정렬됨 > " + i + ", user_id : " + Math.round(gapsProjUsers[i][0]) + ", |avgUrs - 숙련도| : " + gapsProjUsers[i][1]);
        }
        System.out.println("\n");
        //--------------------------------------------------------------------------------------------------------------------------------------------

        //구현 2: 이전 단계에서 구한 sample들에 대해서 모두 calculateUrs()를 이용해 urs를 구함 ---------------------------------------------------------------------
        for(i = 0; i < sampleSize; i++){
            selectedUsers[i][0] = gapsProjUsers[i][0];
            selectedUsers[i][1] = gapsProjUsers[i][2] + random.nextFloat() * 3f;   //원래는 calculateUrs() 함수를 사용하여 구해야 하지만 DB 완성 전까지는 이와 같은 방식으로
            System.out.println(i + ", user_id : " + Math.round(selectedUsers[i][0]) + ", urs : " + selectedUsers[i][1]);
        }
        System.out.println("\n");
        //--------------------------------------------------------------------------------------------------------------------------------------------

        //구현 3: |selectedUsers[][1]-PROJECT_INFO.avg_score(id=proj_id)|의 값이 작은 순으로 recommendSize만큼 추출 -----------------------------------------
        for(i = 0; i < sampleSize; i++){
            selectedUsers[i][1] = Math.abs(selectedUsers[i][1]-avgScore);
        }

        Arrays.sort(selectedUsers, Comparator.comparingDouble(arr -> arr[1]));

        for(i = 0; i < recommendSize; i++){
            result[i] = Math.round(selectedUsers[i][0]);
            System.out.println("정렬됨 > " + i + ", user_id : " + Math.round(selectedUsers[i][0]) + ", |urs - avgUrs| : " + selectedUsers[i][1]);
        }
        System.out.println("\n");
        //---------------------------------------------------------------------------------------------------------------------------------------------

        return result;
    }

    static int[] getProjs(int sampleSize, int recommendSize, int userId, String targetStack){
        int result[] = new int[recommendSize];   //getProjs() 함수 최종 출력값
        return result;
    }
}

