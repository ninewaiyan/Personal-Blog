package com.blog.model;

public class Comments {
    private Long id;
    private String comment;
    private String userName;
    private Long postId;

    // Default constructor
    public Comments() {
    }

    // Constructor with parameters
    public Comments(Long id, String comment, String userName, Long postId) {
        this.id = id;
        this.comment = comment;
        this.userName = userName;
        this.postId = postId;
    }

    // Constructor without id (useful for inserting new records)
    public Comments(String comment, String userName, Long postId) {
        this.comment = comment;
        this.userName = userName;
        this.postId = postId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", userName='" + userName + '\'' +
                ", postId=" + postId +
                '}';
    }
}
