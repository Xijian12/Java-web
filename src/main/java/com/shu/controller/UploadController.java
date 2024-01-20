package com.shu.controller;

import com.shu.entity.Result;
import com.shu.entity.dto.Account;
import com.shu.entity.vo.request.Material;
import com.shu.entity.vo.request.UpdateAvatarVO;
import com.shu.entity.vo.request.Book;
import com.shu.service.AccountService;
import com.shu.service.BookUploadService;
import com.shu.service.MaterialUploadService;
import com.shu.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    @Autowired
    private MaterialUploadService materialUploadService;

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
            return Result.error("更新图书失败");
        }
    }

    //上传资料封面，并返回资料封面的URL地址和云存储位置
    @PostMapping("/uploadMaterialcover")
    public Result uploadMaterialcover(MultipartFile materialCover) throws IOException {
        log.info("封面上传，封面名为：{}", materialCover.getOriginalFilename());
        List<String> UrlAndUUID = (aliOSSUtils.uploadImage(materialCover, false));

        String fileUrl = UrlAndUUID.get(0);
        String imageUUID = UrlAndUUID.get(1);

        log.info("封面上传URL为：{}", fileUrl);
        log.info("封面上传的云存储地址为：{}", imageUUID);

        return Result.success(UrlAndUUID);
    }

    //更新图书封面，并返回一个URL地址
    @PostMapping("/updateMaterialcover")
    public Result updateMaterialcover(MultipartFile materialCover, String materialId) throws Exception {
        log.info("封面上传，封面名为：", materialCover.getOriginalFilename());
        List<String> UrlAndUUID = (aliOSSUtils.uploadImage(materialCover, false));

        int materialID = Integer.parseInt(materialId);
        Material material = materialUploadService.findMaterialById(materialID);
        if(material == null){
            return Result.error("该图书不存在");
        }
        String originalMaterialcoverAddress = material.getMaterialCoverUuid();

        String fileUrl = UrlAndUUID.get(0);
        String imageUUID = UrlAndUUID.get(1);

        log.info("封面上传URL为：{}", fileUrl);
        log.info("封面上传的云存储地址为：{}", imageUUID);

        if (materialUploadService.updateMaterialcover(materialID, fileUrl, imageUUID)) {
            //调用阿里云工具类删除原来的头像
            if (originalMaterialcoverAddress != null) {
                aliOSSUtils.DeleteFile(originalMaterialcoverAddress);
            }
            return Result.success(fileUrl);
        } else {
            return Result.error("更新资料封面失败");
        }
    }
    @PostMapping("/uploadMaterial")
    public Result uploadMaterialFile(MultipartFile materialFile) throws IOException {
        log.info("资料文件上传，文件名为：{}", materialFile.getOriginalFilename());

        String materialFileFileUUID = aliOSSUtils.uploadFile(materialFile,false);

        log.info("资料文件上传的云存储地址为：{}", materialFileFileUUID);

        return Result.success(materialFileFileUUID);
    }

    //更新图书文件
    @PostMapping("/updateMaterial")
    public Result updateMaterialFile(MultipartFile materialFile, String materialId,String type) throws Exception {
        log.info("图书文件更新，文件名为：{}", materialFile.getOriginalFilename());

        int materialID = Integer.parseInt(materialId);
        int Type = Integer.parseInt(type);
        Material material = materialUploadService.findMaterialById(materialID);
        if(material == null){
            return Result.error("该资料不存在");
        }
        String originalBookFileAddress = null;
        //根据Type判断需要更新什么类型的资料
        switch (Type){
            case 1: {
                originalBookFileAddress = material.getElecBookUuid();
                break;
            }
            case 2: {
                originalBookFileAddress = material.getTeachingPlanUuid();
                break;
            }
            case 3: {
                originalBookFileAddress = material.getClassPptUuid();
                break;
            }
            case 4: {
                originalBookFileAddress = material.getCalendarVolumeUuid();
                break;
            }
            case 5: {
                originalBookFileAddress = material.getAnotherMaterialUuid();
                break;
            }
        }

        String materialFileUUID = aliOSSUtils.uploadFile(materialFile,false);

        log.info("资料文件更新的云存储地址为：{}", materialFileUUID);
        if (materialUploadService.updateMaterialFile(materialID, materialFileUUID,Type)) {
            //调用阿里云工具类删除原来的文件
            if (originalBookFileAddress != null) {
                aliOSSUtils.DeleteFile(originalBookFileAddress);
            }
            return Result.success();
        } else {
            return Result.error("更新封面失败");
        }
    }
    @DeleteMapping("delete")
    public Result deleteFile(String fileUuid) throws Exception {
        log.info("删除的文件的UUID为：{}", fileUuid);

        if(fileUuid != null){
            aliOSSUtils.DeleteFile(fileUuid);
            return Result.success("删除成功");
        }
        return Result.error("文件不存在");
    }
}
