package com.example.spring_jpa_miniproject.service;

import com.example.spring_jpa_miniproject.domain.Book;
import com.example.spring_jpa_miniproject.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Test
    void 아이템등록() {
        //given
        Book book = new Book();
        book.setAuthor("테스트");
        book.setIsbn("123123");
        book.setPrice(3000);
        book.setStockQuantity(300);

        //when
        Long id = itemService.saveItem(book);

        //then
        Assertions.assertThat(book.getName()).isEqualTo(itemService.findItem(id).getName());
    }


}