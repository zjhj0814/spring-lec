package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue //시퀀스 값 같은 걸 쓰겠죠?
    @Column(name="member_id")
    private Long id;

    private String name;

    @Embedded //임베디드 내장 타입을 포함했다는 어노테이션
    //@Embedded나 @Embeddable 중 하나만 사용해도 됨
    private Address address;

    @OneToMany(mappedBy="member") //하나의 회원이 여러개를 주문할 수 있으니까
    //Order 테이블에 있는 멤버 필드에 의해 매핑된거야(읽기 전용)
    //-> 멤버 테이블에 오더를 변경해도 외래키 값이 변경되지 않음
    private List<Order> orders = new ArrayList<>();
}
