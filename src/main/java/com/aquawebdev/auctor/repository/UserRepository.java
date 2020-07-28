package com.aquawebdev.auctor.repository;

import com.aquawebdev.auctor.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
