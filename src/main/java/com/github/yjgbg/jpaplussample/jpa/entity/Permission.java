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
public class Permission implements ActiveEntity<Permission> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User creator;
    @Version
    private Integer version;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles = new ArrayList<>();

    public boolean match(String permission) {
        return permission.startsWith(name);
    }
}
