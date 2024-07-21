package hellojpa;

import hellojpa.domain.Member;
import hellojpa.domain.items.Book;
import hellojpa.domain.items.Item;
import hellojpa.domain.items.Movie;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            /*
            Order order = new Order();
            //양방향이라면//
            //order.addOrderItem(new OrderItem());
            //단방향이라면//
            em.persist(order);
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            em.persist(orderItem);
             */

            /*
            Movie movie = new Movie();
            movie.setDirector("aaa");
            movie.setActor("bbb");
            movie.setName("바람과함께사라지다");
            movie.setPrice(10000);
            em.persist(movie);
            em.flush();
            em.clear();

            Item findMovie = em.find(Item.class, movie.getId());
            System.out.println("findMovie = " + findMovie);
            */

            /*
            Member member = new Member();
            member.setName("user1");
            member.setCreatedBy("kim");
            member.setCreatedDate(LocalDateTime.now());
             */

            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("김영한");
            em.persist(book);

            tx.commit();
        } catch(Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}