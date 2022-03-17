package ca.mcgill.ecse321.grocerystore.dto;

public class OrderItemDto {
	
	private String name;
	private int price;
	private int itemId;
	
	public OrderItemDto() {
		
	}
	
	public OrderItemDto(String name, int price, int itemId) {
		this.name = name;
		this.price = price;
		this.itemId = itemId;
	}
	
	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}


	public int getItemId() {
		return itemId;
	}
	
}