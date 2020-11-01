package com.example.expensestracker.globalOperations;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateStringFormatter
{

    public static String DateToString(Date date)
    {
        String year = (date.toString()).substring(
                date.toString().length()-4);

        String dateStr = (date.toString()).substring(0 , 10) + " " + year;

        return dateStr;
    }

    //Sat Oct 31 2020
    // format = "E MMM dd yyyy"
    public static Date StringToDate(String dateStr) throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd yyyy");
        Date date = formatter.parse(dateStr);

        return date;
    }

    public static String getCurrentMonth()
    {
        String dateStr = DateToString(Calendar.getInstance().getTime());
        return  dateStr.substring(4,7);
    }

    public static String getCurrentYear()
    {
        String dateStr = DateToString(Calendar.getInstance().getTime());
        return  dateStr.substring(dateStr.length()-4);
    }

    public static String getCurrentDate()
    {
        return DateToString(Calendar.getInstance().getTime());
    }

}
