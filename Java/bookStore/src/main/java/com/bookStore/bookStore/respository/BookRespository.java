package com.bookStore.bookStore.respository;

import com.bookStore.bookStore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRespository extends JpaRepository<Book, String> {
}
