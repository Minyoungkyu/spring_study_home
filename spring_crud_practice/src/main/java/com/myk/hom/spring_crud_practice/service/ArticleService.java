package com.myk.hom.spring_crud_practice.service;

import com.myk.hom.spring_crud_practice.domain.Article;
import com.myk.hom.spring_crud_practice.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Long doAdd(Article article) {
        articleRepository.doAdd(article);
        return article.getId();
    }

    public Optional<Article> getArticle(Long id) {
        return articleRepository.getArticle(id);
    }

    public List<Article> getArticles() {
        return articleRepository.getArticles();
    }

    public Long doModify(Long id, String title, String body) {
        if( articleRepository.getArticle(id).isEmpty() ) {
            return -1L;
        }
        return articleRepository.doModify(id, title, body).getId();
    }

    public Long doDelete(Long id) {
        if( articleRepository.getArticle(id).isEmpty() ) {
            return -1L;
        }
        Article article = articleRepository.getArticle(id).get();
        Long deletId = article.getId();
        articleRepository.doDelete(article);
        return deletId;
    }
}
