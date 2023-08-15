package com.myk.hom.spring_crud_practice.controller;

import com.myk.hom.spring_crud_practice.domain.Member;
import com.myk.hom.spring_crud_practice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/user/member/doJoin")
    @ResponseBody
    public String doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
        Member member = new Member(loginId, loginPw, name, nickname, cellphoneNo, email);
        return memberService.doJoin(member);
    }

    @GetMapping("/user/member/doSearch")
    @ResponseBody
    public List<Member> doSearch(String loginId, String name, String nickname) {
        return memberService.getSearchMembers(loginId, name, nickname);
    }

}
