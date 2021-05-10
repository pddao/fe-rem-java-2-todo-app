package de.neuefische.todobackend.service;

import de.neuefische.todobackend.model.TodoItem;
import de.neuefische.todobackend.model.dto.AddTodoItemDto;
import de.neuefische.todobackend.repository.TodoItemRepository;
import de.neuefische.todobackend.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoItemService {

    private final TodoItemRepository todoItemRepository;
    private final IdUtils idUtils;

    @Autowired
    public TodoItemService(TodoItemRepository todoItemRepository, IdUtils idUtils) {
        this.todoItemRepository = todoItemRepository;
        this.idUtils = idUtils;
    }

    public List<TodoItem> listItems(){
        return todoItemRepository.listItems();
    }

    public TodoItem addItem(AddTodoItemDto itemToAdd) {
        String id = idUtils.generateUuid();
        TodoItem todoItem = new TodoItem(id, itemToAdd.getDescription(), itemToAdd.getStatus());
        todoItemRepository.add(todoItem);
        return todoItem;
    }

    public TodoItem updateTodoItem(TodoItem item) {
        return todoItemRepository.update(item);
    }
}
