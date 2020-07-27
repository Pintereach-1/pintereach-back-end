package com.lambdaschool.pintereach.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long categoryid;

    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "userid",
            nullable = false)
    @JsonIgnoreProperties(value = "categories", allowSetters = true)
    private User user;

    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties("category")
    private Set<Article> articles = new HashSet<>();

    public Category()
    {
        //default constructor
    }

    public Category(String categoryName, User user) {
        this.categoryName = categoryName;
        this.user = user;

    }

    public long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(long categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }
}
