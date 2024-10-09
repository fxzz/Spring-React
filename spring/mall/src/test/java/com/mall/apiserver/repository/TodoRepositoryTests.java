package com.mall.apiserver.repository;

import com.mall.apiserver.domain.Todo;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class TodoRepositoryTests {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testTodoRepository() {
        Assertions.assertNotNull(todoRepository);

        log.info(todoRepository.getClass().getName());
    }

    @Test
    public void testInsert() {
        Todo todo = Todo.builder()
                .title("Title")
                .content("Content")
                .dueDate(LocalDate.of(2024,12,30))
                .build();

       Todo result = todoRepository.save(todo);

       Assertions.assertNotNull(result);
       log.info(result.toString());
    }

    @Test
    public void testRead() {
        Todo todoTest = Todo.builder()
                .title("Title")
                .content("Content")
                .dueDate(LocalDate.of(2024, 12, 30))
                .build();

        todoRepository.save(todoTest);

        Long tno = todoTest.getTno();

        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();

        Assertions.assertNotNull(todo);
        log.info(todo.toString());

    }

    @Test
    public void testUpdate() {
        Todo todoTest = Todo.builder()
                .title("Title")
                .content("Content")
                .dueDate(LocalDate.of(2024, 12, 30))
                .build();

        todoRepository.save(todoTest);

        Long tno = todoTest.getTno();

        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();
        todo.changeTitle("Title1");
        todo.changeContent("Content1");
        todo.changeDueDate(LocalDate.of(2024, 11, 30));

        Todo updateTodo = todoRepository.save(todo);
        Assertions.assertEquals(updateTodo.getTitle(), "Title1");
        Assertions.assertEquals(updateTodo.getContent(), "Content1");
        Assertions.assertEquals(updateTodo.getDueDate(), LocalDate.of(2024, 11, 30));

        log.info(updateTodo.toString());
    }
}
