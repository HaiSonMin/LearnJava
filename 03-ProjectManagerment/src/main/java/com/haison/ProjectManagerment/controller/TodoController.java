package com.haison.ProjectManagerment.controller;

import com.haison.ProjectManagerment.dto.TodoDTO;
import com.haison.ProjectManagerment.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class TodoController {

    private final TodoService todoService;
    @Autowired
    public TodoController(@Qualifier("todoServiceImpl") TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("todos")
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')") // PreAuthorize lever method
    public ResponseEntity<TodoDTO> createTodo(@RequestBody TodoDTO todoDTO) {
        TodoDTO newTodo = this.todoService.addTodo(todoDTO);
//        return new ResponseEntity<>(newTodo,HttpStatus.CREATED);
        return ResponseEntity.ok(newTodo);

    }

    @GetMapping("todos")
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
    public ResponseEntity<List<TodoDTO>> getAllTodo() {
        List<TodoDTO> todoDTOList = this.todoService.getTodoAll();
//        return new ResponseEntity<>(todoDTOList,HttpStatus.OK);
        return ResponseEntity.ok(todoDTOList);
    }

    @GetMapping("todos/{id}")
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
    public ResponseEntity<TodoDTO> getTodoById(@PathVariable(name = "id") long todoId) {
        TodoDTO todoDTO = this.todoService.getTodoById(todoId);
//        return new ResponseEntity<>(todoDTO, HttpStatus.OK);
        return ResponseEntity.ok(todoDTO);
    }

    @PutMapping("todos/{id}")
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<TodoDTO> updateTodo(@RequestBody TodoDTO todoDTO,@PathVariable(name = "id") long id) {
        TodoDTO newTodoUpdate = this.todoService.updateTodo(todoDTO,id);
        return ResponseEntity.ok(newTodoUpdate);
    }

    @PatchMapping("todos/{id}/completed")
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
    public ResponseEntity<TodoDTO> updateCompleteTodo(@PathVariable(name = "id") long todoId) {
        TodoDTO newTodoUpdate = this.todoService.completedTodo(todoId);
        return ResponseEntity.ok(newTodoUpdate);
    }

    @PatchMapping("todos/{id}/uncompleted")
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
    public ResponseEntity<TodoDTO> updateUncompleted(@PathVariable(name = "id") long todoId) {
        return ResponseEntity.ok(this.todoService.uncompletedTodo(todoId));
    }

    @DeleteMapping("todos/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> update(@PathVariable(name = "id") long todoId) {
        this.todoService.deleteTodoById(todoId);
        return ResponseEntity.ok("Delete Successfully");
    }

}
