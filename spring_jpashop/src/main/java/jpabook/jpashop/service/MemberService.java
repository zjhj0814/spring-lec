package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //기본적으로 readOnly = false
@RequiredArgsConstructor
public class MemberService {
    //@Autowired //접근할 수 없어서 테스트용으로 바꿀 수 없음
    //final 넣기
    private final MemberRepository memberRepository;

    //constructor injection
    //생성시점에 뭐가 필요한지 알 수 있음
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /*
    setter injection
    @Autowired //
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
     */

    @Transactional
    /*
    회원 가입
     */
    public Long join(Member member){
        //중복 회원 검증
        //문제가 있을 때 예외 터뜨리기
        validateDuplicatedMember(member);
        //회원 저장
        memberRepository.save(member);
        //DB에 들어간 시점이 아니여도 em.persist()할 때
        //키 값이 항상 박혀 있다는 것이 보장됨
        return member.getId();
    }
    
    //조회하는 곳에서의 성능 최적화
    //만약 같은 이름의 멤버가 회원가입을 한다면 문제 유발
    //멀티스레드 상황 등에 대해 고려해서 최후의 방어로
    //member의 name을 유니크 제약 조건으로 잡아주는 것을 권장
    private void validateDuplicatedMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){ //최적화를 원한다면? List의 수를 세서 0보다 크다면
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }
    //@Transactional(readOnly = true)
    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //@Transactional(readOnly = true)
    //회원 한 건만 존재
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
