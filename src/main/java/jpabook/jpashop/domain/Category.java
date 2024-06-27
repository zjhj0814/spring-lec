package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {
    @Id @GeneratedValue
    @Column(name="category_id")
    private Long id;

    private String name;

    //다대다 관계도 연관관계 표시해야 함
    @ManyToMany
    @JoinTable(name="category_item",
      joinColumns=@JoinColumn(name="category_id"), //중간 테이블에 있는 카테고리 아이디
      inverseJoinColumns=@JoinColumn(name="item_id") //이 테이블의 아이템쪽에 들어가는 열
    )//관계형 DB는 다대다 관계를 풀어내는 중간 조인 테이블이 필요
    private List<Item> items = new ArrayList<>();

    //카테고리는 계층구조
    //내 부모와 내 자식을 확인할 수 있어야 함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private Category parent;

    @OneToMany(mappedBy="parent")
    private List<Category> child = new ArrayList<>();

    //연관관계 메소드//
    public void addChildCategory(Category child){
        this.child.add(child);
        child.setParent(this);
    }
}
