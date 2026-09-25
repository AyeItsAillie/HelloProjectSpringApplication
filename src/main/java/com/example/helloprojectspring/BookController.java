package com.example.helloprojectspring;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/books")
public class BookController {

    private final List<Book> books = new ArrayList<>();

    public BookController() {
        books.add(new Book(
                1,
                "The Hobbit",
                "J.R.R Tolkien",
                14.99
        ));
        books.add(new Book(
                2,
                "1984",
                "George Orwell",
                14.99
        ));
        books.add(new Book(
                3,
                "One Piece Manga 1",
                "Oda",
                9.99
        ));
    }

    @GetMapping
    public List<Book> getBooks() {
        return books;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getBook(@PathVariable int id){
        for (Book book : books) {
            if (book.getId() ==id){
                return ResponseEntity.ok(books);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        int newId = books.size() + 1;
        book.setId(newId);
        books.add(book);
        return ResponseEntity.status(201).body(book);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        for (Book book : books) {
            if (book.getId() == id){
                books.remove(book);
                return ResponseEntity.status(204).build();

            }
        }

         return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id,
                                           @RequestBody Book updatedBook) {
        for(Book book :books) {
            if (book.getId() == id) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setPrice(updatedBook.getPrice());

                return ResponseEntity.ok(book);
            }
        }
        return ResponseEntity.notFound().build();
    }

}
