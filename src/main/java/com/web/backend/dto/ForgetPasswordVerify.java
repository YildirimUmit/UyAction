package com.web.backend.dto;

import lombok.*;

import javax.validation.constraints.*;

@Data
public class ForgetPasswordVerify {

    @NotBlank
    private String password;

    @NotBlank
    private String maile;

    @NotBlank
    private String uuid;

}
