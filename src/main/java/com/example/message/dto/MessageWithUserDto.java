package com.example.message.dto;

import lombok.Data;

@Data
public class MessageWithUserDto extends UserDto   {
   
private String text;
}
