package hellojpa;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Hibernate;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

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

            /*
            Member_1 member = new Member_1();
            member.setUsername("member1");
            em.persist(member);

            Team_1 team = new Team_1();
            team.setName("teamA");
            team.getMembers().add(member);

            em.persist(team);
            */

            /*
            Member member = em.find(Member.class, 1L);
            printMemberAndTeam(member);
             */
    
            /*
            프록시
            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            em.persist(member2);

            em.flush();
            em.clear();

            Member m1 = em.find(Member.class, member1.getId());
            System.out.println("m1 = " + m1.getClass());

            Member m2 = em.getReference(Member.class, member2.getId());
            System.out.println("m2 = " + m2.getClass());

            //만약 영속성 컨텍스트를 꺼버린다면? -> 프록시 초기화 실패(no Session : 영속성 컨텍스트가 없음)
            //em.detach(m2);
            //em.close();
            //em.clear();
            m2.getUsername();
            System.out.println("m2 = " + m2.getUsername());

            //Member refMember = em.find(Member.class, member2.getId());
            //System.out.println("refMember = " + refMember.getClass());
            //프록시로 한번 조회하면 em.find() 후 == 해도 맞추기 위해
            //System.out.println("refMember == m2 " + (refMember == m2));

            Member reference = em.getReference(Member.class, member1.getId());
            System.out.println("reference = " + reference.getClass());
            System.out.println("a == a: " + (m1 == reference));

            Hibernate.initialize(reference); //프록시 강제 초기화
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(m2)); //프록시 인스턴스의 초기화 여부 확인

            logic(m1, m2);
            */

            /*
            즉시 로딩 및 지연 로딩
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setTeam(teamB);
            em.persist(member2);

            em.flush();
            em.clear();

            List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class)
                    .getResultList(); //select * from Member

            Member m = em.find(Member.class, member1.getId());
            System.out.println("m = " + m.getTeam().getClass());

            System.out.println("===========");
            m.getTeam(); //프록시를 가져오기 때문에 초기화 X
            m.getTeam().getName(); //실제 team을 사용하는 시점에 초기화 O
            System.out.println("===========");
             */

            /*
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            //CascadeType.ALL
            //em.persist(child1);
            //em.persist(child2);
            em.persist(parent);

            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            findParent.getChildList().remove(0);
            */

            /*
            Address address = new Address("city", "street", "1001");

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(address);
            em.persist(member);

            Address copyAddress = new Address(address.getCity(), address.getCity(), address.getStreet());

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setHomeAddress(copyAddress);
            em.persist(member2);

            //member.getHomeAddress().setCity("newCity");

            Address newAddress = new Address("NewCity", address.getStreet(), address.getZipcode());
            member.setHomeAddress(newAddress);
             */

            /*
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity","street","10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressEntity("old1","street","10000"));
            member.getAddressHistory().add(new AddressEntity("old1","street","10000"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("=========START========");
            Member findMember = em.find(Member.class, member.getId());

            List<Address> addressHistory = findMember.getAddressHistory();
            for(Address address : addressHistory){
                System.out.println("address = " + address.getCity());
            }

            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            for (String favoriteFood : favoriteFoods){
                System.out.println("favoriteFood = " + favoriteFood);
            }

            //findMember.getHomeAddress().setCity("newCity"); 값 타입은 불변객체로
            //homeCity -> newCity
            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity",a.getStreet(),a.getZipcode()));
            //치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");
            //old1->new1
            findMember.getAddressHistory().remove(new AddressEntity("old1","street","10000")); //equals, hash 제대로 구현해야 함
            findMember.getAddressHistory().add(new AddressEntity("new1","street","10000"));
            */

            /* JPQL
            List<Member> result = em.createQuery(
                    "select m From Member m where m.username like '%kim%'",
                    Member.class).getResultList();
            for(Member member : result){
                System.out.println("member = " + member);
            } */

            /* Criteria
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            Root<Member> m = query.from(Member.class);
            CriteriaQuery<Member> cq = query.select(m);

            String username = "dsafas";
            if(username != null){
                cq = cq.where(cb.equal(m.get("username"), "kim"));
            }
            List<Member> resultList = em.createQuery(cq).getResultList(); */

            /* Native Query
            em.createNativeQuery("select MEMBER_ID, city, street, USERNAME from MBR").getResultList(); */

            tx.commit();
        } catch(Exception e){
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close(); //계속 데이터베이스 연결중
        }
        emf.close();
    }

    private static void logic(Member m1, Member m2) {
        System.out.println("m1 == m2 "+(m1 instanceof Member));
        System.out.println("m1 == m2 "+(m2 instanceof Member));
    }

    private static void printMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);

        Team team = member.getTeam();
        System.out.println("team = " + team);
    }
}
