package com.example.spring_jpa_miniproject.repository;

import com.example.spring_jpa_miniproject.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Item item) {
        em.persist(item);
    }

    public Optional<Item> findOne(Long id) {
        return Optional.ofNullable(em.find(Item.class, id));
    }

    public Optional<List<Item>> findAll() {
        return Optional.ofNullable(
                em.createQuery("select i from Item i", Item.class)
                        .getResultList()
        );
    }
}
