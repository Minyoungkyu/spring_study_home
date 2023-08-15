package com.myk.hom.spring_crud_practice.controller;

import com.myk.hom.spring_crud_practice.domain.Article;
import com.myk.hom.spring_crud_practice.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/user/article/doAdd")
    @ResponseBody
    public String doAdd(String title, String body) {
        Article article = new Article(title, body);
        return articleService.doAdd(article) + " 번 등록 완료";
    }

    @GetMapping("/user/article/getArticle")
    @ResponseBody
    public Optional<Article> getArticle(Long id) {
        return articleService.getArticle(id);
    }

    @GetMapping("/user/article/getArticles")
    @ResponseBody
    public List<Article> getArticles() {
        return articleService.getArticles();
    }

    @GetMapping("/user/article/doModify")
    @ResponseBody
    public String doModify(Long id, String title, String body) {
        Long result = articleService.doModify(id, title, body);

        if(result == -1) return "존재하지 않는 게시번호";
        else return result + " 번 수정 완료";
    }

    @GetMapping("/user/article/doDelete")
    @ResponseBody
    public String doDelete(Long id) {
        Long result = articleService.doDelete(id);

        if(result == -1) return "존재하지 않는 게시번호";
        else return result + " 번 삭제 완료";
    }

    @GetMapping("/user/article/doSearch")
    @ResponseBody
    public List<Article> doSearchArticles(String title, String body) {
        return articleService.doSearchArticles(title, body);
    }

}
