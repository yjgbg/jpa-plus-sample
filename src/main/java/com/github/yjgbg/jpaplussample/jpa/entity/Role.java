package com.github.yjgbg.jpaplussample.jpa.entity;

import com.github.yjgbg.jpa.plus.entitySupport.ActiveEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Role implements ActiveEntity<Role> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User creator;
    @ManyToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Permission> permissions = new ArrayList<>();

    public Role addPermission(Permission permission) {
        permissions.add(permission);
        return this;
    }
}
