package com.aquawebdev.auctor.service;

import com.aquawebdev.auctor.entity.News;

import java.util.Optional;

public interface NewsService {
    boolean addNews(String content, String login);
    Optional<News> findById(Long id);
    Iterable<News> findAll();
    void deleteById(Long id);
}
