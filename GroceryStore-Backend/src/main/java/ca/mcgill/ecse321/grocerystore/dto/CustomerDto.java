package ca.mcgill.ecse321.grocerystore.dto;

public class CustomerDto {
	
	private String username;
	private String password;
	private String email;
	private int accountId;
	private String phoneNumber;
	private String address;
	
	public CustomerDto() {
		
	}
	
	public CustomerDto(String username, String password, String email, int accountId, String phoneNumber, String address) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.accountId = accountId;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getAddress() {
		return address;
	}
	
	public int getAccountId() {
		return accountId;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
}