package com.akbar.service.impl;

import com.akbar.annotation.RequiresAdmin;
import com.akbar.context.BaseContext;
import com.akbar.pojo.entity.Image;
import com.akbar.mapper.ImageMapper;
import com.akbar.pojo.result.PageResult;
import com.akbar.service.ImageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;


    /**
     * 上传图片
     */
    @RequiresAdmin
    @Override
    public void addImage(String url, String objectName) {
        Integer adminId = BaseContext.getCurrentAdminId();
        Image image = Image.builder()
                .url(url)
                .objectName(objectName)
                .adminId(adminId)
                .uploadTime(LocalDateTime.now())
                .build();

        imageMapper.addImage(image);
    }


    /**
     * 删除图片
     */
    @RequiresAdmin
    @Override
    public void deleteImage(String objectName) {
        imageMapper.delete(objectName);
    }


    /**
     * 获取图片分页列表
     */
    @Override
    public PageResult getImageList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        List<Image> imageList = imageMapper.selectImagePage();

        PageInfo<Image> pageInfo = new PageInfo<>(imageList);

        return new PageResult(pageInfo.getTotal(), pageInfo.getList());
    }
}
