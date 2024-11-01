package com.java.tutorial.project.service;

import com.java.tutorial.project.domain.Book;
import com.java.tutorial.project.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author meta
 */
@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) throws IOException {
        return bookRepository.createBook(book);
    }

    public Book getBook(String id) throws IOException {
        return bookRepository.getBook(id);
    }

    public List<Book> searchBooks(String query) throws IOException {
        return bookRepository.searchBooks(query);
    }

    public Book updateBook(String id, Book book) throws IOException {
        return bookRepository.updateBook(id, book);
    }

    public void deleteBook(String id) throws IOException {
        bookRepository.deleteBook(id);
    }
}

