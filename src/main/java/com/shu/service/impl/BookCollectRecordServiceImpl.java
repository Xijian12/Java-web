package com.shu.service.impl;

import com.shu.entity.dto.Account;
import com.shu.entity.vo.request.Book;
import com.shu.entity.vo.request.user.BookCollectRecord;
import com.shu.entity.vo.request.user.BookRequest;
import com.shu.entity.vo.request.user.MaterialPage;
import com.shu.mapper.BookCollectRecordMappper;
import com.shu.mapper.BookMapper;
import com.shu.service.AccountService;
import com.shu.service.BookCollectRecordService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class BookCollectRecordServiceImpl implements BookCollectRecordService {
    @Autowired
    private BookCollectRecordMappper bookCollectRecordMappper;
    @Autowired
    private BookMapper bookMapper;
    @Resource
    AccountService accountService;
    @Override
    public void addBookCollectRecord(BookRequest bookRequest) {
        Book bookDetail  = bookMapper.selectBookById(bookRequest.getBookId());
        BookCollectRecord bookCollectRecord = new BookCollectRecord();
        Account account = accountService.findAccountByNameOrEmail(bookRequest.getUserEmail());
        if(account != null){
            bookCollectRecord.setUsername(account.getUsername());
            bookCollectRecord.setUserId(account.getId());
        }
        if(bookDetail != null){
            bookCollectRecord.setBookCoverUrl(bookDetail.getBookCoverUrl());
            bookCollectRecord.setBookAuthor(bookDetail.getBookAuthor());
            bookCollectRecord.setUserEmail(bookRequest.getUserEmail());
            bookCollectRecord.setBookName(bookDetail.getBookName());
            bookCollectRecord.setBookId(bookDetail.getBookId());
            bookCollectRecord.setBookAuthor(bookDetail.getBookAuthor());
            bookCollectRecord.setBookDownloadPoints(bookDetail.getBookPoints());
            bookCollectRecord.setCreateTime(LocalDateTime.now());
            bookCollectRecord.setUpdateTime(LocalDateTime.now());
        }
        bookCollectRecordMappper.insertBookCollectRecord(bookCollectRecord);
    }

    @Override
    public MaterialPage queryBookCollectRecord(String userEmail, Integer page, Integer pageSize){
        Account account = accountService.findAccountByNameOrEmail(userEmail);
        if(account == null){
            return null;
        }
        //设置分页参数
        log.info("userEmail:{}",userEmail);
        PageHelper.startPage(page,pageSize);

        //查询结果
        List<BookCollectRecord> BookCollectRecordList = bookCollectRecordMappper.queryBookCollectRecord(userEmail);
        log.info("BookCollectRecordList:{}",BookCollectRecordList);
        //用PageHelper自带的Page类型对查询结果进行强制转型
        Page<BookCollectRecord> p = (Page<BookCollectRecord>) BookCollectRecordList;

        //对查询结果进行封装
        return new MaterialPage(p.getTotal(),p.getResult());
    }

    @Override
    public void deleteBookCollectRecord(Integer collectId) {
        bookCollectRecordMappper.deleteBookCollectRecord(collectId);
    }
    @Override
    public String addBookCollectRecord(BookCollectRecord bookCollectRecord){
        Book book = bookMapper.selectBookById(bookCollectRecord.getBookId());
        BookCollectRecord repeatBookCollectRecord = bookCollectRecordMappper.isRepeatCollectRecord(bookCollectRecord.getUserEmail(),bookCollectRecord.getBookId());
        Account account = accountService.findAccountByNameOrEmail(bookCollectRecord.getUserEmail());
        if(repeatBookCollectRecord != null){
            return "图书已经收藏过了";
        }
        if(account != null){
            bookCollectRecord.setUserId(account.getId());
            bookCollectRecord.setUsername(account.getUsername());
        }else{
            return "用户不存在";
        }
        if(book != null){
            bookCollectRecord.setBookAuthor(book.getBookAuthor());
            bookCollectRecord.setBookName(book.getBookName());
            bookCollectRecord.setBookCoverUrl(book.getBookCoverUrl());
            bookCollectRecord.setBookDownloadPoints(book.getBookPoints());
            bookCollectRecord.setCreateTime(LocalDateTime.now());
            bookCollectRecord.setUpdateTime(LocalDateTime.now());
            bookCollectRecordMappper.insertBookCollectRecord(bookCollectRecord);
            return null;
        }else{
            return "图书不存在";
        }
    }
}
