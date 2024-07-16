package hellojpa;

import jakarta.persistence.*;

@Entity
public class Locker_1 {
    @Id @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;
    private String name;
    @OneToOne(mappedBy = "locker_1")
    private Member_1 member;
}
