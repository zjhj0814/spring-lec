package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberRepositoryTest {
    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional //데이터가 들어가 있으면 반복적인 테스트를 못하니까
    @Rollback(false)
    public void testMember() throws Exception{
        //given
        Member member = new Member();
        member.setName("memberA");
        //when
        /*
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);
        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);
        System.out.println("findMember == member " + (findMember==member));
        //위 TEST 코드를 실행하면 H2 데이터베이스에 MEMBER 테이블이 생성된 것을 볼 수 있었다
        */
    }
}