package com.example.demo.response;

import com.example.demo.repository.ResponseRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ResponseService {
    ResponseRepository response_rp;

    public ResponseService(ResponseRepository responseRepository){
        response_rp = responseRepository;
    }

    public<T> SingleResponse<T> getSingleResponse(CommonResponse result, T data){
        return response_rp.getSingleResponse(result, data);
    }

    public<T> ListResponse<T> getListResponse(CommonResponse result, List<T> list_data){
        return response_rp.getListResponse(result, list_data);
    }

    public AdminResponse getAdminResponse(CommonResponse result, Long isAdmin){
        return response_rp.getAdminResponse(result, isAdmin);
    }



}
