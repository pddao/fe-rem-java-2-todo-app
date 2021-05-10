package de.neuefische.todobackend.controller;

import de.neuefische.todobackend.model.TodoItem;
import de.neuefische.todobackend.model.dto.AddTodoItemDto;
import de.neuefische.todobackend.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todo")
public class TodoItemController {

    private final TodoItemService service;

    @Autowired
    public TodoItemController(TodoItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<TodoItem> listItems() {
        return service.listItems();
    }

    @GetMapping("{id}")
    public TodoItem findById(@PathVariable String id) {
        Optional<TodoItem> response = service.findById(id);
        if (response.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item with id " + id + " not found");
        }
        return response.get();
    }

    @PostMapping
    public TodoItem addTodoItem(@RequestBody AddTodoItemDto itemToAdd) {
        return service.addItem(itemToAdd);
    }

    @PutMapping("{id}")
    public TodoItem updateTodoItem(@RequestBody TodoItem item) {
        try {
            return service.updateTodoItem(item);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
