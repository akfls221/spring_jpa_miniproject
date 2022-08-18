package com.example.spring_jpa_miniproject.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Table(name = "ORDERS")
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItems> orderItemsList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime orderDate;

    /**
     * 주문 생성 메서드
     */
    public static Orders createOrder(Member member, Delivery delivery, OrderItems... orderItems) {
        Orders order = Orders.builder()
                .member(member)
                .delivery(delivery)
                .orderStatus(OrderStatus.ORDER)
                .orderDate(LocalDateTime.now())
                .build();
        for (OrderItems orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        return order;
    }

    /**
     * 주문 취소 메서드
     */
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalArgumentException("이미 배송완료된 상품은 취소가 불가능 합니다.");
        }

        this.orderStatus = OrderStatus.CANCEL;
        for (OrderItems orderItem : orderItemsList) {
            orderItem.cancel();
        }
    }

    private void addOrderItem(OrderItems orderItem) {
        this.getOrderItemsList().add(orderItem);

    }

    //연관관계 편의 메서드
    public void setMember(Member member) {
        this.member = member;
        if (!member.getOrderList().contains(this)) {
            member.addOrder(this);
        }
    }

    //전체 주문 가격조회
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItems orderItem : orderItemsList) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

    @Builder
    public Orders(Member member, Delivery delivery, OrderStatus orderStatus, LocalDateTime orderDate) {
        this.member = member;
        this.delivery = delivery;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }
}
