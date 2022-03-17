package ca.mcgill.ecse321.grocerystore.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.grocerystore.model.Account;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.EmployeeSchedule.Shift;
import ca.mcgill.ecse321.grocerystore.model.InventoryItem;
import ca.mcgill.ecse321.grocerystore.model.StoreSchedule.Day;


public class ServiceHelpers
{
	/** @author Samuel Valentine */
    public static <T> List<T> toList(Iterable<T> iterable)
    {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable)
        {
            resultList.add(t);
        }
        return resultList;
    }
    
    /** @author Samuel Valentine */
    public static void checkAccountInfoValidity(String email, String username, String password)
    {
        if (email == null || email.trim().length() == 0)
            throw new IllegalArgumentException("Please submit a valid email.");
        if (username == null || username.trim().length() == 0)
            throw new IllegalArgumentException("Please submit a valid username.");
        if (password == null || password.trim().length() == 0)
            throw new IllegalArgumentException("Please submit a valid password.");
    }

    /** @author Samuel Valentine */
    public static void checkAccountInfoValidity(Account user)
    {
        if (user == null) throw new IllegalArgumentException("Please submit a valid user object.");
        if (user.getEmail() == null || user.getEmail().trim().length() == 0)
            throw new IllegalArgumentException("Please submit a valid email.");
        if (user.getUsername() == null || user.getUsername().trim().length() == 0)
            throw new IllegalArgumentException("Please submit a valid name.");
        if (user.getPassword() == null || user.getPassword().trim().length() == 0)
            throw new IllegalArgumentException("Please submit a valid password.");
    }
    
    /** @author Samuel Valentine */
    public static void checkCustomerInfoValidity(String email, String username, String password, String phoneNumber, String address)
    {
        if (email == null || email.trim().length() == 0)
            throw new IllegalArgumentException("Please submit a valid email.");
        if (username == null || username.trim().length() == 0)
            throw new IllegalArgumentException("Please submit a valid username.");
        if (password == null || password.trim().length() == 0)
            throw new IllegalArgumentException("Please submit a valid password.");
        if (phoneNumber == null || phoneNumber.trim().length() == 0)
        	throw new IllegalArgumentException("Please submit a valid phone number.");
        if (address == null || address.trim().length() == 0)
        	throw new IllegalArgumentException("Please submit a valid address.");
    }
    
    /** @author Samuel Valentine */
    public static void checkCustomerInfoValidity(Customer customer)
    {
        if (customer == null) throw new IllegalArgumentException("Please submit a valid customer object.");
        if (customer.getEmail() == null || customer.getEmail().trim().length() == 0)
            throw new IllegalArgumentException("Please submit a valid email.");
        if (customer.getUsername() == null || customer.getUsername().trim().length() == 0)
            throw new IllegalArgumentException("Please submit a valid username.");
        if (customer.getPassword() == null || customer.getPassword().trim().length() == 0)
            throw new IllegalArgumentException("Please submit a valid password.");
        if (customer.getPhoneNumber() == null || customer.getPhoneNumber().trim().length() == 0)
        	throw new IllegalArgumentException("Please submit a valid phone number.");
        if (customer.getAddress() == null || customer.getAddress().trim().length() == 0)
        	throw new IllegalArgumentException("Please submit a valid address.");
    }
    
    public static void checkItemInfoValidity(String name, int price, int currentStock)
    {
        if (name == null || name.trim().length() == 0)
            throw new IllegalArgumentException("Please submit a valid name.");
        if (price < 0)
            throw new IllegalArgumentException("Please submit a valid price.");
        if (currentStock < 0)
            throw new IllegalArgumentException("Please submit a valid stock number.");
    }
    
    public static void checkItemInfoValidity(InventoryItem inventoryItem)
    {
        if (inventoryItem == null) throw new IllegalArgumentException("Please submit a valid inventory item object.");
        if (inventoryItem.getName() == null || inventoryItem.getName().trim().length() == 0)
            throw new IllegalArgumentException("Please submit a valid name.");
        if (inventoryItem.getPrice() < 0)
            throw new IllegalArgumentException("Please submit a valid price.");
        if (inventoryItem.getCurrentStock() < 0)
            throw new IllegalArgumentException("Please submit a valid stock number.");
    }

    public static void checkDateValidity(Date startDate, Date endDate)
    {
        if (startDate == null || endDate == null)
            throw new IllegalArgumentException("Please input a valid start and end date.");
        if (startDate.after(endDate))
            throw new IllegalArgumentException("Your start date cannot be after your end date.");
    }

    public static void checkTimeValidity(Time startTime, Time endTime)
    {
        if (startTime == null || endTime == null)
            throw new IllegalArgumentException("Please input a valid start and end time.");
        if (startTime.after(endTime))
            throw new IllegalArgumentException("Your start time cannot be after your end time.");
        if (startTime.equals(endTime))
            throw new IllegalArgumentException("Your start time cannot be the same as your end time.");
    }
    
    public static void checkDayValidity(Day day)
    {
        if (day == null)
            throw new IllegalArgumentException("Please input a valid day.");
        
        boolean isValidDay = false;
        
        for (Day listedDay : Day.values()) {
            if (listedDay.equals(day)) {
                isValidDay = true;
                break;
            }
        }
        if (!isValidDay)
            throw new IllegalArgumentException("Please input a day of the week");
    }
    
    public static void checkShiftValidity(Shift shift)
    {
        if (shift == null)
            throw new IllegalArgumentException("Please input a valid shift.");
        
        boolean isValidShift = false;
        
        for (Shift listedShift : Shift.values()) {
            if (listedShift.equals(shift)) {
                isValidShift = true;
                break;
            }
        }
        if (!isValidShift)
            throw new IllegalArgumentException("Please input a valid shift");
    }
    
    
}