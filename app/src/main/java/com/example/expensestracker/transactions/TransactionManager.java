package com.example.expensestracker.transactions;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.expensestracker.budget_categories.BudgetCategory;
import com.example.expensestracker.budget_categories.BudgetCategoryManager;
import com.example.expensestracker.dbmanagment.BudgetCategoriesTable;
import com.example.expensestracker.dbmanagment.DatabaseController;
import com.example.expensestracker.dbmanagment.DatabaseManager;
import com.example.expensestracker.dbmanagment.TransactionsTable;
import com.example.expensestracker.globalOperations.DateStringFormatter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

// TODO get only the current month transaction
public class TransactionManager
{
    static ArrayList<Transaction> transactions;
    static DatabaseManager databaseManager;

    public  static boolean loadTransactions() throws ParseException {
        if(BudgetCategoryManager.getBudgetCategories() == null)
        {
            BudgetCategoryManager.LoadCategoryEnvelop();
        }

        databaseManager = DatabaseController.getDatabaseManager();

        transactions = new ArrayList<>();

        //Sat Oct 31 2020
        // %Oct%2020
        // from database

        String currentMonth = DateStringFormatter.getCurrentMonth();
        String currentYear = DateStringFormatter.getCurrentYear();
        String pattern = "%"+currentMonth+"%"+currentYear;

        String selection = TransactionsTable.colBuyDate + " LIKE ? ";
        String[] selectionArg = {pattern};

        Cursor cursor = databaseManager.queryTable(TransactionsTable.TableName
                ,null,selection,
                selectionArg,null,null,null);


        if(cursor.moveToFirst()) {
            do {
                int Id = cursor.getInt(cursor.getColumnIndex(TransactionsTable.colID));
                String productName = cursor.getString(cursor.getColumnIndex(TransactionsTable.colProductName));
                double price = cursor.getDouble(cursor.getColumnIndex(TransactionsTable.colPrice));
                String buyDate = cursor.getString(cursor.getColumnIndex(TransactionsTable.colBuyDate));
                int colBudgetID = cursor.getInt(cursor.getColumnIndex(TransactionsTable.colBudgetID));

                DateStringFormatter.StringToDate(buyDate);

                transactions.add(new Transaction(colBudgetID , productName , price,DateStringFormatter.StringToDate(buyDate)));

            } while (cursor.moveToNext());
        }

        return true;

    }

    public static ArrayList<Transaction> getAllTransactions()
    {
        return transactions;
    }

    public static ArrayList<Transaction> getTransactionsByCategoryID(int categoryID)
    {
        ArrayList<Transaction> output = new ArrayList<>();
        for(int i = 0 ; i < transactions.size() ; i++)
        {
            int ID =  transactions.get(i).getBudgetCategoryID();
            if (ID == (categoryID))
            {
                output.add(transactions.get(i));
            }
        }
        return output;
    }

    public static boolean addNewTransaction(Transaction transaction)
    {

        ContentValues contentValues = new ContentValues();

        contentValues.put(TransactionsTable.colBudgetID,transaction.getBudgetCategoryID());
        contentValues.put(TransactionsTable.colBuyDate,DateStringFormatter.DateToString(transaction.getBuyDate()));
        contentValues.put(TransactionsTable.colPrice,transaction.getPrice());
        contentValues.put(TransactionsTable.colProductName,transaction.getProductName());

        long ID = databaseManager.insert(TransactionsTable.TableName, contentValues);

        if(ID > 0) {

            String buyMonth = (DateStringFormatter.DateToString(transaction.getBuyDate())).substring(4,7);
            String currentMonth = DateStringFormatter.getCurrentMonth();

            if (currentMonth.equalsIgnoreCase(buyMonth)) {
                BudgetCategoryManager.addToCurrentExpenses(transaction.getBudgetCategoryID(), transaction.getPrice());
                transactions.add(transaction);
            }
            return true;
        }
        else {
            return false;
        }


    }

}
