package com.aquawebdev.auctor.repository;

import com.aquawebdev.auctor.entity.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends CrudRepository<News,Long> {
}
