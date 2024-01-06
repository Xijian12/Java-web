package com.example.controller;

import com.example.entity.RestBean;
import com.example.entity.Result;
import com.example.entity.dto.Account;
import com.example.entity.vo.request.UpdateAvatarVO;
import com.example.mapper.AccountMapper;
import com.example.service.AccountService;
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

    @PostMapping("/uploadAvatar")
    public Result uploadAvatar(MultipartFile avatar,String username) throws IOException {
        log.info("图片上传，头像名为：{}", avatar.getOriginalFilename());
        List<String> UrlAndUUID = (aliOSSUtils.uploadImage(avatar,true));

        String fileUrl = UrlAndUUID.get(0);
        String imageUUID = UrlAndUUID.get(1);

        log.info("图片上传URL为：{}",fileUrl);
        log.info("图片上传的名字为：{}",imageUUID);

        UpdateAvatarVO updateAvatarVO=new UpdateAvatarVO(username,fileUrl,imageUUID);
        boolean isSuccess = accountService.updateAvatar(updateAvatarVO.getUsername(),
                updateAvatarVO.getNewAvatarUrl(),updateAvatarVO.getNewAvatarUuid());
        if (isSuccess) {
        return Result.success(UrlAndUUID);
        }
        else {
            return Result.error("上传头像失败");
        }
    }
    @PostMapping("/updateAvatar")
    public Result updateAvatar(MultipartFile avatar,String username) throws Exception {
        log.info("图片上传，头像名为：{}", avatar.getOriginalFilename());
        //查找用户的账号密码，并得到原来的头像地址，
        Account account = accountService.findAccountByNameOrEmail(username);
        String fileAddress = account.getAvatarUuid();
        List<String> UrlAndUUID = (aliOSSUtils.uploadImage(avatar,true));

        String fileUrl = UrlAndUUID.get(0);
        String imageUUID = UrlAndUUID.get(1);

        log.info("图片上传URL为：{}",fileUrl);
        log.info("图片上传的名字为：{}",imageUUID);

        UpdateAvatarVO updateAvatarVO=new UpdateAvatarVO(username,fileUrl,imageUUID);
        boolean isSuccess = accountService.updateAvatar(updateAvatarVO.getUsername(),
                updateAvatarVO.getNewAvatarUrl(),updateAvatarVO.getNewAvatarUuid());
        if (isSuccess) {
            //调用阿里云工具类删除原来的头像
            aliOSSUtils.DeleteFile(fileAddress);
            return Result.success(UrlAndUUID);
        }
        else {
            return Result.error("更新头像失败");
        }
    }

    @PostMapping("/bookcover")
    public Result uploadBookCover(MultipartFile BookCover) throws IOException {

        log.info("封面上传，封面名为：",BookCover.getOriginalFilename());
        List<String> UrlAndUUID = (aliOSSUtils.uploadImage(BookCover,false));

        String fileUrl = UrlAndUUID.get(0);
        String imageUUID = UrlAndUUID.get(1);

        log.info("封面上传URL为：",fileUrl);
        log.info("封面上传的名字为：",imageUUID);

        return Result.success(fileUrl);
    }
}
