package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    /*
    주문 저장
     */
    public void save(Order order){
        em.persist(order);
    }

    /*
    주문 하나 조회
     */
    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

    /*
    주문 전체 조회(단건 조회, 상태별 조회 등..)
     */
    //public List<Order> findAll(OrderSearch orderSearch){}

}
