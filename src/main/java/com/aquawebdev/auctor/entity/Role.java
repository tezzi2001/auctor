package com.aquawebdev.auctor.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "roles")
@Data
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long roleId;

    @Column
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<User> users;

    public Role(Long roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }

}