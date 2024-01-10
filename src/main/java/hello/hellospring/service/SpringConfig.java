package hello.hellospring.service;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

//    private DataSource dataSource; //데이터베이스 연결 정보
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em){
//        this.em=em;
//    }

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean //스프링 빈에 등록할 거야!
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }

//    @Bean
//    public MemberRepository memberRepository(){
//        //return new JdbcMemberRepository(dataSource); //스프링 빈에 Jdbc 리포지토리를 새로 등록!
//        //return new MemoryMemberRepository(); //구현체
//        //return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
    //Controller는 어쩔 수 없이 @Controller+@Autowired으로..
}
