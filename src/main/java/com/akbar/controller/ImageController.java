package com.akbar.controller;

import com.akbar.pojo.result.PageResult;
import com.akbar.pojo.result.Result;
import com.akbar.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;


    /**
     * 上传图片
     */
    @PostMapping("/upload")
    public Result<Void> upload(@RequestParam MultipartFile file) throws IOException {
        imageService.addImage(file);
        return Result.success();
    }


    /**
     * 删除图片
     */
    @PostMapping("/delete")
    public Result<Void> delete(@RequestParam String objectName) {
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
