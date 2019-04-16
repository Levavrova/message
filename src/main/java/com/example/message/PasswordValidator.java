package com.example.message;

import com.example.message.dto.UserDto;
import com.example.message.repository.UserRepository;
import com.example.message.domain.User;
import java.util.Optional;
import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordValidator implements ConstraintValidator<ValidPassword, Object> {
    
    
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    @Inject
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Inject
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){  
    UserDto userDto = (UserDto) obj;
    User user = userRepository.findByUsername(userDto.getUsername());
    Optional.ofNullable(user)
            .orElseThrow(() -> new UsernameNotFoundException(  
                    String.format("Username %s not found.", userDto.getUsername())));
    return passwordEncoder.matches(userDto.getPassword(),user.getPassword());
    }
}
