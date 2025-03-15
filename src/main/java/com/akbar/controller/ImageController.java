package com.akbar.controller;

import com.akbar.constant.MessageConstant;
import com.akbar.exception.FileUploadException;
import com.akbar.pojo.result.PageResult;
import com.akbar.pojo.result.Result;
import com.akbar.service.ImageService;
import com.akbar.util.AliyunOssUtil;
import com.akbar.util.ValidateImageExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private AliyunOssUtil aliyunOssUtil;

    @Autowired
    private ImageService imageService;


    /**
     * 上传图片
     */
    @PostMapping("/upload")
    public Result<Void> upload(@RequestParam MultipartFile file) throws IOException {

        // 获取源文件名
        String originalFilename = file.getOriginalFilename();

        // 校验文件扩展名
        boolean result = ValidateImageExtension.isValidImageExtension(originalFilename);
        if (!result) {
            throw new FileUploadException(MessageConstant.INVALID_IMAGE_EXTENSION);
        }

        // 上传到oss
        String url = aliyunOssUtil.upload(file);

        // 获取上传后文件的object-name
        String objectName = aliyunOssUtil.getObjectName(url);

        // 存储到image表中
        imageService.addImage(url, objectName);

        return Result.success();
    }


    /**
     * 删除图片
     */
    @PostMapping("/delete")
    public Result<Void> delete(@RequestParam String objectName) {

        // 从OSS删除
        aliyunOssUtil.delete(objectName);

        // 从数据库删除
        imageService.deleteImage(objectName);

        return Result.success();
    }


    /**
     * 获取图片列表
     */
    @GetMapping
    public Result<PageResult> imageList(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageResult pageResult = imageService.getImageList(pageNum, pageSize);
        return Result.success(pageResult);
    }
}
