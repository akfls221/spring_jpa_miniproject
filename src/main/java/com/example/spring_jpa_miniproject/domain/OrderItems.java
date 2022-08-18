package com.example.spring_jpa_miniproject.domain;

import com.example.spring_jpa_miniproject.exception.NotEnoughStockException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
public class OrderItems {

    @Id
    @GeneratedValue
    @Column(name = "orderItem_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders order;

    private int orderPrice;

    private int count;

    /**
     * 주문 아이템 생성 메서드
     */
    public static OrderItems createOrderItem(Item item, int orderPrice, int count) throws NotEnoughStockException {
        OrderItems orderItem = OrderItems.builder()
                .item(item)
                .orderPrice(orderPrice)
                .count(count)
                .build();

        item.removeStock(count);
        return orderItem;
    }

    public OrderItems() {

    }

    public void cancel() {
       getItem().addStock(count);
    }

    /**
     * 주문상품 전체 가격조회
     */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
