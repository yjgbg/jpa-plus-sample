package com.github.yjgbg.jpaplussample.jpa.entity;

import com.github.yjgbg.jpa.plus.entitySupport.ActiveEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Accessors
public class User implements ActiveEntity<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Version
    private Integer version;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User creator;

    @ManyToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Role> roles = new ArrayList<>();

    public boolean hasPermission(String permission) {
        return roles.stream()
                .map(Role::getPermissions)
                .flatMap(Collection::stream)
                .anyMatch(x -> x.match(permission));
    }

    public User addRole(Role role) {
        roles.add(role);
        return this;
    }
}
