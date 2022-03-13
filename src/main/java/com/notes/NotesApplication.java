package com.notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class NotesApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(NotesApplication.class, args);
    }
}
