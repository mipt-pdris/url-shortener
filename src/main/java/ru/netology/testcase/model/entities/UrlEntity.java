package ru.netology.testcase.model.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "URL_TABLE",
        indexes = @Index(columnList = "ORIGINAL_URL", unique = true),
        uniqueConstraints = @UniqueConstraint(columnNames = {"ORIGINAL_URL"}))
public class UrlEntity {
    @Id
    @GeneratedValue
    @Getter
    private Long id;

    @Column(name = "ORIGINAL_URL")
    @Getter
    private String originalUrl;

    public UrlEntity(String originalUrl) {
        this.originalUrl = originalUrl;
    }


    public UrlEntity(Long id) {
        this.id = id;
    }
}
