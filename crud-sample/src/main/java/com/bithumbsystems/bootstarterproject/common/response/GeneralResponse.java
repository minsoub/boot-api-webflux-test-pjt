package com.bithumbsystems.bootstarterproject.common.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GeneralResponse<T> extends Response {
    private T data;
    private int status;
    private String message;

}
