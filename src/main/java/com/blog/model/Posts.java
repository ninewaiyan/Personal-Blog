package com.blog.model;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class Posts {
    private Long id;
    private String title;
    private Timestamp createTime;
    private String content;
    private LocalDateTime delete_date;
    private List<Media> mediaList;
    private List<Comments>commentList;

    // Constructor
    public Posts() {
    }
    public String getTimeElapsed() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime postTime = createTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        Duration duration = Duration.between(postTime, now);

        long seconds = duration.getSeconds();

        if (seconds < 60) {
            return (seconds + 2) + " seconds ago";
        } else if (seconds < 3600) {
            return (seconds / 60) + " minutes ago";
        } else if (seconds < 86400) {
            return (seconds / 3600) + " hours ago";
        } else if (seconds < 2592000) {
            return (seconds / 86400) + " days ago";
        } else if (seconds < 31536000) {
            return (seconds / 2592000) + " months ago";
        } else {
            return (seconds / 31536000) + " years ago";
        }
    }

    public Posts(Long id, String title, String content, Timestamp time) {
        this.id = id;
        this.title = title;
        this.createTime = time;
        this.delete_date = null;
        this.content = content;
        
    }
    
    public Posts(String title, String content) {
        this.title = title;
        this.content = content;
        this.createTime = new Timestamp(System.currentTimeMillis()); // Set current time
    }
    
    public Posts(Long id,String title, String content) {
    	this.id = id;
        this.title = title;
        this.content = content;
        
    }

    
    public List<Comments> getCommentList() {
        return commentList;
    }
    
    public void setCommentList(List<Comments> commentList) {
        this.commentList= commentList;
    }

    
    public List<Media> getMediaList() {
        return mediaList;
    }
    
    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

    // Getters and Setters
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDeleteDate() {
        return delete_date;
    }

   
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    
}

