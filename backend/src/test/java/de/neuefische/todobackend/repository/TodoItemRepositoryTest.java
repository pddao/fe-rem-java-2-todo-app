package de.neuefische.todobackend.repository;

import de.neuefische.todobackend.model.TodoItem;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;


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

    @Test
    public void updateShouldUpdateItem(){
        //GIVEN
        TodoItem todoItem = new TodoItem("1", "Hallo", "OPEN");
        TodoItem secondTodoItem = new TodoItem("2", "Hallo 2", "IN_PROGRESS");
        repository.add(todoItem);
        repository.add(secondTodoItem);


        TodoItem todoItemToUpdate = new TodoItem("1", "Hallo updated", "IN_PROGRESS");

        //WHEN
        repository.update(todoItemToUpdate);

        //THEN
        List<TodoItem> todoItems = repository.listItems();
        assertThat(todoItems, containsInAnyOrder(
                new TodoItem("1", "Hallo updated", "IN_PROGRESS"),
                new TodoItem("2", "Hallo 2", "IN_PROGRESS")
        ));
    }

    @Test
    public void updateShouldThrowWhenItemWIthIdNotInRepository(){
        //GIVEN
        TodoItem secondTodoItem = new TodoItem("2", "Hallo 2", "IN_PROGRESS");
        repository.add(secondTodoItem);


        TodoItem todoItemToUpdate = new TodoItem("1", "Hallo updated", "IN_PROGRESS");

        //WHEN
        try {
            repository.update(todoItemToUpdate);
            fail();
        }catch (IllegalArgumentException e){
            assertThat(e.getMessage(), is("Item with with id 1 not found"));
            List<TodoItem> todoItems = repository.listItems();
            assertThat(todoItems, containsInAnyOrder(
                    new TodoItem("2", "Hallo 2", "IN_PROGRESS")
            ));
        }
    }

    @Test
    public void findByIdShouldReturnOptionalWithItemWhenIdExists(){
        //GIVEN
        TodoItem todoItem = new TodoItem("1", "Hallo", "OPEN");
        TodoItem secondTodoItem = new TodoItem("2", "Hallo 2", "IN_PROGRESS");
        repository.add(todoItem);
        repository.add(secondTodoItem);

        //WHEN
        Optional<TodoItem> response = repository.findById("2");

        //THEN
        assertThat(response.get(), is(new TodoItem("2", "Hallo 2", "IN_PROGRESS")));

    }

    @Test
    public void findByIdShouldReturnOptionalEmptyWhenIdNotExists(){
        //GIVEN
        TodoItem todoItem = new TodoItem("1", "Hallo", "OPEN");
        TodoItem secondTodoItem = new TodoItem("2", "Hallo 2", "IN_PROGRESS");
        repository.add(todoItem);
        repository.add(secondTodoItem);

        //WHEN
        Optional<TodoItem> response = repository.findById("4");

        //THEN
        assertThat(response.isEmpty(), is(true));

    }

    @Test
    public void deleteByIdShouldRemoveItemFromDb(){
        //GIVEN
        TodoItem todoItem = new TodoItem("1", "Hallo", "OPEN");
        TodoItem secondTodoItem = new TodoItem("2", "Hallo 2", "IN_PROGRESS");
        repository.add(todoItem);
        repository.add(secondTodoItem);

        //WHEN
        repository.deleteById("2");

        //THEN
        List<TodoItem> todoItems = repository.listItems();
        assertThat(todoItems, containsInAnyOrder(
                new TodoItem("1", "Hallo", "OPEN")
        ));
    }

}
