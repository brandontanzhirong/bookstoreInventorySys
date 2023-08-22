package com.bookStore.bookStore.service;

import com.bookStore.bookStore.exception.BookAlreadyExistsException;
import com.bookStore.bookStore.exception.BookNotFoundException;
import com.bookStore.bookStore.model.Book;
import com.bookStore.bookStore.respository.BookRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {
    @Autowired
    private BookRespository bookRepo;

    public Book add(Book book) throws BookAlreadyExistsException {
        if (bookRepo.existsById(book.getIsbn())){
            throw new BookAlreadyExistsException();
        }
        bookRepo.save(book);
        return book;
    }

    public Book getBookById(String isbn) throws BookNotFoundException {
        return bookRepo.findById(isbn).orElseThrow(BookNotFoundException::new);
    }

    public List<Book> getAllBook(){
        return bookRepo.findAll();
    }

    public void deleteById(String isbn){
        bookRepo.deleteById(isbn);
    }

}
