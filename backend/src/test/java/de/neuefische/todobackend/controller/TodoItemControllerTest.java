package de.neuefische.todobackend.controller;

import de.neuefische.todobackend.model.TodoItem;
import de.neuefische.todobackend.repository.TodoItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.is;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoItemControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TodoItemRepository repository;

    @Autowired
    private TestRestTemplate restTemplate;

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

}
