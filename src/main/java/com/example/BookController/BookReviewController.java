package com.example.BookController;

import com.example.BookService.BookReviewService;
import com.example.pojo.BookReview;
import com.example.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookReviews")
public class BookReviewController {

    private final BookReviewService bookReviewService;

    @Autowired
    public BookReviewController(BookReviewService bookReviewService) {
        this.bookReviewService = bookReviewService;
    }

    @PostMapping
    public ResponseEntity<?> createBookReview(@RequestBody BookReview bookReview) {
        bookReviewService.addBookReview(bookReview);
        return ResponseEntity.ok(new Response(0, "操作成功", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookReviewById(@PathVariable int id) {
        BookReview bookReview = bookReviewService.getBookReviewById(id);
        return ResponseEntity.ok(new Response(0, "操作成功", bookReview));
    }

    @GetMapping
    public ResponseEntity<?> getAllBookReviews() {
        List<BookReview> bookReviews = bookReviewService.getAllBookReviews();
        return ResponseEntity.ok(new Response(0, "操作成功", bookReviews));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBookReview(@PathVariable int id, @RequestBody BookReview bookReview) {
        bookReview.setId(id);
        bookReviewService.updateBookReview(bookReview);
        return ResponseEntity.ok(new Response(0, "操作成功", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookReview(@PathVariable int id) {
        bookReviewService.deleteBookReview(id);
        return ResponseEntity.ok(new Response(0, "操作成功", null));
    }
}