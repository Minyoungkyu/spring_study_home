package com.myk.hom.spring_crud_practice;

import com.myk.hom.spring_crud_practice.repository.ArticleRepository;
import com.myk.hom.spring_crud_practice.repository.JpaArticleRepository;
import com.myk.hom.spring_crud_practice.repository.JpaMemberRepository;
import com.myk.hom.spring_crud_practice.repository.MemberRepository;
import com.myk.hom.spring_crud_practice.service.ArticleService;
import com.myk.hom.spring_crud_practice.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public ArticleService articleService() {
        return new ArticleService(articleRepository());
    }

    @Bean
    public ArticleRepository articleRepository() {
        return new JpaArticleRepository(em);
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
    }

}
