package com.example.BookService;

import com.example.BookMapper.BookMapper;
import com.example.pojo.Book;
import com.example.pojo.BookDeletionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private final BookMapper bookMapper;


    public boolean deleteBooksIfAdmin(BookDeletionRequest request) {
        // 检查是否为管理员
        if (!bookMapper.isAdmin(request.getAdminAccount())){
            return false;
        }

        // 删除图书
        bookMapper.deleteBooksByIds(request.getBookIds());

        return true;
    }
    public void deleteBooks(List<Integer> ids) {
        bookMapper.deleteBooksByIds(ids);
    }

    public BookService(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }
 public List<Book> selectBooksByIds(List<Integer> BookIds){
        return bookMapper.selectBooksByIds(BookIds);
 }
    public void createBook(Book book) {
        bookMapper.insertBook(book);
    }

    public Book getBookById(int id) {
        return bookMapper.selectBookById(id);
    }

    public List<Book> getAllBooks() {
        return bookMapper.selectAllBooks();
    }

    public int updateBook(Book book) {
        return bookMapper.updateBook(book);
    }
    public boolean deleteBooksIfUser(BookDeletionRequest request) {
        // 检查是否为管理员
        if (!bookMapper.isUser(request.getUserEmail())) {
            return false;
        }

        // 删除图书
        bookMapper.deleteBooksByIds(request.getBookIds());
        return true;
    }


}