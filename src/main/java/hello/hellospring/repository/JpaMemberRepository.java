package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;
    //JPA는 entitymanager로 모두 동작함 + 내부적으로 데이터 소스 정보를 가지고 있어서 DB와 통신함

    public JpaMemberRepository(EntityManager em) {
        //라이브러리 받으면 스프링 부트가 자동으로 현재 DB랑 연결까지 해서 entitymanager생성함 injection만 하기
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //persist 영구적인 -> 영구적으로 저장하다
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    //pk기반이 아닌 것들은 jpql으로 작성하기
    @Override
    public Optional<Member> findByName(String name) {
        //jpql이라는 객체지향 쿼리 언어 사용
        //<-> sql과 유사하지만,
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
        //"select m(멤버 자체를 조회) from Member (as) m"
        //기존 sql은 id, name 찾은 다음에 맵핑하고..
        //보통 테이블 대상으로 쿼리를 날리는데, 멤버 엔티티를 대상으로 쿼리를 날려서 SQL로 번역됨
    }
}
