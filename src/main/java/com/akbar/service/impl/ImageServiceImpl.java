package com.akbar.service.impl;

import com.akbar.pojo.entity.Image;
import com.akbar.pojo.vo.PageBean;
import com.akbar.mapper.ImageMapper;
import com.akbar.service.ImageService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageMapper imageMapper;

    @Autowired
    public ImageServiceImpl(ImageMapper imageMapper) {
        this.imageMapper = imageMapper;
    }

    @Override
    public boolean addImage(String url, String objectName) {
        Integer adminId = 9;
        int result = imageMapper.addImage(url, objectName, adminId);
        return result > 0;
    }


    @Override
    public boolean deleteImage(String objectName) {
        int result = imageMapper.deleteImage(objectName);
        return result > 0;
    }


    @Override
    public PageBean getImageList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Image> imageList = imageMapper.getImageList();
        Page<Image> page = (Page<Image>) imageList;
        return new PageBean(page.getTotal(), page.getResult());
    }
}
