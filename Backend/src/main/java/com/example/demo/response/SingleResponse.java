package com.example.demo.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResponse<T> extends CommonResponse {
    T data;
}
