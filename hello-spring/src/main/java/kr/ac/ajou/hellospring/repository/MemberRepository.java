package kr.ac.ajou.hellospring.repository;

import kr.ac.ajou.hellospring.domain.Member;

import java.util.Optional;
import java.util.*;

public interface MemberRepository {
    // 회원 리포지토리 인터페이스

    Member save(Member member); // repository에 member가 save된다
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
