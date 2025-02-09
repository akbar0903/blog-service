package com.akbar.controller;

import com.akbar.annotation.LogAnno;
import com.akbar.domain.entity.Tag;
import com.akbar.service.TagService;
import com.akbar.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    private final TagService tagService;
    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    // 添加标签
    @PostMapping
    @LogAnno(operationType = "添加标签")
    public Result<Void> addTag(@RequestBody @Validated Tag tag) {
        tagService.addTag(tag);
        return Result.success("添加标签成功！");
    }


    // 修改标签
    @PutMapping("/{id}")
    @LogAnno(operationType = "修改标签")
    public Result<Void> updateTag(@PathVariable Integer id,@RequestBody @Validated Tag tag) {
        tag.setId(id);
        tagService.updateTag(tag);
        return Result.success("修改标签成功！");
    }


    // 回显标签
    @GetMapping("/{id}")
    public Result<Tag> getTag(@PathVariable Integer id) {
        Tag tag = tagService.getTag(id);
        return Result.success(tag);
    }


    // 删除标签
    @DeleteMapping("/{id}")
    @LogAnno(operationType = "删除标签")
    public Result<Void> deleteTag(@PathVariable Integer id) {
        tagService.deleteTag(id);
        return Result.success("删除标签成功！");
    }


    // 获取所有标签列表
    @GetMapping
    public Result<List<Tag>> getTagList() {
        List<Tag> tagList = tagService.getTagList();
        return Result.success(tagList);
    }


    // 查询标签
    @GetMapping("/search")
    public Result<List<Tag>> searchTag(@RequestParam("name") String name) {
        List<Tag> tagList = tagService.searchTag(name);
        return Result.success(tagList);
    }
}
