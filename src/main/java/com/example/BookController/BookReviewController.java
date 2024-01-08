package com.example.BookController;

import com.example.BookService.BookReviewService;
import com.example.pojo.BookReview;
import com.example.pojo.CommentDeletionRequest;
import com.example.pojo.Page;
import com.example.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok(new Response(0, "操作成功", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookReviewById(@PathVariable int id) {
        BookReview bookReview = bookReviewService.getBookReviewById(id);
        return ResponseEntity.ok(new Response(0, "操作成功", bookReview));
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> updateBookReview(@PathVariable int id, @RequestBody BookReview bookReview) {
        bookReview.setId(id);
        bookReviewService.updateBookReview(bookReview);
        return ResponseEntity.ok(new Response(0, "操作成功", null));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteComments(@RequestBody CommentDeletionRequest request) {
        if (bookReviewService.deleteCommentsIfAdmin(request)) {
            return ResponseEntity.ok(new Response(0, "操作成功", null));
        } else {
            return ResponseEntity.ok(new Response(0, "操作失败", null));
        }
}
    @GetMapping
    public ResponseEntity<?> getCommentsByBookId(
            @RequestParam ("bookId")int bookId,
            @RequestParam("page") int page,
            @RequestParam ("pageSize")int pageSize) {

        Page<BookReview> comments = bookReviewService.getCommentsByBookId(bookId, page, pageSize);
        return ResponseEntity.ok(comments);
    }

}