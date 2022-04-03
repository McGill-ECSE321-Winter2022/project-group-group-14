package ca.mcgill.ecse321.grocerystore.dto;

import java.util.List;
import java.util.ArrayList;

/**
 * @author clarissabaciu
 */

public class GroceryOrderDto {
    /**
     * design decisions based on tutorial: 
     * - Dto objects used when referencing associations
     * - String used instead of enumeration type from model classes
     * */ 
	
	private int orderId;
    private int totalCost;
    private String orderType;
    private String orderStatus;
    private List<OrderItemDto> orderItems;
    private CustomerDto customer;

    public GroceryOrderDto(){   //empty constructor
    }

    
    public GroceryOrderDto(int orderId, int totalCost, String orderType, String orderStatus){   
    	this.orderId = orderId;
    	this.totalCost = totalCost;
        this.orderType = orderType;
        this.orderItems = new ArrayList<OrderItemDto>();
        this.customer = null;
        this.orderStatus = orderStatus;
    }
    
    public GroceryOrderDto(int orderId, int totalCost, String orderType, String orderStatus, CustomerDto customer){   
    	this.orderId = orderId;
    	this.totalCost = totalCost;
        this.orderType = orderType;
        this.orderItems =  new ArrayList<OrderItemDto>();
        this.customer = customer;
        this.orderStatus = orderStatus;
    }
    
    public GroceryOrderDto(int orderId, int totalCost, String orderType, String orderStatus, List<OrderItemDto> orderItems, CustomerDto customer){   
    	this.orderId = orderId;
    	this.totalCost = totalCost;
        this.orderType = orderType;
        this.orderItems = orderItems;
        this.customer = customer;
        this.orderStatus = orderStatus;
    }
    public GroceryOrderDto(int orderId, int totalCost, String orderType, String orderStatus, List<OrderItemDto> orderItems){   
    	this.orderId = orderId;
    	this.totalCost = totalCost;
        this.orderType = orderType;
        this.orderItems = orderItems;
        this.customer = null;
        this.orderStatus = orderStatus;
    }

    public int getTotalCost(){
        return this.totalCost;
    }

    public String getOrderType(){
        return this.orderType;
    }
    
    public String getOrderStatus(){
        return this.orderStatus;
    }

    public List<OrderItemDto> getOrderItems(){
        return this.orderItems;
    }

    public CustomerDto getCustomer(){
        return this.customer;
    }
    
    public int getOrderId() {
    	return orderId;
    } 
}
