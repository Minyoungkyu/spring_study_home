package com.myk.hom.spring_crud_practice.repository;


import com.myk.hom.spring_crud_practice.domain.Article;
import com.myk.hom.spring_crud_practice.domain.QArticle;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.myk.hom.spring_crud_practice.domain.QArticle.article;

public class JpaArticleRepository implements ArticleRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;
    private final QArticle qArticle;
    @Autowired
    public JpaArticleRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
        this.qArticle = QArticle.article;
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

    @Override
    public List<Article> doSearchArticles(String title, String body) {
        return query
                .selectFrom(qArticle)
                .where(ctTitle(title).or(ctBody(body))
                        )
                .fetch();

//        return query
//                .selectFrom(qArticle)
//                .where(eqTitle(title),
//                        eqBody(body))
//                .fetch();
    }

    private BooleanExpression ctTitle(String title) {
        if(title == null) return null;
        return qArticle.title.contains(title);
    }

    private BooleanExpression ctBody(String body) {
        if(body == null) return null;
        return qArticle.body.contains(body);
    }








//    private BooleanExpression eqTitle(String title) {
//        if(title == null) return null;
//        return qArticle.title.eq(title);
//    }




//    @Override
//    public Optional<Member> findByName(String name) {
//        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
//                .setParameter("name", name)
//                .getResultList();
//
//        return result.stream().findAny();
//    }



}
