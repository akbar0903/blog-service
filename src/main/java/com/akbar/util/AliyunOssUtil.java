package com.akbar.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
public class AliyunOssUtil {
    // OSS参数
    @Value("${aliyun-oss.end-point}")
    private String endPoint;
    @Value("${aliyun-oss.access-key-id}")
    private String accessKeyId;
    @Value("${aliyun-oss.access-key-secret}")
    private String accessKeySecret;
    @Value("${aliyun-oss.bucket-name}")
    private String bucketName = "blog-ultimate";


    /**
     * 上传文件
     * @param file 要上传的文件
     * @return 返回文件在oss中的url地址
     * 比如：https://blog-ultimate.oss-cn-beijing.aliyuncs.com/7915cb0c-144b-4af0-9e6d-c649bdf9cd4c.JPG
     */
    public String upload(MultipartFile file) throws IOException {
        // 获取上传文件流
        InputStream inputStream = file.getInputStream();

        // 避免文件名被覆盖
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));

        // 上传文件到OSS
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, fileName, inputStream);

        // 关闭OSS客户端
        ossClient.shutdown();

        // 返回OSS文件路径
        return endPoint.split("//")[0] + "//" + bucketName + "." + endPoint.split("//")[1] + "/" + fileName;
    }


    /**
     * 删除文件
     *
     */
    public void delete(String objectName) {
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
        ossClient.deleteObject(bucketName, objectName);

        ossClient.shutdown();
    }


    /**
     * 获取object-name
     * @param url 文件在oss中的地址
     * @return 返回object-name
     * 比如：83fb5950-47b5-4636-a383-dcce0566406f.jpg
     */
    public String getObjectName(String url) {
        String ossUtilName = "oss-cn-beijing.aliyuncs.com/";
        return url.substring(url.indexOf(ossUtilName) + ossUtilName.length());
    }
}