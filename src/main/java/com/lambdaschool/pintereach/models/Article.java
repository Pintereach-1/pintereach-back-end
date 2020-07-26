package com.lambdaschool.pintereach.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "article")
public class Article
{
    @Id
    @GeneratedValue
    private long articleId;

    private String imageUrl;

    private String title;

    private String categoryId;

    private String description;

    public Article(String imageUrl, String title, String categoryId, String description) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.categoryId = categoryId;
        this.description = description;
    }

    public Article()
    {
    //default constructor
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
