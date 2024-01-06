package com.example.BookController;

import com.example.BookService.BookService;
import com.example.pojo.Book;
import com.example.pojo.DeleteBooksRequest;
import com.example.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        int bookId = bookService.addBook(book);
        return ResponseEntity.ok(new Response(0, "操作成功", bookId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable int id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(new Response(0, "操作成功", book));
    }

    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(new Response(0, "操作成功", books));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable int bookId, @RequestBody Book book) {
        book.setBookId(bookId);
        bookService.updateBook(book);
        return ResponseEntity.ok(new Response(0, "操作成功", null));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteBooks(@RequestBody DeleteBooksRequest request) {
        // 可以在这里添加身份验证逻辑

        bookService.deleteBooks(request.getIds());
        return ResponseEntity.ok(new Response(0, "操作成功", null));
    }
}