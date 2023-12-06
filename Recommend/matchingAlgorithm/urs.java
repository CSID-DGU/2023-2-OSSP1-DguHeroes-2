import java.util.*;

public class urs {
    // 프로젝트의 평균 userRoleScore를 계산해주는 메소드
    static float getTeamScore(int projId){
        float avgScore = 0;

        //구현: proj_id를 이용하여 PROJECT_MEMBER 테이블에서 프로젝트 멤버들의 user_role_score 추출
        Random random = new Random();
        int randomCount = random.nextInt(7) + 1;
        float[] userRoleScore = new float[randomCount];
        for (int i = 0; i < userRoleScore.length; i++) {
            userRoleScore[i] = 3 + random.nextFloat() * (10 - 3);
        }
        //------------------------------------------------------------------------------

        float sum = 0;
        for (float score : userRoleScore) {
            sum += score;
        }
        avgScore = sum / userRoleScore.length;

        return avgScore;
    }

    // 특정 프로젝트에서 특정 유저의 userRoleScore를 계산해주는 메소드
    static float calculateUrs(int projId, int userId, String targetStack){
        float urs = 0;

        //구현: 프로젝트(projId)가 요구하는 기술스택 리스트를 PROJECT_SPEC 테이블에서 추출 (중복 제거) -----
        List<String> projStack = new ArrayList<>();
        projStack.add("Java");
        projStack.add("SpringBoot");
        projStack.add("React");
        //----------------------------------------------------------------------------------

        //구현: 유저(userId)가 사용할 줄 아는 기술스택 리스트를 USER_STACK 테이블에서 추출 -----------
        List<String> userStack = new ArrayList<>();;   //test dataset
        userStack.add("Java");
        userStack.add("SpringBoot");
        //-------------------------------------------------------------------------------

        List<String> intersection = findIntersection(projStack, userStack);

        //System.out.println(intersection + " : intersection success");

        //구현: intersection을 이용하여 참조해야 할 숙련도 테이블들 선택 후,
        //선택된 숙련도 테이블들과 함수의 input으로 받은 stack, 그리고 STACK_DISTANCE 테이블을 가지고 userRoleScore 계산 --------
        Random random = new Random();
        urs = 3 + random.nextFloat() * (10 - 3);
        //--------------------------------------------------------------------------------------------------------

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
