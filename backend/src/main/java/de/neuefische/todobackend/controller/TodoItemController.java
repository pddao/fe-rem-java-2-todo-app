package de.neuefische.todobackend.controller;

import de.neuefische.todobackend.model.TodoItem;
import de.neuefische.todobackend.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
