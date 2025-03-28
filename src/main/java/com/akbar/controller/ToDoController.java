package com.akbar.controller;

import com.akbar.constant.MessageConstant;
import com.akbar.pojo.entity.ToDo;
import com.akbar.pojo.result.Result;
import com.akbar.pojo.vo.ToDoVo;
import com.akbar.service.ToDoService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class ToDoController {
    @Autowired
    private ToDoService toDoService;


    /**
     * 添加任务
     *
     * @param title 标题
     * @param type  描述
     */
    @PostMapping
    public Result<Void> addToDo(
            @NotBlank(message = MessageConstant.TO_DO_TITLE_CANT_BE_EMPTY)
            @RequestParam
            String title,
            @NotBlank(message = MessageConstant.TO_DO_TYPE_CANT_BE_EMPTY)
            @RequestParam
            String type) {

        toDoService.addToDo(title, type);
        return Result.success();
    }


    /**
     * 回显任务
     *
     * @param id 任务id
     */
    @GetMapping("/info")
    public Result<ToDoVo> getToDo(@RequestParam Integer id) {
        ToDoVo toDo = toDoService.getToDo(id);
        return Result.success(toDo);
    }


    /**
     * 更新任务
     *
     * @param id          任务id
     * @param title       任务标题
     */
    @PutMapping
    public Result<Void> updateToDo(
            @RequestParam
            Integer id,
            @RequestParam
            String title) {

        toDoService.updateToDo(id, title);
        return Result.success();
    }


    /**
     * 删除任务
     *
     * @param id 任务id
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteToDo(@PathVariable Integer id) {
        toDoService.deleteToDo(id);
        return Result.success();
    }


    /**
     * 获取任务列表
     *
     * @return 任务列表
     */
    @GetMapping("/list")
    public Result<List<ToDo>> getToDoList() {
        List<ToDo> toDoList = toDoService.getToDoList();
        return Result.success(toDoList);
    }

    /**
     * 设置成已完成
     *
     * @param id 任务id
     */
    @PatchMapping("/toggle")
    public Result<Void> toggleIsCompleted(@RequestParam Integer id) {
        toDoService.toggleIsCompleted(id);
        return Result.success();
    }
}
