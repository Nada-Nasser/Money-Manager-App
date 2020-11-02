package com.example.expensestracker.dbmanagment;

public class MonthlyExpensesTable
{
    public static final String TableName = "monthlyExpenses";


    static final String DropMonthlyExpensesTable  = "DROP TABLE IF EXISTS " + TableName;

    public  static final String colYear = "year";
    public  static final String colMonth = "month";
    public  static final String colExpenses = "expenses";

    static final String createMonthlyExpensesTableSQLQuery =  "CREATE TABLE IF NOT EXISTS " + TableName + "(\n " +
            colYear +" varchar(10),\n" +
            colMonth  +" varchar(3),\n" +
            colExpenses  +" double,\n" +
            "PRIMARY KEY ("+colMonth +"," +colYear+")"+"\n"+
            " ); " ;
}
