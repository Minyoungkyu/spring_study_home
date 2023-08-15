package com.myk.hom.spring_crud_practice;


import com.myk.hom.spring_crud_practice.domain.Article;
import com.myk.hom.spring_crud_practice.domain.QArticle;
import com.myk.hom.spring_crud_practice.repository.JpaArticleRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class SpringCrudPracticeApplicationTests {

	@Autowired
	EntityManager em;

	@Test
	void contextLoads() {
		Article article = new Article("제에모옥","내애애요옹오오옹");
		em.persist(article);

		JPAQueryFactory query = new JPAQueryFactory(em);
		QArticle qArticle = new QArticle("a");

		Article result = query
				.selectFrom(qArticle)
				.fetchOne();

		Assertions.assertThat(result).isEqualTo(article);


	}



}
