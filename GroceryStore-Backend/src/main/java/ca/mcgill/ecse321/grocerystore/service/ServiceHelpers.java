package ca.mcgill.ecse321.grocerystore.service;

import ca.mcgill.ecse321.grocerystore.model.Account;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


public class ServiceHelpers
{
    public static <T> List<T> toList(Iterable<T> iterable)
    {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable)
        {
            resultList.add(t);
        }
        return resultList;
    }

    public static void checkAccountInfoValidity(String email, String name, String password, String phone)
    {
        if (email == null || email.trim().length() == 0)
            throw new IllegalArgumentException("Please submit a valid email.");
        if (name == null || name.trim().length() == 0)
            throw new IllegalArgumentException("Please submit a valid name.");
        if (password == null || password.trim().length() == 0)
            throw new IllegalArgumentException("Please submit a valid password.");
        if (phone == null || phone.trim().length() == 0)
            throw new IllegalArgumentException("Please submit a valid phone.");
    }

    public static void checkAccountInfoValidity(Account user)
    {
        if (user == null) throw new IllegalArgumentException("Please submit a valid user object.");
        if (user.getEmail() == null || user.getEmail().trim().length() == 0)
            throw new IllegalArgumentException("Please submit a valid email.");
        if (user.getUsername() == null || user.getUsername().trim().length() == 0)
            throw new IllegalArgumentException("Please submit a valid name.");
        if (user.getPassword() == null || user.getPassword().trim().length() == 0)
            throw new IllegalArgumentException("Please submit a valid phone.");
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
    }
}