package com.java.tutorial.project;

import com.java.tutorial.project.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author meta
 */
@SpringBootApplication
public class SpringBootProjectApplication implements CommandLineRunner {

    private final BookRepository bookRepository;

    @Autowired
    public SpringBootProjectApplication(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        bookRepository.createIndexAndMapping();
    }

}
