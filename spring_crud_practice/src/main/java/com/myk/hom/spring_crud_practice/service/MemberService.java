package com.myk.hom.spring_crud_practice.service;

import com.myk.hom.spring_crud_practice.domain.Member;
import com.myk.hom.spring_crud_practice.repository.MemberRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public String doJoin(Member member) {
        try {
        validateDuplicateMember(member);
        } catch ( IllegalStateException e) {
            return e.getMessage();
        }
        return memberRepository.doJoin(member).getLoginId() + " 님 회원가입을 환영합니다.";
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.getMember(member.getLoginId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 사용중인 아이디입니다.");
                });
    }

    public Member getMember(String loginId) {
        return memberRepository.getMember(loginId).get();
    }

    public List<Member> getSearchMembers(String loginId, String name, String nickname) {
        return memberRepository.getSearchMembers(loginId, name, nickname);
    }
}
