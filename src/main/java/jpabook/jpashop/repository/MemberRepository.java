package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    //@PersistenceContext //JPA의 엔티티 매니저를 주입 받기
    //순수한 JPA는 엔티티 매니저 팩토리에서 엔티티 매니저를 꺼내고..
    @Autowired //스프링 데이터 JPA가 없으면 안 됨 @PersistenceContext 필요
    private final EntityManager em;

//    public MemberRepository(EntityManager em) {
//        this.em = em;
//    } 생성자 주입 가능

    //@PersistenceUnit
    //private EntityManagerFactory entityManagerFactory;

    public void save(Member member){
        em.persist(member);
        //persist하면 영속성 컨텍스트에 member를 넣고
        //트랜잭션이 commit하면 그때 db에 반영
        //DB마다 다르긴 한데 em.persist 한다고 기본적으로 인서트 안 나감
    }

    public Member findOne(Long id){ //단건 조회
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                 .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
