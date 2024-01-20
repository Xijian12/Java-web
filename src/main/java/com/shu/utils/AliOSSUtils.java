package com.shu.utils;

import com.aliyun.oss.*;
import com.aliyun.oss.internal.OSSHeaders;
import com.aliyun.oss.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 阿里云 OSS 工具类
 */
@Slf4j          //导入日志库
@Component
public class AliOSSUtils {
    @Autowired
    private AliOSSPropreties aliOSSPropreties;

    /**
     * 实现上传头像、封面到OSS,返回文件的URL地址和文件名UUID
     */
    //ISAVATAR为真，则表示上传的是头像，反之则表示上传的是图书封面
    public List<String> uploadImage(MultipartFile file,boolean ISAVATAR) throws IOException {
        String endpoint = aliOSSPropreties.getEndpoint();
        String accessKeyId = aliOSSPropreties.getAccessKeyId();
        String accessKeySecret = aliOSSPropreties.getAccessKeySecret();
        String bucketName = aliOSSPropreties.getBucketName();
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();

        // 避免文件覆盖
        String catalogueAvatarOrBookCover = "";
        //如果ISAVATAR为真，则代表上传的是头像，则添加到/Avatar目录下，反之，则添加到/BookCover目录下
        if(ISAVATAR){
            catalogueAvatarOrBookCover = aliOSSPropreties.getCatalogueAvatar();
        }else{
            catalogueAvatarOrBookCover = aliOSSPropreties.getCatalogueCover();
        }

        String originalFilename = file.getOriginalFilename();
        String newfileName = catalogueAvatarOrBookCover + UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //创建请求类
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, newfileName, inputStream);
        // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        metadata.setObjectAcl(CannedAccessControlList.PublicRead);
        putObjectRequest.setMetadata(metadata);

        //上传文件到 OSS
        PutObjectResult result = ossClient.putObject(putObjectRequest);

        //文件访问路径
        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + newfileName;
        // 关闭ossClient
        ossClient.shutdown();

        List<String> UrlAndUUID = new ArrayList<>();
        UrlAndUUID.add(url);
        UrlAndUUID.add(newfileName);

        return UrlAndUUID;// 把上传到oss的路径返回
    }

    //上传其他文件，例如图书，资料等
    //ISBOOK为真，表是上传的是图书，ISBOOK为假，表是上传的是资料
    public String uploadFile(MultipartFile file,boolean ISBOOK) throws IOException {
        String endpoint = aliOSSPropreties.getEndpoint();
        String accessKeyId = aliOSSPropreties.getAccessKeyId();
        String accessKeySecret = aliOSSPropreties.getAccessKeySecret();
        String bucketName = aliOSSPropreties.getBucketName();
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();

        String catalogueBookOrMaterial = "";
        //如果ISAVATAR为真，则代表上传的是头像，则添加到/Avatar目录下，反之，则添加到/BookCover目录下
        if(ISBOOK){
            catalogueBookOrMaterial = aliOSSPropreties.getCatalogueBooks();
        }else{
            catalogueBookOrMaterial = aliOSSPropreties.getCatalogueMaterial();
        }
        // 避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        String newfileName = catalogueBookOrMaterial + UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //创建请求类
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, newfileName, inputStream);

        // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        metadata.setObjectAcl(CannedAccessControlList.Private);
        putObjectRequest.setMetadata(metadata);

        //上传文件到 OSS
        PutObjectResult result = ossClient.putObject(putObjectRequest);

        return newfileName;
    }

    //获取文件的下载URL，需要传入文件的地址
    public String GetFileDownloadUrl(String objectName,String newFileName) throws IOException {
        String endpoint = aliOSSPropreties.getEndpoint();
        String accessKeyId = aliOSSPropreties.getAccessKeyId();
        String accessKeySecret = aliOSSPropreties.getAccessKeySecret();
        String bucketName = aliOSSPropreties.getBucketName();
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        URL signedUrl = null;
        try {
            // 指定生成的签名URL过期时间，单位为毫秒。本示例以设置过期时间为1小时为例。
            Date expiration = new Date(new Date().getTime() + 3600 * 1000L);

            // 生成签名URL。
            GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.GET);
            // 设置过期时间。
            request.setExpiration(expiration);
            ResponseHeaderOverrides Headers=new ResponseHeaderOverrides();
            Headers.setContentDisposition(String.format("attachment;filename=%s",newFileName));
            request.setResponseHeaders(Headers);
            // 通过HTTP GET请求生成签名URL。
            signedUrl = ossClient.generatePresignedUrl(request);
            // 打印签名URL。
            //log.info("signed url for getObject: " + signedUrl);
        } catch (OSSException oe) {
            log.info("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            log.info("Error Message:" + oe.getErrorMessage());
            log.info("Error Code:" + oe.getErrorCode());
            log.info("Request ID:" + oe.getRequestId());
            log.info("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            log.info("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            log.info("Error Message:" + ce.getMessage());
        }
        return String.valueOf(signedUrl);
    }

    //删除文件，需要传入文件的地址
    public void DeleteFile(String objectName) throws Exception {
        String endpoint = aliOSSPropreties.getEndpoint();
        String accessKeyId = aliOSSPropreties.getAccessKeyId();
        String accessKeySecret = aliOSSPropreties.getAccessKeySecret();
        String bucketName = aliOSSPropreties.getBucketName();
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            // 删除文件或目录。如果要删除目录，目录必须为空。
            ossClient.deleteObject(bucketName, objectName);
        } catch (OSSException oe) {
            log.info("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            log.info("Error Message:" + oe.getErrorMessage());
            log.info("Error Code:" + oe.getErrorCode());
            log.info("Request ID:" + oe.getRequestId());
            log.info("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            log.info("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            log.info("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}

