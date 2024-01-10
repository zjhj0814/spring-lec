package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
//@Repository
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long,Member> store=new HashMap<>();
    private static long sequence = 0L;
    //동시선 문제에 의해서 Atomic Long과 같이 써야 함

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        //위에 것처럼 쓰면 클라이언트에서 무엇을 할 수 있음
        //요즘은 Null을 그냥 내보내기보다는 Optional로 감싸는 추세
    }

    @Override
    public Optional<Member> findByName(String name) {
        //Lambda로 루프 돌리기
        return store.values().stream()
                .filter(member->member.getName().equals(name)) //같은 경우에만 필터링
                .findAny(); //하나로 또 찾기
        //결과가 원래 Optional로 반환됨
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
