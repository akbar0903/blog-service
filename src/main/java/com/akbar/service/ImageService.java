package com.akbar.service;

import com.akbar.pojo.result.PageResult;

public interface ImageService {
    void addImage(String url, String objectName);

    void deleteImage(String objectName);

    PageResult getImageList(Integer pageNum, Integer pageSize);
}
