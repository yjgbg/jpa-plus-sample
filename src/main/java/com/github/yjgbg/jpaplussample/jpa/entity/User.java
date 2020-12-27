package com.github.yjgbg.jpaplussample.jpa.entity;

import com.github.yjgbg.jpa.plus.entitySupport.ActiveEntity;
import com.github.yjgbg.jpaplussample.config.BeanHook;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Accessors(chain = true)
public class User implements ActiveEntity<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User creator;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();

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
