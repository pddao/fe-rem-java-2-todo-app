package de.neuefische.todobackend.controller;

import de.neuefische.todobackend.model.TodoItem;
import de.neuefische.todobackend.model.dto.AddTodoItemDto;
import de.neuefische.todobackend.repository.TodoItemRepository;
import de.neuefische.todobackend.utils.IdUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoItemControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TodoItemRepository repository;

    @MockBean
    private IdUtils idUtils;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void clearRepository() {
        repository.clear();
    }

    @Test
    public void getTodoItemsShouldReturnItemsFromDb() {
        //GIVEN
        repository.add(new TodoItem("1", "fancy", "OPEN"));
        repository.add(new TodoItem("2", "super ", "IN_PROGRESS"));

        //WHEN
        ResponseEntity<TodoItem[]> response = restTemplate.getForEntity("http://localhost:" + port + "/api/todo", TodoItem[].class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), arrayContainingInAnyOrder(new TodoItem("1", "fancy", "OPEN"), new TodoItem("2", "super ", "IN_PROGRESS")));
    }

    @Test
    public void addTodoItemShouldAddItemToRepository() {
        //GIVEN
        AddTodoItemDto addTodoItemDto = new AddTodoItemDto("Hallo", "IN_PROGRESS");
        when(idUtils.generateUuid()).thenReturn("42");
        //WHEN
        ResponseEntity<TodoItem> response = restTemplate.postForEntity("http://localhost:" + port + "/api/todo", addTodoItemDto, TodoItem.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(new TodoItem("42", "Hallo", "IN_PROGRESS")));

        List<TodoItem> todoItems = repository.listItems();
        assertThat(todoItems, hasItem(new TodoItem("42", "Hallo", "IN_PROGRESS")));
    }

    @Test
    public void putTodoItemShouldUpdateItem() {
        //GIVEN
        repository.add(new TodoItem("1", "fancy", "OPEN"));
        repository.add(new TodoItem("2", "super ", "IN_PROGRESS"));

        //WHEN
        TodoItem updatedTodo = new TodoItem("2", "super 2", "OPEN");
        restTemplate.put("http://localhost:" + port + "/api/todo/2", updatedTodo, TodoItem.class);

        //THEN
        List<TodoItem> todoItems = repository.listItems();
        assertThat(todoItems, containsInAnyOrder(
                new TodoItem("1", "fancy", "OPEN"),
                new TodoItem("2", "super 2", "OPEN")));
    }

    @Test
    public void whenPutItemWithUnknownIdServerReturn404(){
        //GIVEN
        repository.add(new TodoItem("1", "super ", "IN_PROGRESS"));

        TodoItem updatedTodo = new TodoItem("2", "super 2", "OPEN");

        //WHEN
        HttpEntity<TodoItem> entity = new HttpEntity<>(updatedTodo);
        ResponseEntity<TodoItem> response = restTemplate.exchange("http://localhost:" + port + "/api/todo/2",
                HttpMethod.PUT,
                entity,
                TodoItem.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));


    }

    @Test
    public void getTodoItemByIdShouldReturnTodoItem(){
        //GIVEN
        repository.add(new TodoItem("1", "super ", "IN_PROGRESS"));
        repository.add(new TodoItem("2", "super fancy", "OPEN"));

        //WEN
        ResponseEntity<TodoItem> response = restTemplate.getForEntity("http://localhost:" + port + "/api/todo/2", TodoItem.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(new TodoItem("2", "super fancy", "OPEN")));

    }
    @Test
    public void getTodoItemByIdShouldReturn404WhenItemNotFound(){
        //GIVEN
        repository.add(new TodoItem("1", "super ", "IN_PROGRESS"));

        //WEN
        ResponseEntity<TodoItem> response = restTemplate.getForEntity("http://localhost:" + port + "/api/todo/2", TodoItem.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void deleteByIdShouldDeleteItemFromDb(){
        //GIVEN
        repository.add(new TodoItem("1", "super", "IN_PROGRESS"));
        repository.add(new TodoItem("2", "super fancy", "OPEN"));

        //WHEN
        restTemplate.delete("http://localhost:" + port + "/api/todo/2");

        //THEN
        List<TodoItem> todoItems = repository.listItems();
        assertThat(todoItems, containsInAnyOrder(
                new TodoItem("1", "super", "IN_PROGRESS")));
    }
}
