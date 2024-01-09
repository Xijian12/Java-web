package com.example.controller;

import com.example.entity.Result;
import com.example.entity.dto.Account;
import com.example.entity.vo.request.UpdateAvatarVO;
import com.example.entity.vo.request.Book;
import com.example.service.AccountService;
import com.example.service.BookUploadService;
import com.example.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;
    @Autowired
    private AccountService accountService;
    @Autowired
    private BookUploadService bookUploadService;

    @PostMapping("/uploadAvatar")
    public Result uploadAvatar(MultipartFile avatar, String username) throws IOException {
        log.info("图片上传，头像名为：{}", avatar.getOriginalFilename());
        List<String> UrlAndUUID = (aliOSSUtils.uploadImage(avatar, true));

        String fileUrl = UrlAndUUID.get(0);
        String imageUUID = UrlAndUUID.get(1);

        log.info("图片上传URL为：{}", fileUrl);
        log.info("图片上传的云存储地址为：{}", imageUUID);

        UpdateAvatarVO updateAvatarVO = new UpdateAvatarVO(username, fileUrl, imageUUID);
        boolean isSuccess = accountService.updateAvatar(updateAvatarVO.getUsername(),
                updateAvatarVO.getNewAvatarUrl(), updateAvatarVO.getNewAvatarUuid());
        if (isSuccess) {
            return Result.success(UrlAndUUID);
        } else {
            return Result.error("上传头像失败");
        }
    }

    @PostMapping("/updateAvatar")
    public Result updateAvatar(MultipartFile avatar, String username) throws Exception {
        log.info("图片上传，头像名为：{}", avatar.getOriginalFilename());
        //查找用户的账号密码，并得到原来的头像地址，
        Account account = accountService.findAccountByNameOrEmail(username);
        String fileAddress = account.getAvatarUuid();
        List<String> UrlAndUUID = (aliOSSUtils.uploadImage(avatar, true));

        String fileUrl = UrlAndUUID.get(0);
        String imageUUID = UrlAndUUID.get(1);

        log.info("图片上传URL为：{}", fileUrl);
        log.info("图片上传的云存储地址为：{}", imageUUID);

        UpdateAvatarVO updateAvatarVO = new UpdateAvatarVO(username, fileUrl, imageUUID);
        boolean isSuccess = accountService.updateAvatar(updateAvatarVO.getUsername(),
                updateAvatarVO.getNewAvatarUrl(), updateAvatarVO.getNewAvatarUuid());
        if (isSuccess) {
            //调用阿里云工具类删除原来的头像
            if(fileAddress!=null){
                aliOSSUtils.DeleteFile(fileAddress);
            }
            return Result.success(UrlAndUUID);
        } else {
            return Result.error("更新头像失败");
        }
    }

    //上传图书封面，并返回图书封面的URL地址和云存储位置
    @PostMapping("/uploadBookcover")
    public Result uploadBookCover(MultipartFile bookCover) throws IOException {


        log.info("封面上传，封面名为：{}", bookCover.getOriginalFilename());
        List<String> UrlAndUUID = (aliOSSUtils.uploadImage(bookCover, false));

        String fileUrl = UrlAndUUID.get(0);
        String imageUUID = UrlAndUUID.get(1);

        log.info("封面上传URL为：{}", fileUrl);
        log.info("封面上传的云存储地址为：{}", imageUUID);

        return Result.success(UrlAndUUID);
    }

    //更新图书封面，并返回一个URL地址
    @PostMapping("/updateBookcover")
    public Result updateBookCover(MultipartFile bookCover, String bookId) throws Exception {
        log.info("封面上传，封面名为：", bookCover.getOriginalFilename());
        List<String> UrlAndUUID = (aliOSSUtils.uploadImage(bookCover, false));

        int bookID = Integer.parseInt(bookId);
        Book book = bookUploadService.findBookById(bookID);
        if(book == null){
            return Result.error("该图书不存在");
        }
        String originalBookcoverAddress = book.getBookCoverUuid();

        String fileUrl = UrlAndUUID.get(0);
        String imageUUID = UrlAndUUID.get(1);

        log.info("封面上传URL为：{}", fileUrl);
        log.info("封面上传的云存储地址为：{}", imageUUID);

        if (bookUploadService.updateBookcover(bookID, fileUrl, imageUUID)) {
            //调用阿里云工具类删除原来的头像
            if (originalBookcoverAddress != null) {
                aliOSSUtils.DeleteFile(originalBookcoverAddress);
            }
            return Result.success(fileUrl);
        } else {
            return Result.error("更新封面失败");
        }
    }

    //上传图书文件，并返回图书文件在云存储的地址
    @PostMapping("/uploadBook")
    public Result uploadBookFile(MultipartFile bookFile) throws IOException {
        log.info("图书文件上传，文件名为：{}", bookFile.getOriginalFilename());

        String bookFileUUID = aliOSSUtils.uploadFile(bookFile,true);

        log.info("图书文件上传的云存储地址为：{}", bookFileUUID);

        return Result.success(bookFileUUID);
    }

    //更新图书文件
    @PostMapping("/updateBook")
    public Result updateBookFile(MultipartFile bookFile, String bookId) throws Exception {
        log.info("图书文件更新，文件名为：{}", bookFile.getOriginalFilename());

        int bookID = Integer.parseInt(bookId);
        Book book = bookUploadService.findBookById(bookID);
        if(book == null){
            return Result.error("该图书不存在");
        }
        String originalBookFileAddress = book.getBookCoverUuid();

        String bookFileUUID = aliOSSUtils.uploadFile(bookFile,true);

        log.info("图书文件更新的云存储地址为：{}", bookFileUUID);
        if (bookUploadService.updateBookFile(bookID, bookFileUUID)) {
            //调用阿里云工具类删除原来的文件
            if (originalBookFileAddress != null) {
                aliOSSUtils.DeleteFile(originalBookFileAddress);
            }
            return Result.success();
        } else {
            return Result.error("更新封面失败");
        }
    }
}
