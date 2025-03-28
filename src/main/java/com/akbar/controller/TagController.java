package com.akbar.controller;

import com.akbar.constant.MessageConstant;
import com.akbar.pojo.entity.Tag;
import com.akbar.pojo.result.Result;
import com.akbar.service.TagService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
@Validated
public class TagController {

    @Autowired
    private TagService tagService;


    /**
     * 添加标签
     */
    @PostMapping
    public Result<Void> addTag(
            @RequestParam
            @NotBlank(message = MessageConstant.TAG_NAME_CANT_BE_EMPTY)
            @Size(max = MessageConstant.TAG_NAME_MAX_LENGTH, message = MessageConstant.TAG_NAME_TOO_LONG)
            String name) {
        tagService.addTag(name);
        return Result.success();
    }


    /**
     * 更新标签
     */
    @PutMapping("/{id}")
    public Result<Void> updateTag(
            @PathVariable Integer id,

            @RequestParam @NotBlank(message = MessageConstant.TAG_NAME_CANT_BE_EMPTY)
            @Size(max = MessageConstant.TAG_NAME_MAX_LENGTH, message = MessageConstant.TAG_NAME_TOO_LONG)
            String name) {

        tagService.updateTag(id, name);
        return Result.success();
    }


    /**
     * 回显标签
     */
    @GetMapping("/info")
    public Result<Tag> getTag(@RequestParam Integer id) {
        Tag tag = tagService.getTag(id);
        return Result.success(tag);
    }


    /**
     * 删除标签
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteTag(@PathVariable Integer id) {
        tagService.deleteTag(id);
        return Result.success();
    }


    /**
     * 获取所有标签
     */
    @GetMapping("/list")
    public Result<List<Tag>> getTagList() {
        List<Tag> tagList = tagService.getTagList();
        return Result.success(tagList);
    }
}
