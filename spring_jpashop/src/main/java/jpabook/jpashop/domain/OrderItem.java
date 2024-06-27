package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //protected OrderItem(){}
public class OrderItem {
    @Id @GeneratedValue
    @Column(name="order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;
    
    private int orderPrice; //주문 가격
    private int count; //주문 수량

    //==생성 메소드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removestock(count);
        return orderItem;
    }

    //==비즈니스 로직==//
    /*
    오더아이템 취소
     */
    public void cancel() {
        //아이템 재고 늘리기(원복)
        getItem().addStock(count);
    }

    //==조회 로직==//
    /*
    주문상품 가격 전체 조회
     */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
