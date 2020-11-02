package com.example.expensestracker.monthestracker;

public class MonthExpenses
{
    private String month;
    private String year;
    private double expenses;

    public MonthExpenses(String month, String year, double expenses) {
        this.month = month;
        this.year = year;
        this.expenses = expenses;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }
}
