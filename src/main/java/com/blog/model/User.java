package com.blog.model;

public class User {
    private long userId;        // Corresponds to user_id in the database
    private String firstname;
    private String lastname;
    private String username;    // Corresponds to username in the database
    private String email;       // Corresponds to email in the database
    private String password;    // Corresponds to password in the database
    private String role;        // Corresponds to role in the database ('owner' or 'user')
    private boolean enable;
    // No-argument constructor
    public User() {}
    
    

	public User(long userId, String firstname, String lastname, String username, String email, String password,
			String role,boolean enable) {
		super();
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.enable = enable;
	}
	
	



	public User(String firstname, String lastname, String username, String email, String password, String role,boolean enable) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.enable = enable;
	}



	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}



	public boolean isEnable() {
		return enable;
	}



	public void setEnable(boolean enable) {
		this.enable = enable;
	}

   
}

