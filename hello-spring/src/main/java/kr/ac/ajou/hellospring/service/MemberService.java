package kr.ac.ajou.hellospring.service;

import kr.ac.ajou.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.ac.ajou.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

// Annotation이 없으면 이 클래스는 그냥 순수한 자바 코드일 뿐
// 즉 Spring에서 이 클래스를 인지할 방법이 없다.
// Annotation을 사용하여 Spring에게 인식시키고 Container에 담는것이다.
//@Service
public class MemberService {

    private final MemberRepository memberRepository;

    // @Service에서 Spring이 이 클래스를 container에 등록을 할 때,
    // @Autowired를 확인하고 container에 등록된 @Repository MemberRepository를 찾아서 주입하도록 한다.
    //@Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member){
        validateDuplicateMember(member); //같은 이름이 있는지 확인
        memberRepository.save(member);
        return member.getId();
    }

    //중복회원 확인하는 함수
    private void validateDuplicateMember(Member member){
        memberRepository.findByName(member.getName()).ifPresent(m ->{
            throw new IllegalStateException("이미 존재하는 회원입니다. ");
        });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
