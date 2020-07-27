package com.lambdaschool.pintereach.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "article")
public class Article
{
    @Id
    @GeneratedValue
    private long articleid;

    private String Url;

    private String title;

    @ManyToOne
    @JoinColumn(name = "categoryid",
            nullable = false)
    @JsonIgnoreProperties(value = "articles", allowSetters = true)
    private Category category;

    private String description;

    public Article(String Url, String title, Category category, String description) {
        this.Url = Url;
        this.title = title;
        this.category = category;
        this.description = description;
    }

    public Article()
    {
    //default constructor
    }

    public long getArticleid() {
        return articleid;
    }

    public void setArticleid(long articleid) {
        this.articleid = articleid;
    }

    public String getImageUrl() {
        return Url;
    }

    public void setImageUrl(String Url) {
        this.Url = Url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
