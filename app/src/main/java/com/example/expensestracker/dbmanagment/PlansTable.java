package com.example.expensestracker.dbmanagment;

public class PlansTable
{
    public static final String TableName = "plans";


    static final String DropPlansTable = "DROP TABLE IF EXISTS " + TableName;

    public  static final String colID = "colID";
    public  static final String colTitle = "title";
    public  static final String colDescription = "description";
    public  static final String colNeededMoney = "neededMoney";
    public  static final String colDeadline = "deadline";

    static final String createPlansTableSQLQuery =  "CREATE TABLE IF NOT EXISTS " + TableName + "(\n " +
            "colID Integer Primary key Autoincrement ,\n" +
            colTitle +" varchar(255),\n" +
            colDescription  +" varchar(255),\n" +
            colNeededMoney  +" double,\n" +
            colDeadline  +" varchar(255)\n" +
            " ); " ;
}
