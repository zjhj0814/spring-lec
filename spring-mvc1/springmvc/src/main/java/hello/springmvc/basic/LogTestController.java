package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
    //private final Logger log = LoggerFactory.getLogger(LogTestController.class);

    @RequestMapping("/log-test")
    public String LogTest(){
        String name = "Spring";

        System.out.println("name = " + name);

        //로그 정보: 시간, 프로세스ID, 실행한 쓰레드정보, 컨트롤러, 메시지
        //log.trace("trace my log=" + name); 잘못된 사용 방법!
        //자바는 문자 치환 후 더하는 연산이 실행되어 메모리에 저장됨 -> 메모리, CPU 사용
        log.trace("trace log={}", name);
        log.debug("debug log={}", name); //개발 서버
        log.info("info log={}", name); //비즈니스 정보, 운영 시스템 필요 정보 //운영 서버
        log.warn("warn log={}", name); //경고
        log.error("error log={}", name); //에러

        return "ok";
    }
}
