package com.blog.model;

public class Videos {
	
	Long id;
	String title;
	String video;
	Long course_id;
	

	public Videos(Long id, String title, String video, Long course_id) {
		super();
		this.id = id;
		this.title = title;
		this.video = video;
		this.course_id = course_id;
	}
	
	public Videos( String title, String video, Long course_id) {
		this.title = title;
		this.video = video;
		this.course_id = course_id;
	}
	
	
	public Videos(Long id, String title) {
		super();
		this.id = id;
		this.title = title;
		
	}
	
	public Videos(Long id, String title,String video) {
		super();
		this.id = id;
		this.title = title;
		this.video = video;
		
	}
	
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
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public Long getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}
	
	

}
