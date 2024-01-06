package com.example.BookService;

import com.example.BookMapper.BookReviewMapper;
import com.example.pojo.BookReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookReviewService {

    private final BookReviewMapper bookReviewMapper;

    @Autowired
    public BookReviewService(BookReviewMapper bookReviewMapper) {
        this.bookReviewMapper = bookReviewMapper;
    }

    public int addBookReview(BookReview bookReview) {
        return bookReviewMapper.insertBookReview(bookReview);
    }

    public BookReview getBookReviewById(int id) {
        return bookReviewMapper.selectBookReviewById(id);
    }

    public List<BookReview> getAllBookReviews() {
        return bookReviewMapper.selectAllBookReviews();
    }

    public int updateBookReview(BookReview bookReview) {
        return bookReviewMapper.updateBookReview(bookReview);
    }

    public int deleteBookReview(int id) {
        return bookReviewMapper.deleteBookReviewById(id);
    }
}