package com.example.spring_jpa_miniproject.repository;

import com.example.spring_jpa_miniproject.domain.Orders;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class OrderRepository {

    @PersistenceContext
    EntityManager em;

    /**
     *주문 저장
     */
    public void save(Orders order) {
        em.persist(order);
    }

    /**
     * 주문 검색
     * param : Id
     */
    public Orders findOne(Long id) {
        return em.find(Orders.class, id);
    }


}
