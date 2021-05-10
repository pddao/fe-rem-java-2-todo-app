package de.neuefische.todobackend.repository;

import de.neuefische.todobackend.model.TodoItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class TodoItemRepository {

    private final List<TodoItem> todoItems = new ArrayList<>();


    public TodoItem add(TodoItem item) {
        todoItems.add(item);
        return item;
    }

    public List<TodoItem> listItems() {
        return Collections.unmodifiableList(todoItems);
    }


    public void clear() {
        todoItems.clear();
    }

    public TodoItem update(TodoItem item) {
        if (findById(item.getId()).isEmpty()) {
            throw new IllegalArgumentException("Item with with id " + item.getId() + " not found");
        }
        removeItemById(item.getId());
        return add(item);
    }

    private void removeItemById(String id) {
        Optional<TodoItem> itemToRemove = findById(id);
        if (itemToRemove.isPresent()) {
            todoItems.remove(itemToRemove.get());
        }
    }

    private Optional<TodoItem> findById(String id) {
        for (TodoItem todoItem : todoItems) {
            if (todoItem.getId().equals(id)) {
                return Optional.of(todoItem);
            }
        }
        return Optional.empty();
    }
}
