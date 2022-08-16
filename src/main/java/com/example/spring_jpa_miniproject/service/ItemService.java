package com.example.spring_jpa_miniproject.service;

import com.example.spring_jpa_miniproject.domain.Item;
import com.example.spring_jpa_miniproject.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Long saveItem(Item item) {
        itemRepository.save(item);
        return item.getId();
    }

    public Long update(Long id, Item request) {
        Item item = itemRepository.findOne(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하는 아이템이 없습니다."));
        item.updateItem(request);

        return item.getId();
    }

    public Item findItem(Long id) {
        return itemRepository.findOne(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하는 아이템이 없습니다."));
    }

    public List<Item> findItems() {
        return new ArrayList<>(itemRepository.findAll()
                .orElseThrow(() -> new IllegalArgumentException("아이템이 비어있습니다.")));
    }

}
