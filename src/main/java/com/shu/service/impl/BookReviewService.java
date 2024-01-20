package com.shu.service.impl;

import com.shu.entity.vo.request.Book;
import com.shu.entity.vo.request.user.BookReviewwithavtar;
import com.shu.mapper.BookReviewMapper;
import com.shu.entity.vo.request.BookReview;
import com.shu.entity.vo.request.CommentDeletionRequest;
import com.shu.entity.vo.request.Page;
import com.shu.service.AccountService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@Slf4j
public class BookReviewService {

    private final BookReviewMapper bookReviewMapper;
    @Resource
    AccountService accountService;
    @Autowired
    private BookService bookService;

    @Autowired
    public BookReviewService(BookReviewMapper bookReviewMapper) {
        this.bookReviewMapper = bookReviewMapper;
    }

    public int addBookReview(BookReview bookReview) {
        bookReview.setCreateTime(LocalDateTime.now());
        bookReview.setUpdateTime(LocalDateTime.now());
        Book book= bookService.getBookById(bookReview.getBookId());
        if(book != null){
            bookReview.setBookVersion(book.getBookVersion());
        }
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

    public Page<BookReviewwithavtar> getCommentsByBookId(int bookId, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        List<BookReviewwithavtar> comments = bookReviewMapper.findCommentsByBookId(bookId, offset, pageSize);
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
    public boolean deleteCommentsIfUser(CommentDeletionRequest request) {
        // 执行批量删除操作
        boolean FLAG = true;
        log.info("request:{}",request);
        for(int i=0;i<request.getIds().size();i++) {
            BookReview bookReview = bookReviewMapper.selectBookReviewById(request.getIds().get(i));
            if(bookReview != null) {
                if (bookReview.getUserEmail().equals(request.getUserEmail())) {
                    bookReviewMapper.deleteCommentsByIds(request.getIds());
                }
                else{
                    FLAG =false;
                }
            }
            else{
                FLAG =false;
            }
        }
        return FLAG;
    }

    //修改用户名
    public void updateUsername(String oldUsername,String newUsername){
        bookReviewMapper.updateUsername(oldUsername,newUsername);
    }
}