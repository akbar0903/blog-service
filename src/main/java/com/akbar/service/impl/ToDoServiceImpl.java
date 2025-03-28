package com.akbar.service.impl;

import com.akbar.annotation.RequiresAdmin;
import com.akbar.constant.MessageConstant;
import com.akbar.mapper.ToDoMapper;
import com.akbar.pojo.entity.ToDo;
import com.akbar.pojo.vo.ToDoVo;
import com.akbar.service.ToDoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ToDoServiceImpl implements ToDoService {

    @Autowired
    private ToDoMapper toDoMapper;


    /**
     * 添加任务
     */
    @RequiresAdmin
    @Override
    public void addToDo(String title, String type) {
        ToDo toDo = new ToDo();
        toDo.setTitle(title);
        toDo.setType(type);

        toDoMapper.insert(toDo);
    }


    /**
     * 回显任务
     */
    @Override
    public ToDoVo getToDo(Integer id) {
        ToDo toDo = toDoMapper.selectById(id);
        ToDoVo toDoVo = new ToDoVo();
        BeanUtils.copyProperties(toDo, toDoVo);

        return toDoVo;
    }


    /**
     * 更新任务
     */
    @RequiresAdmin
    @Override
    public void updateToDo(Integer id, String title) {
        ToDo toDo = new ToDo();
        toDo.setId(id);
        toDo.setTitle(title);

        toDoMapper.update(toDo);
    }


    /**
     * 删除任务
     */
    @RequiresAdmin
    @Override
    public void deleteToDo(Integer id) {
        toDoMapper.delete(id);
    }


    /**
     * 获取任务列表
     */
    @Override
    public List<ToDo> getToDoList() {
        return toDoMapper.list();
    }


    /**
     * 切换任务状态
     *
     */
    @RequiresAdmin
    @Override
    public void toggleIsCompleted(Integer id) {
        ToDo toDo = toDoMapper.selectById(id);

        // 如果是已完成就改成未完成，反之亦然
        if (toDo.getIsCompleted() == 1) {
            toDo.setIsCompleted(MessageConstant.TO_DO_UNCOMPLETED_STATUS);
        } else {
            toDo.setIsCompleted(MessageConstant.TO_DO_COMPLETED_STATUS);
        }

        toDoMapper.update(toDo);
    }
}
