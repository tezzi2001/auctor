package com.aquawebdev.auctor.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_t")
@Data
public class User {
    @Id
    private String login;
}
