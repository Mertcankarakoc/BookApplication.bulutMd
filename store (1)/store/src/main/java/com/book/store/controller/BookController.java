package com.book.store.controller;

import com.book.store.model.Book;
import com.book.store.service.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Operation()
    @GetMapping
    public List<Book> getAllBooks(Long id) {
        return bookService.findAllBookId(id);
    }

    @GetMapping("{id}")
    public Optional<Book> getBookById(@PathVariable("id") Long id){

        return bookService.findBookById(id);
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String author,
                                  @RequestParam(required = false) Optional<Integer> publicationYear){
        if (publicationYear.isPresent()){
            int year = publicationYear.get();

        }
        return bookService.searchBooks(title, author, publicationYear);
    }

    @PostMapping()
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        bookService.createBook(book);
        return new ResponseEntity<Book>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        bookService.updateBook(id, book);
        return new ResponseEntity<Book>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void>deleteBookById(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);


    }
}
