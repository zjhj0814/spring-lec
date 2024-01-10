package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;
    //new MemberService() 하면 여러 컨트롤러에서 멤버 서비스를 가져다 쓸 수 있기 때문에
    //여러 인스턴스를 생성할 필요 없이 하나만 만들면 됨 => 스프링 컨테이너에 등록하고 쓰기
    @Autowired //스프링 컨테이너에 있는 멤버 서비스와 이 멤버 서비스를 연결시킴
    //컨트롤러와 서비스를 연결할 때 @Autowired를 사용함
    //멤버 컨트롤러가 생성될 때 스프링 빈에 등록되어 있는 멤버 서비스를 객체를 가져다가 넣어줌(DI, 의존관계 주입)
    public MemberController(MemberService memberService) {
        //MemberService가 순수한 자바 클래스여서 스프링이 알 방법이 없음
        this.memberService = memberService;
    }

    @GetMapping("/members/new") //url치는 것(조회) getMapping
    public String createForm(){
        return "members/createMemberForm";
        //리턴하니까 template에서 찾음
        //view resolver라는 애를 통해서 createMemberForm.html이 선택되고
        //thymeleaf 템플릿 엔진에 렌더링을 함
    }
    @PostMapping("/members/new") //폼 같은데 넣어서 전달할 때
    public String create(MemberForm form){
        Member member=new Member();
        member.setName(form.getName());
        //member를 통해서 form에서 받은 값 클래스로 처리

        System.out.println("member = "+member.getName());

        memberService.join(member);
        return "redirect:/"; //홈 화면으로 돌리기
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        //멤버 리스트 자체를 모델에 다 담아서 화면에 넘길 것
        return "members/memberList";
    }
}
