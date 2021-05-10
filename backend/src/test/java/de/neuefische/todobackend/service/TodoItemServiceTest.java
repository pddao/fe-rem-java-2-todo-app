package de.neuefische.todobackend.service;

import de.neuefische.todobackend.model.TodoItem;
import de.neuefische.todobackend.model.dto.AddTodoItemDto;
import de.neuefische.todobackend.repository.TodoItemRepository;
import de.neuefische.todobackend.utils.IdUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

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

}
