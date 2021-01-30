package com.alodiga.hsm.data.object;

public class User {
	
	
	private Long id;
	private String login;
	private String password;
	private String documentNumber;
	private String firstName;
	private String lastName;
	private String creationDate;
	private String email;
	private String phoneNumber;
	private String enabled;
	

	public User(Long id, String login, String password, String documentNumber, String firstName, String lastName,
			String creationDate, String email, String phoneNumber, String enabled) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.documentNumber = documentNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.creationDate = creationDate;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.enabled = enabled;
	}
	
	public User() {

	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	

}
