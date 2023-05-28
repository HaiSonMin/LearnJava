package com.haison.ProjectManagerment.service;

import com.haison.ProjectManagerment.dto.TodoDTO;

import java.util.List;

public interface TodoService  {

    TodoDTO addTodo(TodoDTO todoDTO);
    TodoDTO getTodoById(Long id);
    List<TodoDTO> getTodoAll();
    TodoDTO updateTodo(TodoDTO todoDTO,long id);
    TodoDTO completedTodo(long id);
    TodoDTO uncompletedTodo(long id);
    void deleteTodoById(Long id);
}
