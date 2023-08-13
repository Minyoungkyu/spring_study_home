package com.myk.hom.spring_crud_practice.service;

import com.myk.hom.spring_crud_practice.domain.Article;
import com.myk.hom.spring_crud_practice.repository.ArticleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired ArticleService articleService;
    @Autowired ArticleRepository articleRepository;

    @Test
    void 게시물_조회() {
        //given
        Article article = new Article("제목","내용");

        //when
        articleService.doAdd(article);
        Article findArticle = articleService.getArticle(article.getId()).get();

        //than
        Assertions.assertThat(article.getTitle()).isEqualTo(findArticle.getTitle());
    }


}