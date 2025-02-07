package com.akbar.controller;

import com.akbar.util.AliyunOssUtil;
import com.akbar.util.Result;
import com.akbar.util.ValidateImageExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    private final AliyunOssUtil aliyunOssUtil;

    @Autowired
    public FileController(AliyunOssUtil aliyunOssUtil) {
        this.aliyunOssUtil = aliyunOssUtil;
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
        return new Result<>(1, "success", url);
    }


    // 删除文件
    @PostMapping("/delete")
    public Result<Void> delete(@RequestParam String url) {
        aliyunOssUtil.delete(url);
        return Result.success("删除成功！");
    }
}
