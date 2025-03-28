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
    public Result<String> upload(@RequestParam MultipartFile file) throws IOException {
        String url = imageService.addImage(file);
        return Result.success(url);
    }


    /**
     * 删除图片
     */
    @DeleteMapping("/delete")
    public Result<Void> delete(
            @RequestParam String url,
            @RequestParam String objectName) {

        imageService.deleteImage(url, objectName);
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
