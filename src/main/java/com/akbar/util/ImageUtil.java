package com.akbar.util;

public class ImageUtil {

    public static boolean isValidImageExtension(String originalFilename) {
        // 获取文件扩展名
        String fileExtension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".") + 1) : "";

        // 校验图片格式
        String[] allowedExtensions = {"jpg", "jpeg", "png", "gif", "webp", "svg"};
        for (String ext : allowedExtensions) {
            if (ext.equalsIgnoreCase(fileExtension)) {
                return true;
            }
        }
        return false;
    }
}
