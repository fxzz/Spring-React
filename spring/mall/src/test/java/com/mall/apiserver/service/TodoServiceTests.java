package com.mall.apiserver.service;

import com.mall.apiserver.domain.Todo;
import com.mall.apiserver.dto.PageRequestDTO;
import com.mall.apiserver.dto.TodoDTO;
import com.mall.apiserver.repository.TodoRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@Log4j2
public class TodoServiceTests {

    @Autowired
    TodoService todoService;

    @Autowired
    TodoRepository todoRepository;

    @Test
    public void testGet() {

        for (int i = 0; i < 30; i++) {
            Todo todoTest = Todo.builder()
                    .title("Title" + i)
                    .writer("Content" + i)
                    .dueDate(LocalDate.of(2024, 12, 30))
                    .build();

            todoRepository.save(todoTest);
        }

        Long tno = 20L;
        log.info(todoService.get(tno));
    }

    @Test
    public void testRegister() {
        TodoDTO todoDTO = TodoDTO.builder()
                .title("Title")
                .content("Content")
                .dueDate(LocalDate.of(2024, 12, 30))
                .build();

        log.info(todoService.register(todoDTO));
    }

    @Test
    public void testGetList() {
         PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

         log.info(todoService.getList(pageRequestDTO));
    }

}
