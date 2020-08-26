package com.aquawebdev.auctor.service;

import com.aquawebdev.auctor.entity.Article;
import java.util.List;
import java.util.Optional;

public interface ArticleService {
    boolean addArticles(String content, String login);
    Optional<Article> findById(Long id);
    List<Article> findAll();
    void deleteById(Long id);
    List<Article> articlesForOnePage(int page);
}
