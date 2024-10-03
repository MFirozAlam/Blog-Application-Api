package com.blog.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Post {

    @jakarta.persistence.Id // Use the correct @Id annotation from JPA, not Spring's @Id.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    // Assuming Comment class is defined somewhere in the same package
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true) // Mapped by the variable name in Comment.
    private List<Comment> comments;

    // Default constructor
    public Post(Long postId) {
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
