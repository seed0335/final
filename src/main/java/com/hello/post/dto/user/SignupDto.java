package com.hello.post.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class SignupDto {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
