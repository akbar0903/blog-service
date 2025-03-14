package com.akbar.service.impl;

import com.akbar.annotation.RequiresAdmin;
import com.akbar.mapper.ArticleTagMapper;
import com.akbar.mapper.CategoryMapper;
import com.akbar.mapper.TagMapper;
import com.akbar.pojo.entity.Article;
import com.akbar.pojo.dto.ArticleDto;
import com.akbar.pojo.entity.ArticleTag;
import com.akbar.pojo.entity.Category;
import com.akbar.pojo.vo.ArticleVO;
import com.akbar.pojo.vo.PageBean;
import com.akbar.mapper.ArticleMapper;
import com.akbar.service.ArticleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleTagMapper articleTagMapper;


    /**
     * 添加文章
     */
    @RequiresAdmin
    @Override
    public void addArticle(ArticleDto articleDto) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDto, article);
        articleMapper.insert(article);

        //插入文章-标签关系
        //因为插入article产生的主键可以回到article对象中，这里可以直接使用
        Integer articleId = article.getId();
        for(Integer tagId : articleDto.getTagIds()) {
            ArticleTag articleTag = new ArticleTag(articleId, tagId);
            articleTagMapper.insert(articleTag);
        }
    }


    /**
     * 回显文章信息
     */
    @Override
    public ArticleVO getArticleVo(Integer id) {
        return articleMapper.selectArticleVoById(id);
    }


    @Override
    public void updateArticle(Article article) {
        LocalDateTime now = LocalDateTime.now();
        article.setUpdatedTime(now);
        articleMapper.updateArticle(article);
    }

    @Override
    public PageBean page(Integer pageNum, Integer pageSize, String title, Integer categoryId, Integer tagId, String state) {
        PageHelper.startPage(pageNum, pageSize);

        List<ArticleDto> articleResults = articleMapper.pageArticle(title, categoryId, tagId, state);
        Page<ArticleDto> page = (Page<ArticleDto>) articleResults;
        return new PageBean(page.getTotal(),page.getResult());
    }

    @Override
    public void deleteArticle(Integer id) {
        articleMapper.deleteArticle(id);
    }
}
