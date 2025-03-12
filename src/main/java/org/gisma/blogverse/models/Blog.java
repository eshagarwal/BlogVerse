package org.gisma.blogverse.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "blogs") // Maps this model to the "blogs" collection in MongoDB
public class Blog {

    @Id
    private String id;
    private String title;
    private String content;
    private String author;
    private String category;

    @CreatedDate
    private LocalDateTime createdAt;
    private List<Comment> comments = new ArrayList<>();

    // Constructors
    public Blog() {}

    public Blog(String title, String content, String author, String category) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Blog blog)) return false;
        return Objects.equals(getId(), blog.getId()) && Objects.equals(getTitle(), blog.getTitle()) && Objects.equals(getContent(), blog.getContent()) && Objects.equals(getAuthor(), blog.getAuthor()) && Objects.equals(getCategory(), blog.getCategory()) && Objects.equals(getCreatedAt(), blog.getCreatedAt()) && Objects.equals(getComments(), blog.getComments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getContent(), getAuthor(), getCategory(), getCreatedAt(), getComments());
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<Comment> getComments() { return comments; }
    public void setComments(List<Comment> comments) { this.comments = comments; }
}
