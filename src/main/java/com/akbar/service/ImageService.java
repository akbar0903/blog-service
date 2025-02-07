package com.akbar.service;

import com.akbar.domain.vo.PageBean;

public interface ImageService {
    boolean addImage(String url, String objectName);

    boolean deleteImage(String objectName);

    PageBean getImageList(int pageNum, int pageSize);
}
