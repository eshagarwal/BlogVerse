package org.gisma.blogverse.models;

import java.util.Objects;

// Embedded inside the blogs collection itself
public class Comment {

    private String author;
    private String text;

    public Comment(String author, String text) {
        this.author = author;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Comment comment)) return false;
        return Objects.equals(getAuthor(), comment.getAuthor()) && Objects.equals(getText(), comment.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthor(), getText());
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
}
