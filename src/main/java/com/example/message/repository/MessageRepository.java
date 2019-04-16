package com.example.message.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.example.message.domain.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {
    
   Boolean existsByIdAndAuthor(Long Id, String author);
   List<Message> findByAuthor(String author);
   List<Message> findByTextContains(String text);
}