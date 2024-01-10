package com.example.controller;

import com.example.service.impl.BookService;
import com.example.entity.vo.request.Book;
import com.example.entity.vo.request.BookDeletionRequest;
import com.example.entity.vo.request.Response;
import com.example.utils.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private final BookService bookService;
    @Autowired
    private AliOSSUtils aliOSSUtils;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        bookService.createBook(book);
        return  ResponseEntity.ok(new Response(200, "操作成功"));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable int bookId) {
        Book book = bookService.getBookById(bookId);
        return ResponseEntity.ok(new Response(0, "操作成功", book));
    }

    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(new Response(0, "操作成功", books));
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateBook(@RequestBody Book book) {

        bookService.updateBook(book);
        return ResponseEntity.ok(new Response(0, "操作成功", null));
    }
    @PutMapping("/admin")
    public ResponseEntity<?> updateBookAdmin(@RequestBody Book book) {

        bookService.updateBook(book);
        return ResponseEntity.ok(new Response(0, "操作成功", null));
    }

    @DeleteMapping("/admin")
    public ResponseEntity<?> deleteBooks(@RequestBody BookDeletionRequest request) throws Exception {
        if (bookService.deleteBooksIfAdmin(request)) {
            List<Book> books=bookService.selectBooksByIds(request.getBookIds());
            if(books!=null){
                for (Book book : books) {
                    aliOSSUtils.DeleteFile(book.getBookFileUuid());
                }
            }
            return ResponseEntity.ok(new Response(0, "操作成功", null));
        } else {
            return ResponseEntity.ok(new Response(0, "操作失败，该账户不是管理员！", null));
        }
    }
    @GetMapping("/test")
    public ResponseEntity<?> selectBooksById(@RequestBody BookDeletionRequest request) throws Exception {   List<Book> books=bookService.selectBooksByIds(request.getBookIds());
        if(books!=null){
            for(int i=0;i<books.size();i++){
            aliOSSUtils.DeleteFile(books.get(i).getBookFileUuid());}
        }
        return ResponseEntity.ok(new Response(0, "操作成功",books ));

    }
    @DeleteMapping("/user")
    public ResponseEntity<?> deleteBooksUser(@RequestBody BookDeletionRequest request) throws Exception {

        if (bookService.deleteBooksIfUser(request)) {
            List<Book> books=bookService.selectBooksByIds(request.getBookIds());
            if(books!=null){
                for (Book book : books) {
                    aliOSSUtils.DeleteFile(book.getBookFileUuid());
                }
            }

            return ResponseEntity.ok(new Response(0, "操作成功", null));
        } else {
            return ResponseEntity.ok(new Response(0, "操作失败，不能删除别人的图书！", null));
        }
    }
}