package com.akbar.service;

import com.akbar.pojo.entity.ToDo;
import com.akbar.pojo.vo.ToDoVo;

import java.util.List;

public interface ToDoService {
    void addToDo(String title, String description);

    ToDoVo getToDo(Integer id);

    void updateToDo(Integer id, String title);

    void deleteToDo(Integer id);

    List<ToDo> getToDoList();

    void toggleIsCompleted(Integer id);
}
