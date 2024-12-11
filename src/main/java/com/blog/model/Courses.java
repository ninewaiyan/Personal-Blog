package com.blog.model;

public class Courses  {
	
	Long id;
	String name;
	String image;
	
	
	public Courses(Long id,String name, String image) {
		
		this.id = id;
		this.name = name;
		this.image = image;
	}
	
	public Courses(Long id,String name) {
		this.id= id;
		this.name = name;
		
	}
	
	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Courses(String name, String image) {
		super();
		this.name = name;
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
