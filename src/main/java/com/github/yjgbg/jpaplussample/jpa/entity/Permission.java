package com.github.yjgbg.jpaplussample.jpa.entity;

import com.github.yjgbg.jpa.plus.entitySupport.ActiveEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
public class Permission implements ActiveEntity<Permission> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    private User creator;

    public boolean match(String permission) {
        return permission.startsWith(name);
    }
}
