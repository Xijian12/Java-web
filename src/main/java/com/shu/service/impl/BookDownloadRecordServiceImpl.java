package com.shu.service.impl;

import com.shu.entity.dto.Account;
import com.shu.entity.vo.request.Book;
import com.shu.entity.vo.request.user.BookDownloadRecord;
import com.shu.entity.vo.request.user.BookRequest;
import com.shu.entity.vo.request.user.MaterialPage;
import com.shu.mapper.BookDownloadRecordMappper;
import com.shu.mapper.BookMapper;
import com.shu.service.AccountService;
import com.shu.service.BookDownloadRecordService;
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
public class BookDownloadRecordServiceImpl implements BookDownloadRecordService {
    @Autowired
    private BookDownloadRecordMappper bookDownloadRecordMappper;
    @Autowired
    private BookMapper bookMapper;
    @Resource
    AccountService accountService;
    @Override
    public void addBookDownloadRecord(BookRequest bookRequest){
        Book bookDetail  = bookMapper.selectBookById(bookRequest.getBookId());
        BookDownloadRecord bookDownloadRecord = new BookDownloadRecord();
        Account account = accountService.findAccountByNameOrEmail(bookRequest.getUserEmail());
        if(account != null){
            bookDownloadRecord.setUsername(account.getUsername());
            bookDownloadRecord.setUserId(account.getId());
        }
        if(bookDetail != null){
            bookDownloadRecord.setBookCoverUrl(bookDetail.getBookCoverUrl());
            bookDownloadRecord.setBookAuthor(bookDetail.getBookAuthor());
            bookDownloadRecord.setUserEmail(bookRequest.getUserEmail());
            bookDownloadRecord.setBookName(bookDetail.getBookName());
            bookDownloadRecord.setBookId(bookDetail.getBookId());
            bookDownloadRecord.setBookAuthor(bookDetail.getBookAuthor());
            bookDownloadRecord.setBookDownloadPoints(bookDetail.getBookPoints());
            bookDownloadRecord.setCreateTime(LocalDateTime.now());
            bookDownloadRecord.setUpdateTime(LocalDateTime.now());
        }
        bookDownloadRecordMappper.insertDownloadRecord(bookDownloadRecord);
    }

    @Override
    public MaterialPage queryBookDownloadRecord(String userEmail, Integer page, Integer pageSize){
        //设置分页参数
        log.info("userEmail:{}",userEmail);
        PageHelper.startPage(page,pageSize);

        //查询结果
        List<BookDownloadRecord> BookDownloadRecordList = bookDownloadRecordMappper.selectBookDownloadRecord(userEmail);
        log.info("BookDownloadRecordList:{}",BookDownloadRecordList);
        //用PageHelper自带的Page类型对查询结果进行强制转型
        Page<BookDownloadRecord> p = (Page<BookDownloadRecord>) BookDownloadRecordList;

        //对查询结果进行封装
        return new MaterialPage(p.getTotal(),p.getResult());
    }

    @Override
    public void deleteBookDownloadRecord(Integer downloadId){
        bookDownloadRecordMappper.deleDownloadRecord(downloadId);
    }
}
