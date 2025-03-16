package com.akbar.service;

import com.akbar.pojo.result.PageResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    void addImage(MultipartFile file) throws IOException;

    void deleteImage(String objectName);

    PageResult getImageList(Integer pageNum, Integer pageSize);
}
