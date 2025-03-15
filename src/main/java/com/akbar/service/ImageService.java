package com.akbar.service;

import com.akbar.pojo.result.PageResult;

public interface ImageService {
    boolean addImage(String url, String objectName);

    boolean deleteImage(String objectName);

    PageResult getImageList(int pageNum, int pageSize);
}
