package com.myk.hom.spring_crud_practice.repository;


import com.myk.hom.spring_crud_practice.domain.Article;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class JpaArticleRepository implements ArticleRepository {

    private final EntityManager em;

    @Autowired
    public JpaArticleRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Article doAdd(Article article) {
        em.persist(article);
        return article;
    }

    @Override
    public Optional<Article> getArticle(Long id) {
        return Optional.ofNullable(em.find(Article.class, id));
    }

    @Override
    public List<Article> getArticles() {
        return em.createQuery("select a from Article a", Article.class)
                .getResultList();
    }

    @Override
    public Article doModify(Long id, String title, String body) {
        Article article = em.find(Article.class, id);
        article.setTitle(title);
        article.setBody(body);
        return article;
    }

    @Override
    public void doDelete(Article article) {
        em.remove(article);
    }

//    @Override
//    public Optional<Member> findByName(String name) {
//        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
//                .setParameter("name", name)
//                .getResultList();
//
//        return result.stream().findAny();
//    }



}
