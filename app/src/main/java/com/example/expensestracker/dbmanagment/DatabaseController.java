package com.example.expensestracker.dbmanagment;

import android.content.ContentValues;
import android.content.Context;

import com.example.expensestracker.budget_categories.BudgetCategoryManager;
import com.example.expensestracker.globalOperations.DateStringFormatter;
import com.example.expensestracker.mainactivity.fragments.home.HomeFragment;


// TODO Change the current budget every month

public class DatabaseController
{
    static private DatabaseManager databaseManager;

    public DatabaseController(Context context)
    {
        if(databaseManager == null)
        {
            databaseManager = new DatabaseManager(context);
        }
    }


    public static DatabaseManager getDatabaseManager() {

        return databaseManager;
    }


}
