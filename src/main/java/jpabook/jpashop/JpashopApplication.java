package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//위 패키지와 위 패키지의 하위 패키지를 컴포넌트 스캔하여 스프링 빈에 저장
public class JpashopApplication {

	public static void main(String[] args) {
		/*
		Hello hello=new Hello();
		hello.setData("hello");
		String data = hello.getData();
		System.out.println("data = " + data);
		*/
		SpringApplication.run(JpashopApplication.class, args);
	}
}
