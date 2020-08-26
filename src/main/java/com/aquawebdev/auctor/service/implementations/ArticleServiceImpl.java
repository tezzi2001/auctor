package com.aquawebdev.auctor.service.implementations;

import com.aquawebdev.auctor.entity.Article;
import com.aquawebdev.auctor.entity.User;
import com.aquawebdev.auctor.repository.ArticleRepository;
import com.aquawebdev.auctor.repository.UserRepository;
import com.aquawebdev.auctor.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public List<Article> articlesForOnePage(int page) {
        int articlesPerPage = 7;
        List<Article> articlesForPage = new ArrayList<>();
        List<Article> persistentArticles = articleRepository.findAll();

        Comparator<Article> comparator = Comparator.comparing(Article::getDate);
        persistentArticles.sort(comparator);

        int i = articlesPerPage * (page - 1);
        for(; i < articlesPerPage * page; i++) {
          articlesForPage.add(persistentArticles.get(i));
        }
        return articlesForPage;
    }
}
