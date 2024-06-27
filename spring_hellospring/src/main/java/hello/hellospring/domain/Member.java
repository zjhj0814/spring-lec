package hello.hellospring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity //jpa가 관리하는 entity가 됨

public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //@Id==pk 쿼리에 ID 안 넣음 -> 아이덴티티 전략
    private Long id;
    //만약 DB에 column명이 USER_NAME이면 @Column(name="username")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
