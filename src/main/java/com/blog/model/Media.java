package com.blog.model;

public class Media {
    private Long id;
    private String path;
    private Long postId;

    // Default constructor
    public Media() {
    }

    // Constructor with parameters
    public Media(Long id, String path, Long postId) {
        this.id = id;
        this.path = path;
        this.postId = postId;
    }
    
    

    // Constructor without id (useful for inserting new records)
    public Media(String path, Long postId) {
        this.path = path;
        this.postId = postId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", postId=" + postId +
                '}';
    }
}
