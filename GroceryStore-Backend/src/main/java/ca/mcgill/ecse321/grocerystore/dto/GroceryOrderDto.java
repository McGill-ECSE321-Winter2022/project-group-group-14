package ca.mcgill.ecse321.grocerystore.dto;

import java.util.List;

/**
 * @author clarissabaciu
 */

public class GroceryOrderDto {
    /**
     * design decisions based on tutorial: 
     * - orderId not included
     * - Dto objects used when referencing associations
     * - String used instead of enumeration type from model classes
     * */ 

    private int totalCost;
    private String orderType;
    private List<OrderItemDto> orderItems;
    private CustomerDto customer;

    public GroceryOrderDto(){   //empty constructor
    }

    public GroceryOrderDto(int totalCost, String orderType, List<OrderItemDto> orderItems, CustomerDto customer){   
        this.totalCost = totalCost;
        this.orderType = orderType;
        this.orderItems = orderItems;
        this.customer = customer;
    }

    public int getTotalCost(){
        return this.totalCost;
    }

    public String getOrderType(){
        return this.orderType;
    }

    public List<OrderItemDto> getOrderItems(){
        return this.orderItems;
    }

    public CustomerDto getCustomer(){
        return this.customer;
    }
    
}
