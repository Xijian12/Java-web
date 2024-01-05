package com.example.controller;

import com.example.entity.Result;
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

    @PostMapping("/avatar")
    public Result uploadAvatar(MultipartFile avatar) throws IOException {

        log.info("图片上传，头像名为：",avatar.getOriginalFilename());
        List<String> UrlAndUUID = (aliOSSUtils.uploadAvatar(avatar));

        String fileUrl = UrlAndUUID.get(0);
        String imageUUID = UrlAndUUID.get(1);

        log.info("图片上传URL为：",fileUrl);
        log.info("图片上传的名字为：",imageUUID);

        return Result.success(UrlAndUUID);
    }

    @PostMapping("/bookcover")
    public Result uploadBookCover(MultipartFile BookCover) throws IOException {

        log.info("封面上传，封面名为：",BookCover.getOriginalFilename());
        List<String> UrlAndUUID = (aliOSSUtils.uploadAvatar(BookCover));

        String fileUrl = UrlAndUUID.get(0);
        String imageUUID = UrlAndUUID.get(1);

        log.info("封面上传URL为：",fileUrl);
        log.info("封面上传的名字为：",imageUUID);

        return Result.success(UrlAndUUID);
    }
}
