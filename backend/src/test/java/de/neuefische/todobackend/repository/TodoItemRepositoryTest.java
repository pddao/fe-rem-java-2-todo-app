package de.neuefische.todobackend.repository;

import de.neuefische.todobackend.model.TodoItem;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;


class TodoItemRepositoryTest {

    private final TodoItemRepository repository = new TodoItemRepository();

    @Test
    public void addShouldAddTodoItemToRepository() {
        //GIVEN
        TodoItem todoItem = new TodoItem("1", "Hallo", "OPEN");
        TodoItem secondTodoItem = new TodoItem("2", "Hallo 2", "IN_PROGRESS");

        //WHEN
        TodoItem addedItem = repository.add(todoItem);
        repository.add(secondTodoItem);
        List<TodoItem> todoItems = repository.listItems();

        //THEN
        assertThat(addedItem, is(new TodoItem("1", "Hallo", "OPEN")));
        assertThat(todoItems, containsInAnyOrder(
                new TodoItem("1", "Hallo", "OPEN"),
                new TodoItem("2", "Hallo 2", "IN_PROGRESS")
        ));
    }

}
