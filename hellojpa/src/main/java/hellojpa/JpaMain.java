package hellojpa;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamＢ");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("관리자1");
            member1.setAge(10);
            member1.setTeam(teamA);
            member1.setType(MemberType.ADMIN);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            member2.setTeam(teamB);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("관리자3");
            member3.setTeam(teamB);
            em.persist(member3);

            em.flush();
            em.clear();

            /*
            TypedQuery<Member> query1 = em.createQuery("select m from Member m where m.username = :username", Member.class);
            //TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);
            //Query query3 = em.createQuery("select m.username, m.age from Member m");
            query1.setParameter("username","member1");
            Member singleResult = query1.getSingleResult();
            System.out.println("singleResult = " + singleResult.getUsername());

            List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
            List<Team> result2 = em.createQuery("select t from Member m join m.team t", Team.class)
                    .getResultList(); //예측할 수 있게 team으로 join
            Member findMember = result.get(0);
            findMember.setAge(20);

            //Member result = query1.getSingleResult();
            //Spring Data JPA -> 결과가 없으면 Null, Optional<>로 반환
            //System.out.println("result = " + result);

            //1.Query로 값 조회
            List resultList = em.createQuery("select m.username, m.age from Member m").getResultList();

            Object o = resultList.get(0);
            Object[] object = (Object[]) o;
            System.out.println("username = " + object[0]);
            System.out.println("age = " + object[1]);
            
            //2. TypeQuery로 조회
            List<Object[]> resultList1 = em.createQuery("select m.username, m.age from Member m").getResultList();
            Object[] o1 = resultList1.get(0);
            System.out.println("username = " + object[0]);
            System.out.println("age = " + object[1]);

            //3. DTO로 조회
            List<MemberDTO> resultList2 = em.createQuery("select new hellojpa.MemberDTO(m.username, m.age) from Member m", MemberDTO.class).getResultList();
             */

            /*
            List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();

            System.out.println("result.size() = " + result.size());
            for (Member member1 : result) {
                System.out.println("member1 = " + member1);
            }
             */

            /* JOIN
            String query = "select m from Member m left outer join m.team t on t.name = 'teamA'";
            List<Member> result = em.createQuery(query, Member.class).getResultList();
             */

            /* JPQL 타입 표현과 기타식
            String query = "select m.username, 'HELLO', TRUE from Member m " +
                        "where m.type = hellojpa.MemberType.ADMIN";
            List<Object[]> result = em.createQuery(query)
                    .getResultList();
            for (Object[] objects : result) {
                System.out.println("objects = " + objects[0]);
                System.out.println("objects = " + objects[1]);
                System.out.println("objects = " + objects[2]);
            }
            */

            /* case
            String query = "select "
                    + "case when m.age <= 10 then '학생요금' "
                    + "when m.age >=60 then '경로요금' "
                    + "else '일반요금' end "
                    + "from Member m";
            List<String> result = em.createQuery(query, String.class).getResultList();
            for (String s : result) {
                System.out.println("s = " + s);
            }
             */

            /* coalesce
            String query = "select coalesce(m.username, '이름 없는 회원') from Member m";
            List<String> result = em.createQuery(query, String.class).getResultList();
            for (String s : result) {
                System.out.println("s = " + s);
            }*/

            /*
            String query = "select nullif(m.username, '관리자') from Member m";
            List<String> result = em.createQuery(query, String.class).getResultList();
            for (String s : result) {
                System.out.println("s = " + s);
            }*/

            /*
            String query = "select concat('a','b') from Member m";
            String query1 = "select substring(m.username, 2,3) from Member m";
            String query2 = "select locate('de','abcdefg') from Member m"; //Integer로 나옴
            String query3 = "select size(t.member) from Team t";
            String query4 = "select index(t.member) from Team t";

            //String query5 = "select function('group_concat', m.username) from Member m"; //그룹이 가지고 있는 것을 한번에 보여주기
            String query5 = "select group_concat(m.username) from Member m"; //그룹이 가지고 있는 것을 한번에 보여주기
            List<String> result = em.createQuery(query5, String.class).getResultList();
            for (String s : result) {
                System.out.println("s = " + s);
            }*/

            /*
            String query = "select t.member from Team t";
            String query2 = "select m.username from Team t join t.member m"; //from절에 명시적 join
            Collection result = em.createQuery(query2, Collection.class).getResultList();
            for (Object o : result) {
                System.out.println("o = " + o);
            }
             */

            //다대일 조인
//            String query = "select m from Member m join fetch m.team";
//            List<Member> result = em.createQuery(query, Member.class).getResultList();
//            for (Member member : result) {
//                System.out.println("member = " + member.getUsername() + ", " + member.getTeam().getName());
//            }

            /*
            //일대다 조인일 때 애플리케이션에서 중복 제거
            String query = "select t from Team t join fetch t.member";
            List<Team> result = em.createQuery(query, Team.class)
                    .getResultList();
            //System.out.println("result = " + result.size());
            for (Team team : result) {
                System.out.println("team = " + team.getName() + ", " + team.getMember().size());
                for (Member member : team.getMember()) {
                    System.out.println("-->member = " + member);
                }
            }
             */

            /* 엔티티 직접 사용
            String query = "select m from Member m where m.id = :member";
            String query2 = "select m from Member m where m.team = :team";
            Member findMember = em.createQuery(query2, Member.class)
                    .setParameter("team", teamA)
                    .getSingleResult();
            System.out.println("findMember = " + findMember);
             */

            /* namedQuery
            List<Member> resultList = em.createNamedQuery("Member.findByUsername", Member.class)
                    .setParameter("username", "관리자1")
                    .getResultList();
            for (Member member : resultList) {
                System.out.println("member = " + member);
            }
             */

            //FLUSH
            int resultCount = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();
            System.out.println("resultCount = " + resultCount);

            em.clear();

            Member findMember = em.find(Member.class, member1.getId());

            System.out.println("member1.getAge() = " + findMember.getAge()); //영속성 컨텍스트 초기화 후 다시 조회
            System.out.println("member2.getAge() = " + member2.getAge());
            System.out.println("member3.getAge() = " + member3.getAge());

            tx.commit();
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
