package com.github.yjgbg.jpaplussample.jpa.entity;

import com.github.yjgbg.jpa.plus.entitySupport.ActiveEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Role implements ActiveEntity<Role> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Version
    private Integer version;
    @OneToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User creator;
    @ManyToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Permission> permissions = new ArrayList<>();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();

    public Role addPermission(Permission permission) {
        permissions.add(permission);
        return this;
    }
}
