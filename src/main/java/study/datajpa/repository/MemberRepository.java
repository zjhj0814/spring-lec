package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //List<Member> findByUsername(String username); //구현하지 않아도 동작함 --> 쿼리 메소드 기능

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);
    List<Member> findTop3HelloBy(); //전체 조회
    @Query(name = "Member.findByUsername")
    List<Member> findByUsername(@Param("username")String username);
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username")String username, @Param("age")int age);
    @Query("select m.username from Member m")
    List<String> findUsernameList();
    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto(); //new operation으로 생성자와 매핑
    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names")List<String> names);
    List<Member> findListByUsername(String username); //전체 조회
    Member findMemberByUsername(String username); //단건 조회
    Optional<Member> findOptionalByUsername(String username); //Optional 조회

}
