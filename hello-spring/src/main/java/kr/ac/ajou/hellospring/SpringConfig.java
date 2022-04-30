package kr.ac.ajou.hellospring;

import kr.ac.ajou.hellospring.repository.MemberRepository;
import kr.ac.ajou.hellospring.repository.MemoryMemberRepository;
import kr.ac.ajou.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
        // Spring Container에 bean으로 등록되어 있는 memberRepository를 wiring한다.
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
