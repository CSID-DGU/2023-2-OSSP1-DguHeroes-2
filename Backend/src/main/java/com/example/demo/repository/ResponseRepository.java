package com.example.demo.repository;

import com.example.demo.response.CommonResponse;
import com.example.demo.response.ListResponse;
import com.example.demo.response.AdminResponse;
import com.example.demo.response.SingleResponse;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ResponseRepository {
    public<T> SingleResponse<T> getSingleResponse(CommonResponse result, T data);
    public<T> ListResponse<T> getListResponse(CommonResponse result, List<T> list_data);
    public AdminResponse getAdminResponse(CommonResponse result, Long isAdmin);
}
