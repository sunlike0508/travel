package com.travel.board.factory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponseFactory {

    public static ResponseEntity<ApiResponse> createSuccessResponse(Object data) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code("SUCCESS").message("성공").data(data).build();

        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    public static ResponseEntity<ApiResponse> createFailResponse() {
        ApiResponse apiResponse = ApiResponse.builder()
                .code("FAIL").message("실패").data(null).build();

        return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
