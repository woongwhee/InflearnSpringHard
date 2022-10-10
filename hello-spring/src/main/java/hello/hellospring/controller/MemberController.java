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
//스프링 컨테이너가 처음 만들어질때 Controlller를 넣어둔다.
public class MemberController {
    //필드주입
    //@Autowired private MemberService memberService;

    private final MemberService memberService;
    //생성자 주입 (권장) 의존관계가 실행중에 동적으로 변경하는 경우가 없기때문에 생성자 주입을 권장함
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    //setter주입
    //로딩시 세터로 주입하면 퍼블릭하게 설정해 놔야되기때문에 임의로 접근 할 수 있어서 좋지 않다.
//    @Autowired
//    public void setMemberController(MemberService memberService) {
//        this.memberService = memberService;
//    }
    @GetMapping("/members/new")
    public String creatForm(){
        return "members/createMemberForm";
    }
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member =new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/";

    }
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members=memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList.html";
    }
}

