package ca.mcgill.ecse321.grocerystore.dto;

public class OwnerDto {
	
	private String username;
	private String password;
	private String email;
	private int accountId;
	
	public OwnerDto() {
		
	}
	
	public OwnerDto(String username, String password, String email, int accountId) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.accountId = accountId;
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
	
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
}