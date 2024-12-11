package com.blog.model;

import java.sql.Timestamp;

public class ShopItems {
    private long id;
    private String name;
    private String description;
    private String image;
    private String price;
    private Timestamp deleateDate;

    // Constructors
    public ShopItems() {}

    public ShopItems(long id, String name,String price, String description ,String image ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        
    }
    
    public ShopItems( Long id,String name,String price, String description) {
    	
    	this.id = id;
        this.name= name;
        this.price = price;
        this.description = description;
    }
    
    public ShopItems( String name,String price, String description,String image) {
        
        this.name= name;
        this.price = price;
        this.image = image;
        this.description = description;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setTitle(String title) {
        this.name = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

    
   
}
