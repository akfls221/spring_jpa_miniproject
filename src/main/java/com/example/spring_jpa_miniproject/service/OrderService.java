package com.example.spring_jpa_miniproject.service;

import com.example.spring_jpa_miniproject.domain.*;
import com.example.spring_jpa_miniproject.exception.NotEnoughStockException;
import com.example.spring_jpa_miniproject.repository.MemberRepository;
import com.example.spring_jpa_miniproject.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemService itemService;

    /**
     * 주문 로직
     */
    public Long order(Long memberId, Long itemId, int count) throws NotEnoughStockException {

        //멤버, 아이템 엔티티 조회
        Member member = memberRepository.findOne(memberId).orElseThrow(() -> new IllegalArgumentException("존재하는 회원이 없습니다."));
        Item item = itemService.findItem(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery(member.getAddress());
        //주문상품 생성
        OrderItems orderItem = OrderItems.createOrderItem(item, item.getPrice(), count);
        //주문 생성
        Orders order = Orders.createOrder(member, delivery, orderItem);
        //주문 저장
        orderRepository.save(order);

        return order.getId();

    }

    /**
     * 주문 취소
     */
    public void cancelOrder(Long orderId) {
        Orders order = orderRepository.findOne(orderId);

        order.cancel();
    }

    /**
     * 주문 검색
     */

}
