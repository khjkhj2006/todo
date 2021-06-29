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
import org.zerock.j07.todo.entity.Store;
import org.zerock.j07.todo.repository.StoreRepository;

import java.beans.Transient;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@ActiveProfiles("dev")
@Log4j2
public class StoreRepositoryTests {

    // Insert
    @Autowired
    private StoreRepository storeRepository;

    @Test
    public void insertTest(){
        IntStream.rangeClosed(1,100).forEach(i -> {
            Store store = Store.builder()
                    .name("상점"+i)
                    .address("종로"+i+"가")
                    .menu("짜장면")
                    .latitude(1.0)
                    .longitude(1.0)
                    .build();
            storeRepository.save(store);
        });
    }

    // Select
    @Test
    public void selectTest() {
        Optional<Store> result = storeRepository.findById(2L);

        result.ifPresent(store -> log.info(store));
    }

    // Delete
    @Test
    public void deleteTest() {
        storeRepository.delete(Store.builder().sNo(1L).build());
    }

    // Paging
    @Test
    public void pagingTest() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("sNo").descending());

        Page<Store> result = storeRepository.findAll(pageable);

        log.info(result);

        result.getContent().forEach(store -> log.info(store));
    }

    // Update
    @Test
    public void updateTest() {
        Optional<Store> result = storeRepository.findById(1L);

        result.ifPresent(store -> {
            store.changeMenu("볶음밥");
            storeRepository.save(store);
        });
    }

}


