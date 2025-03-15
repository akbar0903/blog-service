# 个人博客项目后端

## 1.通过本项目我学到了
### [1].定义复合校验注解

自定义校验password的注解`ValidPassword`:

```java
package com.akbar.annotation;

import com.akbar.constant.MessageConstant;
import com.akbar.constant.RegexConstant;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 密码校验注解
 */
@Target(ElementType.FIELD)            // 使用与类中的成员变量
@Retention(RetentionPolicy.RUNTIME)  // 会在哪个阶段保留，RUNTIME:运行时阶段
@Constraint(validatedBy = {})        // 谁给这个注解提供校验规则，如果不提供，以NotBlank，Pattern作为校验规则
@NotBlank(message = MessageConstant.PASSWORD_CANT_BE_EMPTY)
@Pattern(regexp = RegexConstant.PASSWORD_PATTERN, message = MessageConstant.PASSWORD_TOO_SHORT)
public @interface ValidPassword {
    // 默认message信息，可以被（NotBlank，Pattern）的message覆盖
    String message() default "Invalid password";

    // 指定分组
    Class<?>[] groups() default {};

    // 获取注解的附加信息（一般很少用，但是必须得写）
    Class<? extends Payload>[] payload() default {};
}
```

### [2].分组校验

```java
package com.akbar.pojo.entity;

import com.akbar.constant.MessageConstant;
import com.akbar.constant.TimeFormatConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @NotNull(groups = Update.class, message = MessageConstant.ARTICLE_ID_CANT_BE_NULL)
    private Integer id;
    @NotBlank(message = MessageConstant.ARTICLE_TITLE_CANT_BE_EMPTY)
    private String title;
    @NotBlank(message = MessageConstant.ARTICLE_SUMMERY_CANT_BE_EMPTY)
    private String summary;
    @NotBlank(message = MessageConstant.ARTICLE_CONTENT_CANT_BE_EMPTY)
    private String content;
    @URL(message = MessageConstant.INVALID_URL)
    private String coverImage;
    private Integer state;           // '1'：发布,'0'：草稿
    private Integer adminId;
    @NotNull(message = MessageConstant.CATEGORY_ID_CANT_BE_NULL)
    private Integer categoryId;
    @JsonFormat(pattern = TimeFormatConstant.DATE_TIME_PATTERN)
    private LocalDateTime createdTime;
    @JsonFormat(pattern = TimeFormatConstant.DATE_TIME_PATTERN)
    private LocalDateTime updatedTime;

    /**
     * 分组校验
     * 如果某个校验没有指定分组，那么这个校验就属于default分组
     * 分组之间可以继承，比如Add继承default默认分组，比如下面的
     * NotBlank( groups = {Add.class, Update.class})
     * 因为Add和Update都继承了default分组，所以不用groups = {Add.class, Update.class}这样写
     * 直接不写分组就好了
     */
    // 添加组
    public interface Add extends Default {

    }

    // 更新组
    public interface Update extends Default {

    }
}
```

### [3].多表查询，以回显文章信息为例（用ArticleVo封装）

**ArticleVo：**

```java
package com.akbar.pojo.vo;

import com.akbar.constant.TimeFormatConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleVO {
    private Integer id;
    private String title;
    private String summary;
    private String content;
    private String coverImage;
    private Integer state;
    private String categoryName;
    private List<String> tagNames;
    @JsonFormat(pattern = TimeFormatConstant.DATE_TIME_PATTERN)
    private LocalDateTime createdTime;
    @JsonFormat(pattern = TimeFormatConstant.DATE_TIME_PATTERN)
    private LocalDateTime updatedTime;
}
```

**第一种方案：分步骤查询**
> 性能不好，不推荐

```java
/**
 * 回显文章信息
 */
@Override
public ArticleVO getArticle(Integer id) {
    Article article = articleMapper.selectById(id);
    String categoryName = categoryMapper.selectNameById(article.getCategoryId());
    List<Integer> tagIds = articleTagMapper.selectTagIdsByArticleId(article.getId());

    //查询标签名称列表
    List<String> tagNames = new ArrayList<>();
    for (Integer tagId : tagIds) {
        String tagName = tagMapper.selectNameById(tagId);
        if (tagName != null) {
            tagNames.add(tagName);
        }
    }

    // 组装articleVo
    return ArticleVO.builder()
            .id(article.getId())
            .title(article.getTitle())
            .summary(article.getSummary())
            .content(article.getContent())
            .coverImage(article.getCoverImage())
            .state(article.getState())
            .categoryName(categoryName)
            .tagNames(tagNames)
            .createdTime(article.getCreatedTime())
            .updatedTime(article.getUpdatedTime())
            .build();
}
```

