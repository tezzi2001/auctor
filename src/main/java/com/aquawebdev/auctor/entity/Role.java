package com.aquawebdev.auctor.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "roles")
@Data
@EqualsAndHashCode(of = "roleId")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long roleId;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(Long roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}