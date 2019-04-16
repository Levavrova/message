package com.example.message.dto;

import com.example.message.ValidPassword;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ValidPassword
public class UserDto {

    @NotNull
    @NotEmpty
    @JsonProperty("author")
    String username;
    @NotNull
    @NotEmpty
    String password;
}
