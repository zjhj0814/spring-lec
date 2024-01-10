package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { //인터페이스 -> 인터페이스 //멤버에 pk(id) 타입
    //DB 접근 메소드는 공통된 것이 많음 → JPA 라이브러리 내장됨
    //도메인(id, 이메일,…)은 인터페이스로 공통화 불가능
    //JPQL select m from Member m where m.name = ?

    @Override
    Optional<Member> findByName(String name); //Optional<Member> findByNameAndId(String name, Long id); 도 가능
    //스프링 데이터 JPA가 인터페이스를 보고 구현체를 만들어서 스프링 빈에 등록해 줌
}
