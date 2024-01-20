package com.shu.controller;

import com.shu.entity.vo.request.user.BookReviewwithavtar;
import com.shu.service.impl.BookReviewService;
import com.shu.entity.vo.request.BookReview;
import com.shu.entity.vo.request.CommentDeletionRequest;
import com.shu.entity.vo.request.Page;
import com.shu.entity.vo.request.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book/comment")
public class BookReviewController {

    private final BookReviewService bookReviewService;

    @Autowired
    public BookReviewController(BookReviewService bookReviewService) {
        this.bookReviewService = bookReviewService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> createBookReview(@RequestBody BookReview bookReview) {
        bookReviewService.addBookReview(bookReview);
        return ResponseEntity.ok(new Response(200, "操作成功", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookReviewById(@PathVariable int id) {
        BookReview bookReview = bookReviewService.getBookReviewById(id);
        return ResponseEntity.ok(new Response(200, "操作成功", bookReview));
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> updateBookReview(@PathVariable int id, @RequestBody BookReview bookReview) {
        bookReview.setId(id);
        bookReviewService.updateBookReview(bookReview);
        return ResponseEntity.ok(new Response(200, "操作成功", null));
    }

    @DeleteMapping("/admin")
    public ResponseEntity<?> deleteComments(@RequestBody CommentDeletionRequest request) {
        if (bookReviewService.deleteCommentsIfAdmin(request)) {
            return ResponseEntity.ok(new Response(200, "操作成功", null));
        } else {
            return ResponseEntity.ok(new Response(0, "操作失败", null));
        }}
    @DeleteMapping("/user")
        public ResponseEntity<?> deleteCommentsByUser(@RequestBody CommentDeletionRequest request) {
            if (bookReviewService.deleteCommentsIfUser(request)) {
                return ResponseEntity.ok(new Response(200, "操作成功", null));
            } else {
                return ResponseEntity.ok(new Response(0, "操作失败", null));
            }
        }

    @GetMapping
    public ResponseEntity<?> getCommentsByBookId(
            @RequestParam ("bookId")int bookId,
            @RequestParam("page") int page,
            @RequestParam ("pageSize")int pageSize) {

        Page<BookReviewwithavtar> comments = bookReviewService.getCommentsByBookId(bookId, page, pageSize);
        return ResponseEntity.ok(comments);
    }

}