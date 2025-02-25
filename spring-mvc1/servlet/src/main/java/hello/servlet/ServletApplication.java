package hello.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan //서블릿 자동 등록
@SpringBootApplication
public class ServletApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServletApplication.class, args);
    }

	/*
	스프링 부트를 띄울 때 자동으로 실행되는 일
    @Bean
    ViewResolver internalResourceViewResolver() {
        return new InternalResourceViewResolver("/WEB-INF/views", ".jsp");
    }
	 */

    /*
    직접 스프링 빈에 등록하는 방법
    @Bean
    SpringMemberFormControllerV1 springMemberFormControllerV1() {
        return new SpringMemberFormControllerV1();
    }
     */
}
