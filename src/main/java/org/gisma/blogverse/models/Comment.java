package org.gisma.blogverse.models;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Objects;

public class Comment {

    @Id
    private String id; // Unique ID for the comment
    private String author;
    private String text;
    private LocalDateTime createdAt = LocalDateTime.now();

    // Constructors
    public Comment(String author, String text) {
        this.author = author;
        this.text = text;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Comment comment)) return false;
        return Objects.equals(getId(), comment.getId()) && Objects.equals(getAuthor(), comment.getAuthor()) && Objects.equals(getText(), comment.getText()) && Objects.equals(getCreatedAt(), comment.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthor(), getText(), getCreatedAt());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
