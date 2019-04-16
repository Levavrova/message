package com.example.message;

import com.example.message.domain.Message;
import com.example.message.dto.MessageWithUserDto;
import com.example.message.dto.UserDto;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;
    
    MessageController(MessageService messageService){
        this.messageService = messageService;
    }
    
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    Message post(@Valid @RequestBody MessageWithUserDto messageDto) {        
        return messageService.create(messageDto);
    }
    
    @PutMapping("/{id}")
    Message put(@PathVariable Long id, @Valid @RequestBody MessageWithUserDto messageDto) {
        return messageService.update(id, messageDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@Valid @RequestBody UserDto userDto, @PathVariable Long id) {
        messageService.deleteByMessageId(id, userDto.getUsername());
    }

    @GetMapping("")
    Iterable<Message> getAll() {
        return messageService.getAll();
    }

    @GetMapping("/{author}")
    List<Message> getByAuthor(@PathVariable String author) {
        return messageService.getByAuthor(author);
    }
    
    @GetMapping("search/{text}")
    List<Message> searchText(@PathVariable String text) {
        return messageService.searchText(text);
    }
}