package hellojpa;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("hello"); //데이터베이스 연결
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /* 1. 멤버 생성
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");
            em.persist(member);
            */
            /* 2. 멤버 조회
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
            */
            /* 3. 멤버 삭제
            Member findMember = em.find(Member.class, 1L);
            em.remove(findMember);
            */
            /* 4. 멤버 수정
            Member findMember = em.find(Member.class, 1L); 
            //JPA를 통해 엔티티를 가져오면 JPA가 관리하고
            //트랜잭션 커밋 시점에 바뀐 부분을 update 쿼리 날리고 트랜잭션 종료
            findMember.setName("HelloJPA");
            */

            //JPQL
            /*
            List<Member> result = em.createQuery("select m from Member m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();
            */

            /* 엔티티 상태
            //비영속
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");
            //영속
            System.out.println("===BEFORE===");
            em.persist(member);
            System.out.println("===AFTER===");
            */
            
            /* 1차 캐시
            Member findMember1 = em.find(Member.class, 101L);
            Member findMember2 = em.find(Member.class, 101L);
            System.out.println("result = " +(findMember1 == findMember2));
            */

            /* 트랜잭션을 지원하는 쓰기 지연
            Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");

            em.persist(member1);
            em.persist(member2);
            System.out.println("====================");
            */

            /* dirty checking
            Member member1 = new Member(150L, "A");
            em.persist(member1);
            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZ");
            //em.persist(member); 해당 코드를 통해 얻을 수 있는 이득은 아무것도 없다
            //안 좋은 예시 : if(member.getName().equals("ZZZZ")) em.update(member);
            */

            /* flush
            Member member = new Member(200L, "member200");
            em.persist(member);
            em.flush();
            System.out.println("===========");
            */

            /*
            Member member = em.find(Member.class, 150L);
            member.setName("AAAAA");
            //1. em.detach(member);
            //2. em.clear();
            */

            Member_1 member = new Member_1();
            member.setUsername("member1");
            em.persist(member);

            Team_1 team = new Team_1();
            team.setName("teamA");
            team.getMembers().add(member);

            em.persist(team);

            tx.commit();
        } catch(Exception e){
            tx.rollback();
        } finally {
            em.close(); //계속 데이터베이스 연결중
        }

        emf.close();
    }
}
