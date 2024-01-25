package com.shu.service.impl;

import com.github.pagehelper.PageHelper;
import com.shu.entity.dto.Account;
import com.shu.entity.vo.request.Material;
import com.shu.entity.vo.request.Page;
import com.shu.entity.vo.request.user.BookTop;
import com.shu.entity.vo.request.user.DownloadBook;
import com.shu.entity.vo.request.user.MaterialPage;
import com.shu.mapper.BookMapper;
import com.shu.entity.vo.request.Book;
import com.shu.entity.vo.request.BookDeletionRequest;
import com.shu.service.AccountService;
import com.shu.utils.AliOSSUtils;
import com.shu.utils.Constants;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class BookService {
    @Autowired
    private final BookMapper bookMapper;
    @Resource
    AccountService accountService;
    @Autowired
    AliOSSUtils aliOSSUtils;
    @Scheduled(fixedRate = 43200000)
    public void updateBookGrade() {
       bookMapper.updateBookGrade();
       log.info("定时任务执行中...");

    }
    public List<Book >GetBookObject(String userEmail,Integer offset,Integer size ){
        return bookMapper.getBookObject(userEmail,offset,size);
    }

 public void updateClickByVersion(String bookVersion,int bookDownloadNum,int bookClickNum){
        bookMapper.updateClickByversion(bookVersion,bookDownloadNum,bookClickNum);
 }
    public boolean deleteBooksIfAdmin(BookDeletionRequest request) {
//        // 检查是否为管理员
//        if (!bookMapper.isAdmin(request.getAdminAccount())){
//            return false;
//        }
        //删除图书评论
        for(int i=0;i<request.getBookIds().size();i++){
            bookMapper.deleteBookCommentByIds(request.getBookIds().get(i));
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
    public String createBook(Book book) throws Exception {
        log.info("book:{}",book);
        if(bookMapper.selectRepeatBook(book.getBookVersion(),book.getBookUploader()) != null){
            Book originBook = bookMapper.selectBookByVersion(book.getBookVersion());
            if(book.getBookFileUuid() != null && !book.getBookFileUuid().equals("") && !book.getBookFileUuid().equals(originBook.getBookFileUuid())){
                aliOSSUtils.DeleteFile(book.getBookFileUuid());
            }
            if(book.getBookCoverUuid() != null && !book.getBookCoverUuid().equals("") && !book.getBookCoverUuid().equals(originBook.getBookCoverUuid())){
                aliOSSUtils.DeleteFile(book.getBookCoverUuid());
            }
            return "不能重复上传图书";
        }
        Account account = accountService.findAccountByNameOrEmail(book.getBookUploader());
        if(account != null){
            String newUserInfo = accountService.updateUserPoints(account.getEmail(),account.getPoints() + Constants.uploadPointBonus);
        }
        if(book.getBookCoverUrl() == null || book.getBookCoverUrl().equals("")){
            book.setBookCoverUrl(Constants.defaultBookCoverUrl);
            book.setBookCoverUuid(Constants.defaultBookCoverUuid);
        }
        book.setBookClickNum(0);
        book.setBookDownloadNum(0);
        book.setBookGrade(null);
        book.setUpdateTime(LocalDate.now());
        book.setCreateTime(LocalDate.now());
        bookMapper.insertBook(book);
        return null;
    }

    public Book getBookById(int id) {
        return bookMapper.selectBookById(id);
    }
    public Page<Book> getBookByEmail(String userEmail,int page,int size){
       int offset=(page-1)*size;
       Page<Book> book=new Page<Book>();
       book.setContent(bookMapper.getBookObject(userEmail, offset, size));
       book.setTotalElements(bookMapper.countBooksById(userEmail));
       book.setPage(page);
       book.setSize(size);
        return book;
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
    public MaterialPage findBooks(String bookName, int page, int pageSize){
//        Integer offset = (page - 1) * pageSize;
//        Integer limit=pageSize;
//        BookTop books=new BookTop();
//        books.setTotal(bookMapper.countBooks(bookName, bookAuthor, bookPointsFloor, bookPointsUpper, bookGradeFloor, bookGradeUpper));
//        books.setBooks(bookMapper.findBooks(bookName, bookAuthor, bookPointsFloor, bookPointsUpper, bookGradeFloor, bookGradeUpper, offset, pageSize));
//        return books;
        //设置分页参数
        PageHelper.startPage(page,pageSize);

        //查询结果
        List<Book> bookList = bookMapper.findBooks(bookName);
        log.info("bookList:{}",bookList);
        //用PageHelper自带的Page类型对查询结果进行强制转型
        com.github.pagehelper.Page<Book> p = (com.github.pagehelper.Page<Book>) bookList;

        //对查询结果进行封装
        return new MaterialPage(p.getTotal(),p.getResult());
    }

    public int updateBook(Book book) {
        return bookMapper.updateBook(book);
    }
    public boolean deleteBooksIfUser(BookDeletionRequest request) {

        if (!bookMapper.isUser(request.getUserEmail())) {
            return false;//检查是否为用户
        }
        //检查是否是用户上传的图书
        for(int j=0;j<request.getBookIds().size();j++){
            if(!Objects.equals(request.getUserEmail(), bookMapper.selectBookById(request.getBookIds().get(j)).getBookUploader()))
            {
                return false;
            }
        }
        //删除图书评论
        for(int i=0;i<request.getBookIds().size();i++){
        bookMapper.deleteBookCommentByIds(request.getBookIds().get(i));}

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