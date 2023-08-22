package com.bookStore.bookStore.controller;

import com.bookStore.bookStore.exception.BookAlreadyExistsException;
import com.bookStore.bookStore.exception.ConstraintViolationExceptionResponse;
import com.bookStore.bookStore.model.Book;
import com.bookStore.bookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/add_book")
    public ResponseEntity addBook(@RequestBody Book book) throws BookAlreadyExistsException {
        if (book.getTitle() == null || book.getTitle().isEmpty()){
            ConstraintViolationExceptionResponse response = new ConstraintViolationExceptionResponse("Validation Error");
            response.getViolations().add("Empty Book Title");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else if (book.getIsbn() == null || book.getIsbn().isEmpty()){
            ConstraintViolationExceptionResponse response = new ConstraintViolationExceptionResponse("Validation Error");
            response.getViolations().add("Empty Book ISBN");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        bookService.add(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }
    @DeleteMapping("/remove_book/{isbn}")
    public ResponseEntity<String> removeBook(@PathVariable String isbn){
        bookService.deleteById(isbn);
        return new ResponseEntity<>("Book has been removed successfully.",HttpStatus.OK);
    }
    @PatchMapping("/update_quantity/{isbn}/{quantity}")
    public ResponseEntity<Book> updateBookQuantity(@PathVariable("isbn") String isbn, @PathVariable("quantity") int newQuantity){
        Book book = bookService.getBookById(isbn);
        book.setQuantity(newQuantity);
        bookService.add(book);
        return new ResponseEntity<>(book,HttpStatus.OK);
    }
    @GetMapping("/check_quantity/{isbn}")
    public ResponseEntity<Integer> getBookQuantity(@PathVariable("isbn") String isbn){
        Book book = bookService.getBookById(isbn);
        return new ResponseEntity<>(book.getQuantity(),HttpStatus.OK);
    }

    @GetMapping("/all_books")
    public ResponseEntity<List<Book>> getAllBook(){
        List<Book> bookList = bookService.getAllBook();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }
}
