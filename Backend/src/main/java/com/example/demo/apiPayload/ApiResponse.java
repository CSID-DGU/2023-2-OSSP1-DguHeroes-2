package com.example.demo.apiPayload;

// isSuccess : 성공인지 아닌지 알려주는 필드
// code : HTTP 상태코드로는 너무 제한적인 정보만 줄 수 있어서 조금 더 세부적인 응답 상황을 알려주기 위한 필드
// message : code에 추가적으로 우리에게 익숙한 문자로 상황을 알려주는 필드
// result : 실제로 클라이언트에게 필요한 데이터가 담긴다. 보통 에러 상황에는 null을 담지만, ..to be continued

import com.example.demo.apiPayload.code.BaseCode;
import com.example.demo.apiPayload.code.status.SuccessStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    private final String code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;


    // 성공한 경우 응답 생성
    public static <T> ApiResponse<T> onSuccess(T result){
        return new ApiResponse<>(true, SuccessStatus._OK.getCode() , SuccessStatus._OK.getMessage(), result);
    }

    public static <T> ApiResponse<T> of(BaseCode code, T result){
        return new ApiResponse<>(true, code.getReasonHttpStatus().getCode() , code.getReasonHttpStatus().getMessage(), result);
    }


    // 실패한 경우 응답 생성
    public static <T> ApiResponse<T> onFailure(String code, String message, T data){
        return new ApiResponse<>(true, code, message, data);
    }
}
