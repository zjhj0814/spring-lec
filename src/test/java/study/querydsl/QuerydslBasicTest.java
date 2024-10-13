package study.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;
import study.querydsl.entity.Team;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class QuerydslBasicTest {
    @Autowired
    EntityManager em;
    JPAQueryFactory queryFactory;
    //여러 멀티 쓰레드가 em 접근하면 어떻게 될까? 
    //동시성 문제 없도록 설계되어 있고 em 자체가 멀티스레드에 문제없게 설계됨
    //여러 멀티스레드에서도 들어와도 현재 내 트랜잭션이 어디에 걸려있는지에 따라서 트랜잭션에 바인딩되도록 분배함

    @BeforeEach
    public void before(){
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1",10,teamA);
        Member member2 = new Member("member2",20,teamA);

        Member member3 = new Member("member3",30,teamB);
        Member member4 = new Member("member4",40,teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @Test
    public void startJPQL(){
        //member1을 찾아라
        String qlString = "select m from Member m where m.username =:username";
        Member findByJPQL = em.createQuery(qlString, Member.class)
                .setParameter("username", "member1")
                .getSingleResult();
        assertThat(findByJPQL.getUsername()).isEqualTo("member1");
    }

    @Test
    public void startQuerydsl() {
        queryFactory = new JPAQueryFactory(em);
        //엔티티매니저를 생성자에 -> QueryFactory가 엔티티 매니저와 함께 찾든가..
        //QMember m = new QMember("m"); //구분하는 이름인데 크게 별칭이 중요하지 않음
        QMember m = QMember.member;
        Member findByQuerydsl = queryFactory
                .select(m) //select(QMember.member) static import하면 코드가 깔끔해짐
                .from(m) //from(QMember.member)
                .where(m.username.eq("member1")) //JDBC prepare statement로 자동 파라미터 바인딩
                .fetchOne();
        assertThat(findByQuerydsl.getUsername()).isEqualTo("member1");
    }

}
