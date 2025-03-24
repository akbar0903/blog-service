package com.akbar.constant;

/**
 * 信息提示常量
 */
public class MessageConstant {

    // Admin
    public static final String PASSWORD_ERROR = "密码错误";
    public static final String OLD_PASSWORD_ERROR = "原密码错误";
    public static final String PASSWORDS_DIFFERENT = "两次输入的密码不一样";
    public static final String ACCOUNT_NOT_FOUND = "账号不存在";
    public static final String USERNAME_CANT_BE_EMPTY = "用户名不能为空";
    public static final String PASSWORD_CANT_BE_EMPTY = "密码不能为空";
    public static final String PASSWORD_TOO_SHORT = "密码必须大于等于6个字符";
    public static final String INVALID_EMAIL = "Email格式不准确";
    public static final int MIN_QQ_NUMBER_LENGTH = 9;
    public static final int MAX_QQ_NUMBER_LENGTH = 15;
    public static final String INVALID_QQ_NUMBER = "无效的qq号";

    // PERMISSION
    public static final String PERMISSION_DENIED = "没有权限，拒绝操作";
    public static final String USER_NOT_LOGIN = "请先登录";
    public static final String ALREADY_EXISTS = "已存在";
    public static final String UNKNOWN_ERROR = "未知错误";

    // URL
    public static final String INVALID_URL = "Url格式不准确";

    // Tag
    public static final String TAG_NAME_CANT_BE_EMPTY = "标签名不能为空";
    public static final int TAG_NAME_MAX_LENGTH = 20;
    public static final String TAG_NAME_TOO_LONG = "标签名长度不能超过20个字符！";
    public static final String TAG_HAS_ASSOCIATED_ARTICLES = "当前标签关联了文章，不能删除";

    // Category
    public static final String CATEGORY_ID_CANT_BE_NULL = "分类id不能为空";
    public static final String CATEGORY_NAME_CANT_BE_EMPTY = "分类名不能为空";
    public static final int CATEGORY_NAME_MAX_LENGTH = 20;
    public static final String CATEGORY_NAME_TOO_LONG = "分类名长度不能超过20个字符！";
    public static final String CATEGORY_HAS_ASSOCIATED_ARTICLES = "当前分类关联了文章，不能删除";

    // Article
    public static final String ARTICLE_TITLE_CANT_BE_EMPTY = "文章标题不能为空";
    public static final String ARTICLE_CONTENT_CANT_BE_EMPTY = "文章内容不能为空";
    public static final String ARTICLE_SUMMERY_CANT_BE_EMPTY = "文章摘要不能为空";
    public static final String ARTICLE_ID_CANT_BE_NULL = "文章id不能为空";
    public static final int ARTICLE_TITLE_MAX_LENGTH = 255;
    public static final String ARTICLE_TITLE_TOO_LONG = "文章标题长度不能超过255个字符";
    public static final String ARTICLE_STATE_CANT_BE_NULL = "文章状态不能为空";
    public static final String TAG_IDS_CANT_BE_NULL = "标签列表不能为空";
    public static final int TAG_IDS_MIN_LENGTH = 1;
    public static final String TAG_IDS_TOO_SHORT = "至少需要选择一个标签";

    // Image
    public static final String INVALID_IMAGE_EXTENSION = "当前图片格式不支持上传";

    // MaxUploadSize
    public static final String FILE_SIZE_TOO_LARGE = "文件上传大小超过限制，最大允许10MB";

    // FeaturePlan
    public static final String TO_DO_TITLE_CANT_BE_EMPTY = "任务标题不能为空";
    public static final String TO_DO_TYPE_CANT_BE_EMPTY = "任务类型不能为空";
}
