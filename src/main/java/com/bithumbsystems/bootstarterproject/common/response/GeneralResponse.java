package com.bithumbsystems.bootstarterproject.common.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class GeneralResponse<T> extends Response {
    private T data;
    private int status;
    private String message;

}
