package com.example.BookService;

import com.example.BookMapper.BookMapper;
import com.example.BookMapper.BookReviewMapper;
import com.example.pojo.BookReview;
import com.example.pojo.CommentDeletionRequest;
import com.example.pojo.Page;
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

    public Page<BookReview> getCommentsByBookId(int bookId, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        List<BookReview> comments = bookReviewMapper.findCommentsByBookId(bookId, offset, pageSize);
        int total = bookReviewMapper.countCommentsByBookId(bookId);

        return new Page<>(comments, total, page, pageSize);
    }
    public boolean deleteCommentsIfAdmin(CommentDeletionRequest request) {
        // 检查是否为管理员
        if (!bookReviewMapper.isAdmin(request.getAdminAccount())) {
            return false;
        }

        // 执行批量删除操作
        bookReviewMapper.deleteCommentsByIds(request.getIds());
        return true;
    }
}