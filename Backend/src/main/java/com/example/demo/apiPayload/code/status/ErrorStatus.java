package com.example.demo.apiPayload.code.status;

import com.example.demo.apiPayload.code.BaseErrorCode;
import com.example.demo.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 첫번째 인자만 보고 프론트엔드 개발자는 정확히 알 수 없다.
    // 따라서 두 번째 인자로 자체적인 String으로 code를 제공함으로써 더 정확히 API 응답의 원인을 알 수 있도록 한다.
    // 1. common 에러는 COMMON000 으로 둔다. <- 잘 안쓰지만 마땅하지 않을 때 사용
    // 2. 관련된 경우마다 code에 명시적으로 표현한다.
    //  - 예를 들어 멤버 관련이면 MEMBER001 이런 식으로
    // 3. 2번에 이어서 4000번대를 붙인다. 서버측 잘못은 그냥 COMMON 에러의 서버 에러를 쓰면 됨.
    //  - MEMBER400_1 아니면 MEMBER4001 이런 식으로

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),


    // 멤버 관려 에러
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),

    // 예시,,,
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글이 없습니다."),

    // For test
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트"),

    // FoodCategory Error
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD_CATEGORY4001", "음식 카테고리가 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
