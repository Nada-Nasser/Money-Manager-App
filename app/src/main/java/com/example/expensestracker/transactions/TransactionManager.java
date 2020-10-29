package com.example.expensestracker.transactions;

import com.example.expensestracker.budget_categories.BudgetCategoryManager;

import java.util.ArrayList;
import java.util.Calendar;

// TODO Deal with local database
public class TransactionManager
{
    static ArrayList<Transaction> transactions;

    public  static boolean loadTransactions()
    {
        if(BudgetCategoryManager.getBudgetCategories() == null)
        {
            BudgetCategoryManager.LoadCategoryEnvelop();
        }

        Calendar calendar = Calendar.getInstance();
        transactions = new ArrayList<>();
        transactions.add(new Transaction(BudgetCategoryManager.getBudgetCategory(1),"Product" , 120.0 , calendar.getTime()));
        transactions.add(new Transaction(BudgetCategoryManager.getBudgetCategory(2),"Product" , 100.0 , calendar.getTime()));
        transactions.add(new Transaction(BudgetCategoryManager.getBudgetCategory(3),"Product" , 50.0 , calendar.getTime()));
        transactions.add(new Transaction(BudgetCategoryManager.getBudgetCategory(2),"Product" , 10.0 , calendar.getTime()));
        transactions.add(new Transaction(BudgetCategoryManager.getBudgetCategory(3),"Product" , 100.0 , calendar.getTime()));
        transactions.add(new Transaction(BudgetCategoryManager.getBudgetCategory(3),"Product" , 200.0 , calendar.getTime()));
        transactions.add(new Transaction(BudgetCategoryManager.getBudgetCategory(0),"Product" , 10.0 , calendar.getTime()));
        transactions.add(new Transaction(BudgetCategoryManager.getBudgetCategory(0),"Product" , 80.0 , calendar.getTime()));
        transactions.add(new Transaction(BudgetCategoryManager.getBudgetCategory(0),"Product" , 22.5 , calendar.getTime()));
        transactions.add(new Transaction(BudgetCategoryManager.getBudgetCategory(1),"Product" , 120.0 , calendar.getTime()));
        transactions.add(new Transaction(BudgetCategoryManager.getBudgetCategory(2),"Product" , 100.0 , calendar.getTime()));
        transactions.add(new Transaction(BudgetCategoryManager.getBudgetCategory(3),"Product" , 50.0 , calendar.getTime()));
        transactions.add(new Transaction(BudgetCategoryManager.getBudgetCategory(2),"Product" , 10.0 , calendar.getTime()));
        transactions.add(new Transaction(BudgetCategoryManager.getBudgetCategory(3),"Product" , 100.0 , calendar.getTime()));
        transactions.add(new Transaction(BudgetCategoryManager.getBudgetCategory(3),"Product" , 200.0 , calendar.getTime()));
        transactions.add(new Transaction(BudgetCategoryManager.getBudgetCategory(0),"Product" , 10.0 , calendar.getTime()));
        transactions.add(new Transaction(BudgetCategoryManager.getBudgetCategory(0),"Product" , 80.0 , calendar.getTime()));
        transactions.add(new Transaction(BudgetCategoryManager.getBudgetCategory(0),"Product" , 22.5 , calendar.getTime()));
        transactions.add(new Transaction(BudgetCategoryManager.getBudgetCategory(1),"Product" , 120.0 , calendar.getTime()));
        transactions.add(new Transaction(BudgetCategoryManager.getBudgetCategory(2),"Product" , 100.0 , calendar.getTime()));
        transactions.add(new Transaction(BudgetCategoryManager.getBudgetCategory(3),"Product" , 50.0 , calendar.getTime()));
        transactions.add(new Transaction(BudgetCategoryManager.getBudgetCategory(2),"Product" , 10.0 , calendar.getTime()));
        transactions.add(new Transaction(BudgetCategoryManager.getBudgetCategory(3),"Product" , 100.0 , calendar.getTime()));
        transactions.add(new Transaction(BudgetCategoryManager.getBudgetCategory(3),"Product" , 200.0 , calendar.getTime()));
        transactions.add(new Transaction(BudgetCategoryManager.getBudgetCategory(0),"Product" , 10.0 , calendar.getTime()));
        transactions.add(new Transaction(BudgetCategoryManager.getBudgetCategory(0),"Product" , 80.0 , calendar.getTime()));
        transactions.add(new Transaction(BudgetCategoryManager.getBudgetCategory(0),"Product" , 22.5 , calendar.getTime()));

        return true;
    }

    public static ArrayList<Transaction> getAllTransactions()
    {
        return transactions;
    }

    public static ArrayList<Transaction> getTransactionsByCategory(String category)
    {
        ArrayList<Transaction> output = new ArrayList<>();
        for(int i = 0 ; i < transactions.size() ; i++)
        {
            String categoryName =  transactions.get(i).getBudgetCategory().getName();
            if (categoryName.equalsIgnoreCase(category))
            {
                output.add(transactions.get(i));
            }
        }
        return output;
    }

    public static boolean addNewTransaction(Transaction transaction)
    {
        try {
            BudgetCategoryManager.addToCurrentExpenses(transaction.getBudgetCategory().getName(), transaction.getPrice());
            transactions.add(transaction);

            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

}
