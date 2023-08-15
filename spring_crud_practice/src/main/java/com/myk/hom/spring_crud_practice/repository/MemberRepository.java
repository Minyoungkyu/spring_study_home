package com.myk.hom.spring_crud_practice.repository;

import com.myk.hom.spring_crud_practice.domain.Member;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member doJoin(Member member);

    Optional<Member> getMember(String loginId);

    List<Member> getAllMembers();

    List<Member> getSearchMembers(String loginId, String name, String nickname);


}
