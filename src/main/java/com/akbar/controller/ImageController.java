package com.akbar.controller;

import com.akbar.domain.vo.PageBean;
import com.akbar.service.ImageService;
import com.akbar.util.AliyunOssUtil;
import com.akbar.util.Result;
import com.akbar.util.ValidateImageExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final AliyunOssUtil aliyunOssUtil;
    private final ImageService imageService;

    @Autowired
    public ImageController(AliyunOssUtil aliyunOssUtil, ImageService imageService) {
        this.aliyunOssUtil = aliyunOssUtil;
        this.imageService = imageService;
    }


    // 上传文件
    @PostMapping("/upload")
    public Result<String> upload(@RequestParam MultipartFile file) throws IOException {

        String originalFilename = file.getOriginalFilename();
        // 获取文件扩展名（小写形式）
        String fileExtension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".") + 1) : "";
        if (!ValidateImageExtension.isValidImageExtension(fileExtension)) {
            return new Result<>(0, "文件格式不支持，仅支持png, jpg, jpeg, gif, webp格式的图片", null);
        }

        String url = aliyunOssUtil.upload(file);
        // 获取objectName
        String objectName = url.substring(url.indexOf("oss-cn-beijing.aliyuncs.com/") + "oss-cn-beijing.aliyuncs.com/".length());
        // 保存到数据库
        boolean result = imageService.addImage(url, objectName);
        if (!result) {
            return new Result<>(0, "图片信息保存到数据库失败", null);
        }

        return new Result<>(1, "success", url);
    }


    // 删除文件
    @PostMapping("/delete")
    public Result<Void> delete(@RequestParam String objectName) {
        // 从数据库删除
        boolean result = imageService.deleteImage(objectName);
        if (!result) {
            return  Result.error("删除图片信息失败");
        }
        // 从OSS删除
        aliyunOssUtil.delete(objectName);
        return Result.success("删除成功！");
    }


    // 获取图片列表
    @GetMapping
    public Result<PageBean> imageList(@RequestParam(defaultValue = "1") int pageNum,
                                      @RequestParam(defaultValue = "10") int pageSize) {
        return Result.success(imageService.getImageList(pageNum, pageSize));
    }
}
