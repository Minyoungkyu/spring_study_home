package com.myk.hom.spring_crud_practice.service;

import com.myk.hom.spring_crud_practice.domain.Member;
import com.myk.hom.spring_crud_practice.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {

        //given
        String loginId = "minyg58";
        String loginPw = "skehwkd123";
        String name = "영규";
        String nickname = "너굴맨";
        String cellphoneNo = "010-1111-2222";
        String email = "user@gmail.com";

        Member member = new Member(loginId, loginPw, name, nickname, cellphoneNo, email
        );

        //when
        memberService.doJoin(member);
        Member findmember = memberService.getMember(loginId);

        //then
        Assertions.assertThat(member).isEqualTo(findmember);

    }

    @Test
    void 중복() {
        //given
        String loginId = "minyg58";
        String loginPw = "skehwkd123";
        String name = "영규";
        String nickname = "너굴맨";
        String cellphoneNo = "010-1111-2222";
        String email = "user@gmail.com";

        //when

        Member member = new Member(loginId, loginPw, name, nickname, cellphoneNo, email
        );
        Member member2 = new Member(loginId, loginPw, name, nickname, cellphoneNo, email
        );

        memberService.doJoin(member);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.doJoin(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


    }

}