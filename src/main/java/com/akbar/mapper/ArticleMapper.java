package com.akbar.mapper;

import com.akbar.annotation.AutoFill;
import com.akbar.enumeration.OperationType;
import com.akbar.pojo.dto.article.ArticlePageDto;
import com.akbar.pojo.entity.Article;
import com.akbar.pojo.vo.ArticleVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @AutoFill(OperationType.INSERT)
    void insert(Article article);

    // 回显文章信息
    ArticleVO selectArticleVoById(Integer id);

    @AutoFill(OperationType.UPDATE)
    void update(Article article);

    @Delete(("delete from article where id = #{id}"))
    void delete(Integer id);

    @Select("select count(id) from article where id = #{tagId}")
    Integer countByTagId(Integer tagId);

    @Select("select count(id) from article where category_id = #{categoryId}")
    Integer countByCategoryId(Integer categoryId);

    List<ArticleVO> selectArticlePage(ArticlePageDto articlePageDto);
}