**第二种方案：使用联表查询**
> 推荐

```xml
<!--回显文章信息-->
<resultMap id="articleVoMap" type="com.akbar.pojo.vo.ArticleVO">
    <!-- 映射基本属性 -->
    <id property="id" column="id"/>
    <result property="title" column="title"/>
    <result property="summary" column="summary"/>
    <result property="content" column="content"/>
    <result property="coverImage" column="cover_image"/>
    <result property="state" column="state"/>
    <result property="categoryName" column="category_name"/>
    <result property="createdTime" column="created_time"/>
    <result property="updatedTime" column="updated_time"/>
    <!-- 映射 tagNames 集合，注意，所有result元素要在collection之前出现 -->
    <collection property="tagNames" ofType="java.lang.String">
        <result column="tag_name"/>
    </collection>
</resultMap>
<select id="selectArticleVoById" resultType="com.akbar.pojo.vo.ArticleVO" resultMap="articleVoMap">
    select 
        a.id,
        a.title,
        a.summary,
        a.content,
        a.cover_image,
        a.state,
        c.name AS category_name,
        t.name AS tag_name,
        a.created_time,
        a.updated_time
    from 
        article a
    left join
        category c on a.category_id = c.id
    left join
        article_tag at on a.id = at.article_id
    left join
        tag t on at.tag_id = t.id
    where a.id = #{id}
</select>
```
### [4].文章分页查询
```xml
    <!--回显文章信息-->
    <resultMap id="articleVoMap" type="com.akbar.pojo.vo.ArticleVO">
        <!-- 映射基本属性 -->
        <id property="id" column="id"/>
        <result property="title" column="title"/>
        <result property="summary" column="summary"/>
        <result property="content" column="content"/>
        <result property="coverImage" column="cover_image"/>
        <result property="state" column="state"/>
        <result property="categoryName" column="category_name"/>
        <result property="createdTime" column="created_time"/>
        <result property="updatedTime" column="updated_time"/>
        <!-- 映射 tagNames 集合，注意，所有result元素要在collection之前出现 -->
        <collection property="tagNames" ofType="java.lang.String">
            <result column="tag_name"/>
        </collection>
    </resultMap>

        <!--文章分页查询-->
<select id="selectArticlePage" resultType="com.akbar.pojo.vo.ArticleVO" resultMap="articleVoMap">
    select
        a.id,
        a.title,
        a.summary,
        a.content,
        a.cover_image,
        a.state,
        c.name AS category_name,
        t.name AS tag_name,
        a.created_time,
        a.updated_time
    from
        article a
    left join
        category c on a.category_id = c.id
    left join
        article_tag at on a.id = at.article_id
    left join
        tag t on at.tag_id = t.id
    where
        1 = 1
        <if test="categoryId != null">
            and a.category_id = #{categoryId}
        </if>
        <if test="state != null">
            and a.state = #{state}
        </if>
        <if test="title != null">
            and a.title like concat('%', #{title}, '%')
        </if>
    order by
        a.created_time desc
</select>
```
**为什么where条件中使用`1 = 1`?**<br/>
```xml
    where
        1 = 1
        <if test="categoryId != null">
            and a.category_id = #{categoryId}
        </if>
        <if test="state != null">
            and a.state = #{state}
        </if>
        <if test="title != null">
            and a.title like concat('%', #{title}, '%')
        </if>
```
每个条件前面都使用了 AND 连接。如果没有 1 = 1 作为起始条件，动态拼接可能会导致 SQL 语法错误。

- 如果不写，1=1，然后`categoryId`，`state`，`title`都为空：
> 最后的sql语句是：
```sql
WHERE
```
这导致sql语法错误，如果加上1=1就不会这样：

```sql
WHERE 1=1
```
这是合法的sql语法