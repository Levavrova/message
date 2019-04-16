package com.example.message;

import com.example.message.exception.BadRequestException;
import com.example.message.domain.Message;
import com.example.message.dto.MessageWithUserDto;
import com.example.message.repository.MessageRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MessageService {

    private final MessageRepository messageRepository;

    MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message create(MessageWithUserDto messageDto) {
        return messageRepository.save(
                transferToEntity(messageDto));
    }

    public Message update(Long id, MessageWithUserDto messageDto) {
        if (validAuthor(id, messageDto.getUsername())) {
            Message message = transferToEntity(messageDto);
            message.setId(id);
            return messageRepository.save(message);
        } else {
            throw new BadRequestException(String.format(
                    "The author %s does not have permission to update message Id %d.",
                    messageDto.getUsername(), id));
        }
    }

    public void deleteByMessageId(Long id, String author) {
        if (validAuthor(id, author)) {
            messageRepository.deleteById(id);
        } else {
            throw new BadRequestException(String.format(
                    "The author %s does not have permission to delete message Id %d.",
                    author, id));
        }
    }

    public Iterable<Message> getAll() {
        return messageRepository.findAll();
    }

    public List<Message> getByAuthor(String author){
        return messageRepository.findByAuthor(author);
    }
     
    public List<Message> searchText(String text){
        return messageRepository.findByTextContains(text);
    }
    
    private Message transferToEntity(MessageWithUserDto messageDto) {
        Message message = new Message();
        message.setText(messageDto.getText());
        message.setAuthor(messageDto.getUsername());
        return message;
    }

    private boolean validAuthor(Long id, String author) {
        return messageRepository.existsByIdAndAuthor(id, author);
    }
}