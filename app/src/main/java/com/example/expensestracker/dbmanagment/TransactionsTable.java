package com.example.expensestracker.dbmanagment;

public class TransactionsTable
{
    public static final String TableName = "transactions";
/*
    static final String createTransactionTableSQLQuery =
            "    CREATE TABLE IF NOT EXISTS `transactions` \n" +
                    "    ( id INT NOT NULL Autoincrement ,\n" +
                    "     productName VARCHAR(100) NOT NULL ,\n" +
                    "      price DOUBLE NOT NULL ,\n" +
                    "       buyDate VARCHAR(100) NOT NULL ,\n" +
                    "        budgetID INT(100) NOT NULL ,\n" +
                    "         PRIMARY KEY (id)," +
                    "CONSTRAINT FK FOREIGN KEY (budgetID) REFERENCES budget_categories(id)" +
                    ");";*/

    static final String DropTransactionsTable = "DROP TABLE IF EXISTS " + TableName;


    public  static final String colID = "colID";
    public  static final String colProductName = "productName";
    public  static final String colPrice = "price";
    public  static final String colBuyDate = "buyDate";
    public  static final String colBudgetID = "budgetID";

    static final String createTransactionTableSQLQuery
            =  "CREATE TABLE IF NOT EXISTS " + TableName + "(\n " +
            "colID Integer Primary key Autoincrement ,\n" +
            colProductName +" varchar(255),\n" +
            colPrice  +" double,\n" +
            colBuyDate  +" varchar(255),\n" +
            colBudgetID  +" Integer,\n" +
            "CONSTRAINT FK FOREIGN KEY (budgetID) REFERENCES budget_categories(colID)"+
            " ); " ;

    // "CONSTRAINT FK FOREIGN KEY (budgetID) REFERENCES budget_categories(id)"

}
