package com.haison.ProjectManagerment.service;

import com.haison.ProjectManagerment.dto.TodoDTO;
import com.haison.ProjectManagerment.entity.Todo;
import com.haison.ProjectManagerment.exception.TodoNotFoundException;
import com.haison.ProjectManagerment.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService{

    private TodoRepository todoRepository;
    private ModelMapper modelMapper;

    @Override
    public TodoDTO addTodo(TodoDTO todoDTO) {
        // Convert TodoDTO => Todo
        Todo todo = modelMapper.map(todoDTO,Todo.class);

        TodoDTO newTodoDTO = modelMapper.map(this.todoRepository.save(todo),TodoDTO.class);

        return newTodoDTO;
    }

    @Override
    public TodoDTO getTodoById(Long id) {

        // Convert Todo => TodoDTO
        Todo todo = this.todoRepository.findById(id).orElseThrow(()->
                                    new TodoNotFoundException(String.format("Cant find Todo with ID: %s",id)));

        TodoDTO todoDTO = this.modelMapper.map(todo,TodoDTO.class);
        return  todoDTO;

    }

    @Override
    public List<TodoDTO> getTodoAll() {
        List<Todo> toDoList = this.todoRepository.findAll();

        return toDoList.stream().map(todo -> modelMapper.map(todo,TodoDTO.class))
                                .collect(Collectors.toList());
    }

    @Override
    public TodoDTO updateTodo(TodoDTO todoDTO,long id) {

        Todo todo = this.todoRepository.findById(id)
                                        .orElseThrow(()-> new TodoNotFoundException(String.format("Cant find Todo with ID: %s",todoDTO.getId())));

        todo.setTitle(todoDTO.getTitle());
        todo.setDescription(todoDTO.getDescription());
        todo.setCompleted(todoDTO.isCompleted());

        Todo updateTodo =  this.todoRepository.save(todo);

        return modelMapper.map(updateTodo,TodoDTO.class);
    }


    @Override
    public TodoDTO completedTodo(long id) {

        Todo todo = this.todoRepository.findById(id)
                    .orElseThrow(()-> new TodoNotFoundException(String.format("Cant find Todo with ID: %s",id)));

        todo.setCompleted(Boolean.TRUE);

        Todo completeTodo = this.todoRepository.save(todo);

        return this.modelMapper.map(completeTodo,TodoDTO.class);
    }

    @Override
    public TodoDTO uncompletedTodo(long id) {
        TodoDTO todoDTO = this.getTodoById(id);

        Todo todo = this.modelMapper.map(todoDTO,Todo.class);

        todo.setCompleted(Boolean.FALSE);

        return  this.modelMapper.map(this.todoRepository.save(todo),TodoDTO.class);
    }

    @Override
    public void deleteTodoById(Long id) {
        TodoDTO todoDTO = this.getTodoById(id);

        this.todoRepository.deleteById(todoDTO.getId());
    }
}
