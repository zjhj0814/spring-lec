package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional //기본적으로 커밋을 안 하고 롤백
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    //@Rollback(false)
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");
        //when
        Long savedId = memberService.join(member);
        //then
        assertEquals(member,memberRepository.findOne(savedId));
        //JPA에서 같은 트랜잭션 안에서 같은 엔티티, ID값이 똑같으면
        //같은 영속성 콘텍스트에서 똑같은 애로 관리되니까 가능함
    }
    //테스트가 끝나면 @Transactional로 롤백해서 DB에 남는 것 없음

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");
        //when
        memberService.join(member1);
//        try {
            memberService.join(member2); //예외가 발생해야 한다!!
//        }catch(IllegalStateException e){
//            return;
//        }
        //then
        fail("예외가 발생해야 한다."); //Assert.junit에 fail
    }
}