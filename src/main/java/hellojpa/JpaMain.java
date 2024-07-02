package hellojpa;

import jakarta.persistence.*;

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
            tx.commit();
        } catch(Exception e){
            tx.rollback();
        } finally {
            em.close(); //계속 데이터베이스 연결중
        }

        emf.close();
    }
}
