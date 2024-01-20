package com.shu.service.impl;

import com.shu.entity.dto.Account;
import com.shu.entity.vo.request.user.BookTop;
import com.shu.entity.vo.request.user.DownloadBook;
import com.shu.mapper.BookMapper;
import com.shu.entity.vo.request.Book;
import com.shu.entity.vo.request.BookDeletionRequest;
import com.shu.service.AccountService;
import com.shu.utils.Constants;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class BookService {
    @Autowired
    private final BookMapper bookMapper;
    @Resource
    AccountService accountService;
    @Scheduled(fixedRate = 43200000)
public void updateBookGrade() {
       bookMapper.updateBookGrade();
        System.out.println("定时任务执行中...");

    }
    public List<Book >GetBookObject(String userEmail){
        return bookMapper.getBookObject(userEmail);
    }

 public void updateClickByVersion(String bookVersion,int bookDownloadNum,int bookClickNum){
        bookMapper.updateClickByversion(bookVersion,bookDownloadNum,bookClickNum);
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
        Account account = accountService.findAccountByNameOrEmail(book.getBookUploader());
        if(account != null){
            String newUserInfo = accountService.updateUserInfo(account.getUsername(), account.getUsername(),
                    account.getPassword(), account.getPoints() + Constants.uploadPointBonus);
        }
        if(book.getBookCoverUrl() == null){
            book.setBookCoverUrl(Constants.defaultBookCoverUrl);
            book.setBookCoverUuid(Constants.defaultBookCoverUuid);
        }
        book.setUpdateTime(LocalDate.now());
        book.setCreateTime(LocalDate.now());
        bookMapper.insertBook(book);
    }

    public Book getBookById(int id) {
        return bookMapper.selectBookById(id);
    }
    public List<Book> getBookByEmail(String userEmail){

        return bookMapper.getBookObject(userEmail);
    }
    public DownloadBook downloadBook(String userEmail,int bookId){
        Book book = bookMapper.selectBookById(bookId);
        DownloadBook download = null;
        //如果下载的是自己的资料，则不扣积分
        if(book != null && userEmail.equals(book.getBookUploader())){
            download = new DownloadBook(bookMapper.downloadBook(userEmail,bookId), 1);
        }else{
            download = new DownloadBook(bookMapper.downloadBook(userEmail,bookId),bookMapper.updateUserPoints(userEmail,bookId));
        }

        return download;
    }
    public List<Book> getAllBooks() {
        return bookMapper.selectAllBooks();
    }
    public BookTop findBooks(String bookName, String bookAuthor, Integer bookPointsFloor, Integer bookPointsUpper, Double bookGradeFloor, Double bookGradeUpper, int page, int pageSize){
        Integer offset = (page - 1) * pageSize;
        Integer limit=pageSize;
        BookTop books=new BookTop();
        books.setTotal(bookMapper.countBooks(bookName, bookAuthor, bookPointsFloor, bookPointsUpper, bookGradeFloor, bookGradeUpper));
        books.setBooks(bookMapper.findBooks(bookName, bookAuthor, bookPointsFloor, bookPointsUpper, bookGradeFloor, bookGradeUpper, offset, pageSize));
        return books;

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

    //返回图书的点击总量
    public Long  getBookTotalClickNum() {
        return bookMapper.selectBookTotalClickNum();
    }

    //返回图书的下载总量
    public Long  getBookTotalDownloadNum() {
        return bookMapper.selectBookTotalDownloadNum();
    }

    //更新某本图书的点击量
    public void addBookClickNum(Integer newBookClickNum,Integer bookId){
        log.info("newBookClickNum:{}",newBookClickNum);
        bookMapper.updateBookClickNum(newBookClickNum,bookId);
    }

    //更新某本图书下载量
    public void addBookDownloadNum(Integer newBookDownloadNum,Integer bookId){
        bookMapper.updateBookDownloadNum(newBookDownloadNum,bookId);
    }

}