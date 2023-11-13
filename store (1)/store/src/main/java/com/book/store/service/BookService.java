package com.book.store.service;

import com.book.store.model.Book;
import com.book.store.repository.BookRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAllBookId(Long bookId){
        return bookRepository.findAll();
    }

    public Optional<Book> findBookById(Long id){
        return bookRepository.findById(id);
    }

    public Book createBook(Book book){
        return bookRepository.save(book);
    }

    public List<Book> searchBooks(String title, String author, Optional<Integer> publicationYear){
        if (title!=null) {
            return bookRepository.findByTitleContaining(title);
        }else if(author!=null){
            return bookRepository.findByAuthorContaining(author);
        }else if(publicationYear.isPresent()) {
            return bookRepository.findByPublicationYear(publicationYear);
        }else {
            return null;
        }
    }

    public void deleteBookById(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public void updateBook(Long id, Book book) {
        if (bookRepository.existsById(id)) {
            book.setId(id);
            bookRepository.save(book);
        }
    }
}
