package org.zerock.j07.todo;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.zerock.j07.todo.entity.Todo;
import org.zerock.j07.todo.repository.TodoRepository;


import java.beans.Transient;
import java.util.Optional;
import java.util.stream.IntStream;

@ActiveProfiles("dev")
@SpringBootTest
@Log4j2
public class TodoRepositoryTests {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void test1(){
        log.info(todoRepository.getClass().getName());
    }
    // Insert
    @Test
    public void testInsert(){

        IntStream.rangeClosed(1,300).forEach(i -> {
            Todo todo = Todo.builder().content("내용..."+i).build();
            todoRepository.save(todo);
        }); //loop

    }

    // Select
    @Test
    public void testSelect() {
        Optional<Todo> result = todoRepository.findById(1L);

        // log.info(result.get());
        result.ifPresent(todo -> log.info(todo));

    }

    // Paging
    @Test
    public void testPaging() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());

        Page<Todo> result = todoRepository.findAll(pageable);

        log.info(result);

        result.getContent().forEach(todo -> log.info(todo));
    }

    // Update
    @Test
    public void testUpdate() {
        Optional<Todo> result = todoRepository.findById(300L);

        result.ifPresent(todo -> {
            todo.changeTitle("300번 내용 수정");
            todoRepository.save(todo);
        });
    }

    // Delete
    @Test
    public void testDelete() {
        todoRepository.delete(Todo.builder().tno(300L).build());

    }

    // Update2
    @Transient
    @Commit
    @Test
    public void testUpdate2() {
        todoRepository.updateContent("300.....", 300L);
    };

}
