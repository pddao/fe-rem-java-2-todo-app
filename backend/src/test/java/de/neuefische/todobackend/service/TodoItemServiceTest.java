package de.neuefische.todobackend.service;

import de.neuefische.todobackend.model.TodoItem;
import de.neuefische.todobackend.model.dto.AddTodoItemDto;
import de.neuefische.todobackend.repository.TodoItemRepository;
import de.neuefische.todobackend.utils.IdUtils;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class TodoItemServiceTest {

    private final TodoItemRepository repository = mock(TodoItemRepository.class);
    private final IdUtils idUtils = mock(IdUtils.class);
    private final TodoItemService service = new TodoItemService(repository, idUtils);

    @Test
    public void listTodoItemsShouldReturnItemsFormRepository() {
        //GIVEN
        when(repository.listItems()).thenReturn(List.of(
                new TodoItem("42", "Hallo", "OPEN"),
                new TodoItem("33", "Todo", "IN_PROGRESS")));

        //WHEN
        List<TodoItem> todoItems = service.listItems();

        //THEN
        assertThat(todoItems, containsInAnyOrder(
                new TodoItem("42", "Hallo", "OPEN"),
                new TodoItem("33", "Todo", "IN_PROGRESS")
        ));
    }

    @Test
    public void addTodoItemShouldCreateNewTodoItem() {

        //GIVEN
        AddTodoItemDto itemToAdd = new AddTodoItemDto("Hallo", "OPEN");
        when(idUtils.generateUuid()).thenReturn("42");

        //WHEN
        TodoItem todoItem = service.addItem(itemToAdd);

        //THEN
        assertThat(todoItem, is(new TodoItem("42", "Hallo", "OPEN")));
    }

    @Test
    public void addTodoItemShouldAddTodoItemToDb() {

        //GIVEN
        AddTodoItemDto itemToAdd = new AddTodoItemDto("Hallo", "OPEN");
        when(idUtils.generateUuid()).thenReturn("42");

        //WHEN
        service.addItem(itemToAdd);

        //THEN
        verify(repository).add(new TodoItem("42", "Hallo", "OPEN"));
    }


    @Test
    public void updateTodoItemShouldCallUpdateItemOnRepository(){
        //GIVEN
        TodoItem updatedItem = new TodoItem("42", "Hallo", "OPEN");
        when(repository.update(updatedItem)).thenReturn(updatedItem);

        //WHEN
        TodoItem todoItem = service.updateTodoItem(updatedItem);

        //THEN
        verify(repository).update(new TodoItem("42", "Hallo", "OPEN"));
        assertThat(todoItem, is(new TodoItem("42", "Hallo", "OPEN")));
    }

    @Test
    public void findByIdShouldCallfindByIdOnRepository(){
        //GIVEN
        TodoItem item = new TodoItem("42", "Hallo", "OPEN");
        when(repository.findById("42")).thenReturn(Optional.of(item));

        //WHEN
        Optional<TodoItem> todoItemOptional = service.findById("42");

        //THEN
        assertThat(todoItemOptional.get(), is(new TodoItem("42", "Hallo", "OPEN")));
    }

    @Test
    public void deleteByIdShouldCallDeleteByIdOnRepository(){
        //WHEN
        service.deleteById("42");

        //THEN
        verify(repository).deleteById("42");
    }
}
