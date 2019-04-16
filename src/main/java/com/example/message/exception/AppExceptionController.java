package com.example.message.exception;

import com.example.message.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class AppExceptionController {

    private Logger logger = LoggerFactory.getLogger(AppExceptionController.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BadRequestException.class)    
    public String badRequestException(BadRequestException ex) {
        logger.info(ex.getMessage());
        return String.format("%s: %s",
                HttpStatus.BAD_REQUEST.toString(), ex.getMessage());
    }
 @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = UsernameNotFoundException.class)    
    public String badRequestException(UsernameNotFoundException ex) {
        logger.info(ex.getMessage());
        return String.format("%s: %s",
                HttpStatus.BAD_REQUEST.toString(), ex.getMessage());
    }
}