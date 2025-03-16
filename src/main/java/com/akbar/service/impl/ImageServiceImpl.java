package com.akbar.service.impl;

import com.akbar.annotation.RequiresAdmin;
import com.akbar.constant.MessageConstant;
import com.akbar.context.BaseContext;
import com.akbar.exception.FileUploadException;
import com.akbar.pojo.entity.Image;
import com.akbar.mapper.ImageMapper;
import com.akbar.pojo.result.PageResult;
import com.akbar.service.ImageService;
import com.akbar.util.AliyunOssUtil;
import com.akbar.util.ImageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private AliyunOssUtil aliyunOssUtil;


    /**
     * 上传图片
     */
    @RequiresAdmin
    @Override
    public void addImage(MultipartFile file) throws IOException {
        //获取源文件名
        String originalFilename = file.getOriginalFilename();

        // 校验文件扩展名
        boolean result = ImageUtil.isValidImageExtension(originalFilename);
        if (!result) {
            throw new FileUploadException(MessageConstant.INVALID_IMAGE_EXTENSION);
        }

        // 上传到oss
        String url = aliyunOssUtil.upload(file);

        // 获取上传后文件的object-name
        String objectName = aliyunOssUtil.getObjectName(url);

        // 存储到数据库
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
        // 从OSS删除
        aliyunOssUtil.delete(objectName);
        // 从数据库中删除
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
