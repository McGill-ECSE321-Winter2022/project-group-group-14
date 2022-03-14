package ca.mcgill.ecse321.grocerystore.dto;

public class InventoryItemDto {
	
	private String name;
	private int price;
	private int currentStock;
	private int itemId;
	
	public InventoryItemDto() {
		
	}
	
	public InventoryItemDto(String name, int price, int currentStock, int itemId) {
		this.name = name;
		this.price = price;
		this.currentStock = currentStock;
		this.itemId = itemId;
	}
	
	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getCurrentStock() {
		return currentStock;
	}

	public int getItemId() {
		return itemId;
	}
	
}