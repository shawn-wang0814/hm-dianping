package com.hmdp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LoginFormDTO {
    private String phone;
    private String code;
    private String password;
}
