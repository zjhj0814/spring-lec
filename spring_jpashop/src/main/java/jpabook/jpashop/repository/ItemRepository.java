package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    /*
    상품 저장
     */
    public void save(Item item){
        if(item.getId() == null){
            //아이템은 처음에는 아이디가 없으므로(완전히 새로 생성한 객체_
            //데이터를 저장하기 위해 JPA가 제공하는 persist 사용하기
            em.persist(item);
        }else{
            //update 느낌
            em.merge(item);
        }
    }
    
    /*
    상품 하나 조회
     */
    public Item findOne(Long id){
        return em.find(Item.class, id);
    }
    
    /*
    전체 상품 조회
     */
    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
