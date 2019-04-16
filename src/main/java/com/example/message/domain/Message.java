package com.example.message.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name="message")
@Data
public class Message implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Id
    private Long id;
    @Column(name = "message")
    private String text;
    @Column(name = "username")
    private String author;
}
