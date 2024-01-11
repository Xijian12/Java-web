package com.example.service.impl;

import com.example.entity.vo.request.Page;
import com.example.entity.vo.request.user.DownloadBook;
import com.example.mapper.BookMapper;
import com.example.entity.vo.request.Book;
import com.example.entity.vo.request.BookDeletionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private final BookMapper bookMapper;

    public List<Book >GetBookObject(String userEmail){
        return bookMapper.getBookObject(userEmail);
    }


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
    public List<Book> getBookByEmail(String userEmail){

        return bookMapper.getBookObject(userEmail);
    }
public DownloadBook downloadBook(String userEmail,int bookId){
    DownloadBook download=new DownloadBook(bookMapper.downloadBook(userEmail,bookId),bookMapper.updateUserPoints(userEmail,bookId));

        return download;
}
    public List<Book> getAllBooks() {
        return bookMapper.selectAllBooks();
    }
    public List<Book> findBooks(String bookName, String bookAuthor, Integer bookPointsFloor, Integer bookPointsUpper,Double bookGradeFloor, Double bookGradeUpper,int page,int pageSize){
        Integer offset = (page - 1) * pageSize;
        Integer limit=pageSize;

        return bookMapper.findBooks(bookName, bookAuthor, bookPointsFloor, bookPointsUpper, bookGradeFloor, bookGradeUpper, offset, pageSize);

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

public List<Book> findTopNBooks(int n)
{
  return  bookMapper.findTopNBooks(n);
}
}