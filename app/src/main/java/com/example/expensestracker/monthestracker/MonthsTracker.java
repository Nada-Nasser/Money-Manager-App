package com.example.expensestracker.monthestracker;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;

import com.example.expensestracker.R;
import com.example.expensestracker.budget_categories.BudgetCategoryManager;
import com.example.expensestracker.dbmanagment.BudgetCategoriesTable;
import com.example.expensestracker.dbmanagment.DatabaseController;
import com.example.expensestracker.dbmanagment.DatabaseManager;
import com.example.expensestracker.dbmanagment.MonthlyExpensesTable;
import com.example.expensestracker.globalOperations.DateStringFormatter;
import com.example.expensestracker.mainactivity.fragments.home.HomeFragment;

import java.util.ArrayList;
import java.util.HashMap;

public class MonthsTracker
{
    static private DatabaseManager databaseManager;
    static Context myContext;
    private static SharedPreferences sharedPreferences;

    public static ArrayList<MonthExpenses> everyMonthExpensesList;

    static public void checkUpdates(Context context) // execute every time app starts
    {
        LoadMonthsExpensesFromDatabase();

        myContext = context;
        sharedPreferences = context.getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);
        String lastUpdatedMonth  = sharedPreferences.getString("lastUpdate", DateStringFormatter.getCurrentMonth());

        String currentMonth = DateStringFormatter.getCurrentMonth();

        if (!currentMonth.equalsIgnoreCase(lastUpdatedMonth)) // executes every month
            setAllBudgetCategoriesExpensesToZero();
    }

    private static void LoadMonthsExpensesFromDatabase()
    {
        Log.i("EXPENSES_TRACKING", "LoadMonthsExpensesFromDatabase: Start Loading from database");
        databaseManager = DatabaseController.getDatabaseManager();
        everyMonthExpensesList = new ArrayList<>();

        // from database
        Cursor cursor = databaseManager.queryTable(MonthlyExpensesTable.TableName
                ,null,null,
                null,null,null,null);


        if(cursor.moveToFirst()) {
            do {
                String year = cursor.getString(cursor.getColumnIndex(MonthlyExpensesTable.colYear));
                String month = cursor.getString(cursor.getColumnIndex(MonthlyExpensesTable.colMonth));
                double expenses = cursor.getDouble(cursor.getColumnIndex(MonthlyExpensesTable.colExpenses));

                Log.i("EXPENSES_TRACKING", "LoadMonthsExpensesFromDatabase: "
                + year + "," + month+","+expenses );

                everyMonthExpensesList.add(new MonthExpenses(month,year,expenses));
            } while (cursor.moveToNext());
        }
    }


    private static void setAllBudgetCategoriesExpensesToZero()
    {
        ContentValues contentValues = new ContentValues();

        contentValues.put(BudgetCategoriesTable.colCurrentBudget,0);
        int c = databaseManager.updateEntries(BudgetCategoriesTable.TableName , contentValues,null,null);
        if(c > 0)
        {
            saveLastUpdatedMonth();

            MonthExpenses monthExpenses  = new MonthExpenses(DateStringFormatter.getCurrentMonth()
                    ,DateStringFormatter.getCurrentYear() , BudgetCategoryManager.getTotalExpenses());
            insertNewMonthExpenses(monthExpenses);

            BudgetCategoryManager.setAllBudgetCategoriesExpensesToZero();
            HomeFragment.refresh();
        }
    }

    public static void insertNewMonthExpenses(MonthExpenses monthExpenses) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(MonthlyExpensesTable.colYear,monthExpenses.getYear());
        contentValues.put(MonthlyExpensesTable.colMonth,monthExpenses.getMonth());
        contentValues.put(MonthlyExpensesTable.colExpenses,monthExpenses.getExpenses());

        long ID = databaseManager.insert(MonthlyExpensesTable.TableName, contentValues);

        if(ID > 0) {
            everyMonthExpensesList.add(monthExpenses);
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

    public static HashMap<String, Double> getMonthlyExpensesMap()
    {
        HashMap<String , Double> output = new HashMap<>();

        for (int i = 0 ; i < 5 && i< everyMonthExpensesList.size(); i++)
        {
            MonthExpenses monthExpenses =everyMonthExpensesList.get(everyMonthExpensesList.size() - i - 1);
            String key = monthExpenses.getYear()+","+monthExpenses.getMonth();
            Double value = monthExpenses.getExpenses();

            output.put(key , value);
        }
        return output;
    }
}
