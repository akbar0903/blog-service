package com.akbar.pojo.dto.article;

import com.akbar.constant.MessageConstant;
import com.akbar.pojo.entity.Article;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto extends Article {

    @NotBlank(message = MessageConstant.ARTICLE_TITLE_CANT_BE_EMPTY)
    @Size(max = MessageConstant.ARTICLE_TITLE_MAX_LENGTH, message = MessageConstant.ARTICLE_TITLE_TOO_LONG)
    private String title;

    @NotBlank(message = MessageConstant.ARTICLE_SUMMERY_CANT_BE_EMPTY)
    private String summary;

    @NotBlank(message = MessageConstant.ARTICLE_CONTENT_CANT_BE_EMPTY)
    private String content;

    @URL(message = MessageConstant.INVALID_URL)
    private String coverImage;

    @NotNull(message = MessageConstant.ARTICLE_STATE_CANT_BE_NULL)
    private Integer state;

    @NotNull(message = MessageConstant.CATEGORY_ID_CANT_BE_NULL)
    private Integer categoryId;

    @NotNull(message = MessageConstant.TAG_IDS_CANT_BE_NULL)
    @Size(min = MessageConstant.TAG_IDS_MIN_LENGTH, message = MessageConstant.TAG_IDS_TOO_SHORT)
    private List<Integer> tagIds;
}
