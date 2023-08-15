package com.myk.hom.spring_crud_practice.repository;


import com.myk.hom.spring_crud_practice.domain.Member;
import com.myk.hom.spring_crud_practice.domain.QMember;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;
    private final QMember qMember;

    @Autowired
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
        this.qMember = QMember.member;
    }

    @Override
    public Member doJoin(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> getMember(String logindId) {
        return query
                .selectFrom(qMember)
                .where(qMember.loginId.eq(logindId))
                .stream().findAny();
    }

    @Override
    public List<Member> getAllMembers() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    @Override
    public List<Member> getSearchMembers(String loginId, String name, String nickname) {
        return query
                .selectFrom(qMember)
                .where(ctLoginId(loginId).or(ctName(name).or(ctNickname(nickname))))
                .fetch();
    }

    private BooleanExpression ctLoginId(String loginId) {
        if(loginId == null) return null;
        return qMember.loginId.contains(loginId);
    }

    private BooleanExpression ctName(String name) {
        if(name == null) return null;
        return qMember.name.contains(name);
    }

    private BooleanExpression ctNickname(String nickname) {
        if(nickname == null) return null;
        return qMember.nickname.contains(nickname);
    }






}
