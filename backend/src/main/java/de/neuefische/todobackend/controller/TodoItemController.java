package de.neuefische.todobackend.controller;

import de.neuefische.todobackend.model.TodoItem;
import de.neuefische.todobackend.model.dto.AddTodoItemDto;
import de.neuefische.todobackend.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoItemController {

    private final TodoItemService service;

    @Autowired
    public TodoItemController(TodoItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<TodoItem> listItems(){
        return service.listItems();
    }

    @PostMapping
    public TodoItem addTodoItem(@RequestBody AddTodoItemDto itemToAdd){
        return service.addItem(itemToAdd);
    }
}
