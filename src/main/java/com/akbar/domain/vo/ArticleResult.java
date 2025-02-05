package com.akbar.domain.vo;

import com.akbar.domain.entity.Article;

public class ArticleResult extends Article {
    private String categoryName;
    private String  tagName;
    private String authorName;

    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTagName() {
        return tagName;
    }
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "ArticleResult{" +
                "categoryName='" + categoryName + '\'' +
                ", tagName='" + tagName + '\'' +
                ", authorName='" + authorName + '\'' +
                '}';
    }
}
