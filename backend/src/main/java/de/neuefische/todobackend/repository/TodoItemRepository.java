package de.neuefische.todobackend.repository;

import de.neuefische.todobackend.model.TodoItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class TodoItemRepository {

    private final List<TodoItem> todoItems = new ArrayList<>();


    public TodoItem add(TodoItem item){
        todoItems.add(item);
        return item;
    }

    public List<TodoItem> listItems(){
        return Collections.unmodifiableList(todoItems);
    }


    public void clear() {
        todoItems.clear();
    }
}
