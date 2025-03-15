package com.akbar.service.impl;

import com.akbar.annotation.RequiresAdmin;
import com.akbar.mapper.ArticleTagMapper;
import com.akbar.pojo.dto.article.ArticlePageDto;
import com.akbar.pojo.dto.article.ArticleUpdateDto;
import com.akbar.pojo.entity.Article;
import com.akbar.pojo.dto.article.ArticleDto;
import com.akbar.pojo.entity.ArticleTag;
import com.akbar.pojo.result.PageResult;
import com.akbar.pojo.vo.ArticleVO;
import com.akbar.mapper.ArticleMapper;
import com.akbar.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    /**
     * 更新文章
     */
    @RequiresAdmin
    @Override
    public void updateArticle(ArticleUpdateDto articleUpdateDto) {
        // 更新文章基本信息
        Article article = new Article();
        BeanUtils.copyProperties(articleUpdateDto, article);

        // 更新标签关联
        // 删除旧的标签关联
        articleTagMapper.deleteByArticleId(article.getId());

        // 插入新的标签关联
        List<Integer> tagIds = articleUpdateDto.getTagIds();
        for(Integer tagId : tagIds) {
            ArticleTag articleTag = new ArticleTag(article.getId(), tagId);
            articleTagMapper.insert(articleTag);
        }

        // 保存更新
        articleMapper.update(article);
    }


    /**
     * 删除文章
     */
    @RequiresAdmin
    @Override
    public void deleteArticle(Integer id) {
        // 删除旧的标签关联
        articleTagMapper.deleteByArticleId(id);
        // 再删除文章
        articleMapper.delete(id);
    }


    /**
     * 分页获取文章列表
     */
    @Override
    public PageResult getArticleList(ArticlePageDto articlePageDto) {
        // 设置分页参数
        PageHelper.startPage(articlePageDto.getPageNum(), articlePageDto.getPageSize());

        // 执行查询
        List<ArticleVO> articleList = articleMapper.selectArticlePage(articlePageDto);

        // 封装分页结果
        PageInfo<ArticleVO> pageInfo = new PageInfo<>(articleList);

        return new PageResult(pageInfo.getTotal(), pageInfo.getList());
    }
}
