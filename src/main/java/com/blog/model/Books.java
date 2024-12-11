
package com.blog.model;

public class Books {
	
	Long  id ;
	String name;
	String image;
	String bookfile;
	String description;
	
	
	
	public Books(Long id, String name, String image, String bookfile,String description) {
		this.id = id;
		this.name = name;
		this.image = image;
		this.bookfile = bookfile;
		this.description = description;
	}
	
	public Books( String name, String image, String bookfile,String description) {
		this.name = name;
		this.image = image;
		this.bookfile = bookfile;
		this.description = description;
	}
	
	public Books(Long id, String name,String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getBookfile() {
		return bookfile;
	}
	public void setBookfile(String bookfile) {
		this.bookfile = bookfile;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
