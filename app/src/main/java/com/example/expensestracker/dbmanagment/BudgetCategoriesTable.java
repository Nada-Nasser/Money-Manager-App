package com.example.expensestracker.dbmanagment;

public class BudgetCategoriesTable
{

    public static final String TableName = "budget_categories";

    public static final String DropBudgetCategoriesTable = "DROP TABLE IF EXISTS " + TableName;


    public static final String colID = "colID";
    public  static final String colName = "name";
    public static final String colMaxBudget = "maxBudget";
    public static final String colCurrentBudget = "currentBudget";

    public static final String createBudgetCategoryTableSQLQuery =  "CREATE TABLE IF NOT EXISTS " + TableName + "(\n " +
            "colID Integer Primary key Autoincrement ,\n" +
            colName +" varchar(255),\n" +
            colMaxBudget  +" double,\n" +
            colCurrentBudget  +" double\n" +
            " ); " ;

}
