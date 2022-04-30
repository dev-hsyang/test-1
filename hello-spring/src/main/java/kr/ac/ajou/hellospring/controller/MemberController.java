package kr.ac.ajou.hellospring.controller;

import kr.ac.ajou.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import kr.ac.ajou.hellospring.service.MemberService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// @Controller annonatation이 있으면
// Spring이 실행될때 생성되는 Spring Container에
// Controller annotation이 있는 클래스의 객체를 생성해서 Container에 넣고 관리한다.
@Controller
public class MemberController {

    private  final MemberService memberService;

    // @Autowired:: Spring container에 있는 memberservice 가져다 연결시켜준다.
    // Autowired는 Controller와 Bean을 연결시켜주는 역할을 한다. Bean : @Component / @Service / @Repository
    @Autowired
    public MemberController(MemberService memberService){
        // 여기서는 Controller와 MemberService를 연결시킨다.
        // Spring Container에 Bean으로 등록되어 있는 MemberService parameter로 넣어주는 것. :::: Dependency Injection
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    // 데이터를 조회할 때 주로 GetMapping을 쓰고
    // 데이터를 등록할 때 PostMapping을 쓴다.
    // 같은 URL일지라도 GetMapping에서 호출된 HTML의 <form action="/members/new" method="post">가
    // 입력이 완료된 데이터를 똑같은 URL로 넘겨준다. 이때 실행되는 URL은 PostMapping의 URL이다.
    @PostMapping("/members/new")
    public String create(MemberForm form){
        // 입력 파라메터로 HTML에서 받은 name이 넘어온다

        Member member = new Member();
        member.setName(form.getName());
        // member에 입력받은 name이 set된다.

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
