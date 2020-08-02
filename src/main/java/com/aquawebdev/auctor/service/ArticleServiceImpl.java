package com.aquawebdev.auctor.service;

import com.aquawebdev.auctor.entity.Article;

import com.aquawebdev.auctor.entity.User;
import com.aquawebdev.auctor.repository.ArticleRepository;
import com.aquawebdev.auctor.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Override
    public boolean addArticles(String content, String login) {
        Article article = new Article();
        Optional<User> optionalUser = userRepository.findByLogin(login);
        if(!optionalUser.isPresent()) {
            return false;
        }
        article.setArticleContent(content);
        article.setUser(optionalUser.get());

        articleRepository.save(article);
        return true;
    }

    @Override
    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public Iterable<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }
}
