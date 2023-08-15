package com.myk.hom.spring_crud_practice.service;

import com.myk.hom.spring_crud_practice.domain.Article;
import com.myk.hom.spring_crud_practice.repository.ArticleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
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

        //then
        Assertions.assertThat(article.getTitle()).isEqualTo(findArticle.getTitle());
    }

    @Test
    void 동적쿼리_테스트() {
        //given

        Article article = new Article("제목","내용");
        articleService.doAdd(article);
        String title = null;
        String body = "내용";

        Article article2 = new Article("제목1", "내용1");
        articleService.doAdd(article2);


        //when

        List<Article> articles = articleRepository.doSearchArticles(title, body);

        //then

        Assertions.assertThat(articles.size()).isEqualTo(1);

    }


}