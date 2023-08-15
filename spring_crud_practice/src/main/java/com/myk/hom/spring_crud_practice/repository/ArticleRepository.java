package com.myk.hom.spring_crud_practice.repository;

import com.myk.hom.spring_crud_practice.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    Article doAdd(Article article);

    Optional<Article> getArticle(Long id);

    List<Article> getArticles();

    Article doModify(Long id, String title, String body);

    void doDelete(Article article);

    List<Article> doSearchArticles(String title, String body);
}
