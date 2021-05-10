package de.neuefische.todobackend.service;

import de.neuefische.todobackend.model.TodoItem;
import de.neuefische.todobackend.repository.TodoItemRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TodoItemServiceTest {

    private final TodoItemRepository repository = mock(TodoItemRepository.class);
    private final TodoItemService service = new TodoItemService(repository);

    @Test
    public void listTodoItemsShouldReturnItemsFormRepository(){
       //GIVEN
        when(repository.listItems()).thenReturn(List.of(
                new TodoItem("42", "Hallo", "OPEN"),
                new TodoItem("33", "Todo", "IN_PROGRESS")));

        //WHEN
        List<TodoItem> todoItems = service.listItems();

        //THEN
        assertThat(todoItems,containsInAnyOrder(
                new TodoItem("42", "Hallo", "OPEN"),
                new TodoItem("33", "Todo", "IN_PROGRESS")
        ));
    }

}
