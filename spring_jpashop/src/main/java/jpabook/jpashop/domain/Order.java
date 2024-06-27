package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders") //관례상 Order로 테이블이 생성되니까 Orders로
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY) //다대일 관계니까 연간관계의 주인
    @JoinColumn(name="member_id") //맵핑을 무엇으로 할 것인지, 외래키 이름이 member_id로
    private Member member;

    //양방향 연관관계를 세팅할 때
    @OneToMany(mappedBy="order", cascade = CascadeType.ALL) //OrderItem 테이블의 order_id 아닌가?
    //원래는 오더를 저장->컬렉션에 넣기->오더를 JPA에 persist해서 저장해야 함
    //persist(orderItemA)
    //persist(orderItemB)
    //persist(orderItemC)
    //persist(order) 엔티티 당 각각 호출해야 함
    //cascade = CascadeType.ALL 옵션을 설정한 경우
    //persist(order) 하면 위 코드와 동일한 결과가 나옴
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    //private Date date; - 날짜 관련 어노테이션 필요
    private LocalDateTime orderDate; //분까지 존재하는 시간, 하이버네이트가 자동지원

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 [ORDER, CANCEL]

    //==연관관계 메소드==//
    //컨트롤하는 곳이 가지고 있으면 좋음//
    public void setMember(Member member){
        this.member=member;
        member.getOrders().add(this);
    }

    //public static void main(String[] args){
    //  Member member = new Member();
    //      Order order = new Order();
    //
    //      //연관관계 메소드가 없다면//
    //      //member.getOrders().add(order);
    //      //order.setMember(member);
    //
    //      //연관관계 메소드가 있다면//
    //      order.setMember(member);
    //}

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery=delivery;
        delivery.setOrder(this);
    }

    //==생성 메소드==//
    //Order을 생성하고 set, set, set하는 것보다
    //이런 스타일로 작성해야 앞으로 생성하는 시점에 이것 사용가능
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem : orderItems){
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }
    
    //==비즈니스 로직==//
    /*
    주문 취소
     */
    public void cancel(){
        if(delivery.getStatus() == DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        this.setStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem : orderItems){
            orderItem.cancel();
        }
    }

    //==조회 로직==//
    /*
    전체 주문 가격 조회
     */
    public int getTotalPrice(){
        return orderItems.stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
    }
}
