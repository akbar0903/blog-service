package com.akbar.util;

import com.akbar.constant.ImageConstant;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class ImageUtil {

    /**
     * 图片扩展名校验
     *
     * @param originalFilename 本地文件名
     */
    public static boolean isValidImageExtension(String originalFilename) {
        // 获取文件扩展名
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf('.') + 1);

        // 校验图片格式
        // 我有存svg的需求，而且svg大小不可能超过500kb
        String[] allowedExtensions = {"jpg", "jpeg", "png", "gif", "svg"};
        for (String ext : allowedExtensions) {
            if (ext.equalsIgnoreCase(fileExtension)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 压缩图片到指定大小以下
     *
     * @param file file 原始文件
     * @return 压缩后的MultipartFile
     */
    public static MultipartFile compressImage(MultipartFile file) throws IOException {
        long sizeInKB = file.getSize() / 1024;
        double quality = ImageConstant.COMPRESSION_QUALITY;

        // 如果小于最大图片大小限制，直接输出原来的图片
        if (sizeInKB <= ImageConstant.MAX_IMAGE_SIZE) {
            return file;
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // 提取原始文件扩展名（如 jpg、png 等）
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf('.') + 1);
        log.info("开始对" + originalFilename + "进行压缩处理。。。");


        // 先缓存文件的原始字节数据
        byte[] fileBytes = file.getBytes();

        while (true) {
            baos.reset();
            try (InputStream inputStream = new ByteArrayInputStream(fileBytes)) {
                Thumbnails.of(inputStream)
                        .scale(1.0)           // 保持原始尺寸
                        .outputQuality(quality)
                        .outputFormat(extension)
                        .toOutputStream(baos);
            }

            // 检查压缩后文件的大小
            if (baos.size() / 1024 <= ImageConstant.MAX_IMAGE_SIZE || quality <= 0.1) {
                break;
            }

            // 降低图片质量，每轮降低10%的质量
            quality -= 0.1;
        }

        return new CustomMultipartFile(
                baos.toByteArray(),
                file.getName(),
                file.getOriginalFilename(),
                file.getContentType()
        );
    }
}
