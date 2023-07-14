package com.hello.post.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class SignupDto {

    @NotBlank
    @Size(min=4, max=8)
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    private String username;

    @NotBlank
    @Size(min=8, max=15)
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+$")
    private String password;

}
