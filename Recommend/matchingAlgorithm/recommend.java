import java.util.*;

public class recommend {
    public static void main(String[] args) {
        float a = urs.get_teamscore(123);
        System.out.println("get_teamscore() 테스트 : " + a);

        float b = urs.calculate_urs(123,456,"Java");
        System.out.println("calculate_urs() 테스트 : " + b);

        int result[] = get_users(20, 10, 1, "Java");

        for(int i = 0; i < result.length; i++){
            System.out.println("최종 추천 리스트 > " + i + ", user_id : " + Math.round(result[i]));
        }
    }

    static int[] get_users(int sample_size, int recommend_size, int proj_id, String target_stack){
        //구현 1: target_stack 숙련도 테이블에서 |PROJECT_INFO.avg_score(id=proj_id) - target_stack.proficiency|의 값이 작은 순으로 sample_size만큼 추출 -------------
        int test_size = 100;    //테스트를 위한 target_stack 테이블의 데이터 수
        Random random = new Random();

        float avg_score = 3 + random.nextFloat() * (10 - 3);    //PROJECT_INFO.avg_score(id=proj_id) 테스트 데이터셋 생성
        float proficiency[][] = new float[test_size][2];

        //1000부터 9999까지 겹치지 않는 test_size개의 수 생성
        Set<Integer> uniqueNumbers = new HashSet<>();
        while (uniqueNumbers.size() < test_size) {
            int randomNumber = random.nextInt(9000) + 1000;
            uniqueNumbers.add(randomNumber);
        }

        int i = 0;
        //target_stack.proficiency 테스트 데이터셋 생성
        for (int user_id : uniqueNumbers) {
            proficiency[i][0] = user_id;
            proficiency[i][1] = 3 + random.nextFloat() * (10 - 3);
            System.out.println(i + ", user_id : " + Math.round(proficiency[i][0]) + ", 숙련도 : " + proficiency[i][1]);
            i+=1;
        }
        System.out.println("\n");

        //|avg_urs - proficiency|를 구하여 sample_size만큼 샘플링
        float gap_proj_users[][] = new float[test_size][3]; //|PROJECT_INFO.avg_score(id=proj_id) - target_stack.proficiency|값이 저장되는 배열
        for(i = 0; i < test_size; i++){
            gap_proj_users[i][0] = proficiency[i][0];
            gap_proj_users[i][1] = Math.abs(avg_score-proficiency[i][1]);
            gap_proj_users[i][2] = proficiency[i][1];
            System.out.println(i + ", user_id : " + Math.round(gap_proj_users[i][0]) + ", |avg_urs - 숙련도| : " + gap_proj_users[i][1]);
        }
        System.out.println("\n");

        Arrays.sort(gap_proj_users, Comparator.comparingDouble(arr -> arr[1]));

        for(i = 0; i < sample_size; i++){
            System.out.println("정렬됨 > " + i + ", user_id : " + Math.round(gap_proj_users[i][0]) + ", |avg_urs - 숙련도| : " + gap_proj_users[i][1]);
        }
        System.out.println("\n");
        //--------------------------------------------------------------------------------------------------------------------------------------------

        //구현 2: 이전 단계에서 구한 sample들에 대해서 모두 calculate_urs()를 이용해 urs를 구함 ---------------------------------------------------------------------
        float selected_user[][] = new float[sample_size][2]; //최종 상위 추천 유저가 recommend_size만큼 저장되는 배열
        int result[] = new int[recommend_size];   //get_users() 함수 최종 출력값
        for(i = 0; i < sample_size; i++){
            selected_user[i][0] = gap_proj_users[i][0];
            selected_user[i][1] = gap_proj_users[i][2] + random.nextFloat() * 3f;   //원래는 calculate_urs() 함수를 사용하여 구해야 하지만 DB 완성 전까지는 이와 같은 방식으로
            System.out.println(i + ", user_id : " + Math.round(gap_proj_users[i][0]) + ", urs : " + selected_user[i][1]);
        }
        System.out.println("\n");
        //--------------------------------------------------------------------------------------------------------------------------------------------

        //구현 3: |selected_user[][1]-PROJECT_INFO.avg_score(id=proj_id)|의 값이 작은 순으로 recommend_size만큼 추출 -----------------------------------------
        for(i = 0; i < sample_size; i++){
            selected_user[i][1] = Math.abs(selected_user[i][1]-avg_score);
        }

        Arrays.sort(selected_user, Comparator.comparingDouble(arr -> arr[1]));

        for(i = 0; i < recommend_size; i++){
            result[i] = Math.round(selected_user[i][0]);
            System.out.println("정렬됨 > " + i + ", user_id : " + Math.round(selected_user[i][0]) + ", |urs - avg_urs| : " + selected_user[i][1]);
        }
        System.out.println("\n");
        //---------------------------------------------------------------------------------------------------------------------------------------------

        return result;
    }

    static String[] get_projs(int sample_size, int recommend_size, int user_id, String target_stack){
        return null;
    }
}

