package hellojpa.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

@Entity
public class Member{
    @Id @GeneratedValue
    private Long id;
    @Column(name = "USERNAME")
    private String username;
}