package com.travel.board.factory;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiResponse {
    String code;
    String message;
    Object data;
}
