package com.example.expensestracker.monthestracker;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.expensestracker.R;
import com.example.expensestracker.budget_categories.BudgetCategoryManager;
import com.example.expensestracker.dbmanagment.BudgetCategoriesTable;
import com.example.expensestracker.dbmanagment.DatabaseController;
import com.example.expensestracker.dbmanagment.DatabaseManager;
import com.example.expensestracker.globalOperations.DateStringFormatter;
import com.example.expensestracker.mainactivity.fragments.home.HomeFragment;

public class MonthsTracker
{
    static private DatabaseManager databaseManager;
    static Context myContext;
    private static SharedPreferences sharedPreferences;

    static public void checkUpdates(Context context)
    {
        databaseManager = DatabaseController.getDatabaseManager();
        myContext = context;
        sharedPreferences = context.getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);
        String lastUpdatedMonth  = sharedPreferences.getString("lastUpdate", DateStringFormatter.getCurrentMonth());

        String currentMonth = DateStringFormatter.getCurrentMonth();

        if (!currentMonth.equalsIgnoreCase(lastUpdatedMonth))
            setAllBudgetCategoriesExpensesToZero();
    }


    private static void setAllBudgetCategoriesExpensesToZero()
    {
        ContentValues contentValues = new ContentValues();

        contentValues.put(BudgetCategoriesTable.colCurrentBudget,0);
        int c = databaseManager.updateEntries(BudgetCategoriesTable.TableName , contentValues,null,null);
        if(c > 0)
        {
            //saveLastUpdatedMonth();
            BudgetCategoryManager.setAllBudgetCategoriesExpensesToZero();
            HomeFragment.refresh();
        }
    }

    private static void saveLastUpdatedMonth() // set it to current
    {
        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("lastUpdate", DateStringFormatter.getCurrentMonth());

            editor.commit();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
