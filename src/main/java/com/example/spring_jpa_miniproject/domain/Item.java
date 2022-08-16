package com.example.spring_jpa_miniproject.domain;

import com.example.spring_jpa_miniproject.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@NoArgsConstructor
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    /**
     * 상품 재고 관리용 비지니스 로직 추가
     */
    //재고 추가
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    //재고 감소
    public void removeStock(int quantity) throws NotEnoughStockException {
        if (this.stockQuantity < 0) {
            throw new NotEnoughStockException("상품의 재고 수량이 없습니다.", 400);
        }
        int restStock = this.stockQuantity - quantity;
        this.stockQuantity = restStock;
    }

    public void updateItem(Item item) {
        this.price = item.getPrice();
        this.stockQuantity = item.getStockQuantity();
        this.name = item.getName();
    }
}
