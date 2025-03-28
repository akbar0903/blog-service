package com.akbar.mapper;

import com.akbar.annotation.AutoFill;
import com.akbar.enumeration.OperationType;
import com.akbar.pojo.dto.article.ArticlePageDto;
import com.akbar.pojo.entity.Article;
import com.akbar.pojo.vo.ArticleVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @AutoFill(OperationType.INSERT)
    void insert(Article article);

    // 回显文章信息
    ArticleVo selectArticleVoById(Integer id);

    @AutoFill(OperationType.UPDATE)
    void update(Article article);

    @Delete(("delete from article where id = #{id}"))
    void delete(Integer id);

    @Select("select count(id) from article where category_id = #{categoryId}")
    Integer countByCategoryId(Integer categoryId);

    // 根据分页查询条件，获取文章id列表
    List<Integer> selectArticleIds(String title, Integer categoryId, Integer state);

    // 根据id集合查询完整数据
    List<ArticleVo> selectArticleByIds(List<Integer> articleIds);

    @Select("select count(id) from article where cover_image = #{url}")
    Integer countByCoverImage(String url);
}
