package com.aquawebdev.auctor.service;

import com.aquawebdev.auctor.entity.News;
import com.aquawebdev.auctor.entity.User;
import com.aquawebdev.auctor.repository.NewsRepository;
import com.aquawebdev.auctor.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    @Override
    public boolean addNews(String content, String login) {
        News news = new News();
        Optional<User> optionalUser = userRepository.findByLogin(login);

        if (!optionalUser.isPresent()) {
            return false;
        }
        news.setNewsContent(content);
        news.setUser(optionalUser.get());
        newsRepository.save(news);

        return true;
    }

    @Override
    public Optional<News> findById(Long id) {
        return newsRepository.findById(id);
    }

    @Override
    public Iterable<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }
}
