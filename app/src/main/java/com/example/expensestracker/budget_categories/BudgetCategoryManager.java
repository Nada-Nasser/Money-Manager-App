package com.example.expensestracker.budget_categories;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.expensestracker.dbmanagment.BudgetCategoriesTable;
import com.example.expensestracker.dbmanagment.DatabaseController;
import com.example.expensestracker.dbmanagment.DatabaseManager;

import java.util.ArrayList;
import java.util.HashMap;

// TODO Change the current budget every month
public class BudgetCategoryManager
{
    static ArrayList<BudgetCategory> budgetCategories;
    static DatabaseManager databaseManager;

    // load all category names and its envelop from backend
    static public boolean LoadCategoryEnvelop()
    {
        budgetCategories = new ArrayList<>();
        databaseManager = DatabaseController.getDatabaseManager();

        // from database
        Cursor cursor = databaseManager.queryTable(BudgetCategoriesTable.TableName
                ,null,null,
                null,null,null,null);


        if(cursor.moveToFirst()) {
            do {
                int Id = cursor.getInt(cursor.getColumnIndex(BudgetCategoriesTable.colID));
                String name = cursor.getString(cursor.getColumnIndex(BudgetCategoriesTable.colName));
                double maxBudget = cursor.getDouble(cursor.getColumnIndex(BudgetCategoriesTable.colMaxBudget));
                double currentBudget = cursor.getDouble(cursor.getColumnIndex(BudgetCategoriesTable.colCurrentBudget));

                budgetCategories.add(new BudgetCategory(Id , name , maxBudget , currentBudget));
            } while (cursor.moveToNext());
        }

        return true;
    }

    public static HashMap<String , Double> getCategoryExpensesMap()
    {
        HashMap<String , Double> output = new HashMap<>();
        for (int i = 0 ; i < budgetCategories.size() ; i++)
        {
            output.put(budgetCategories.get(i).getName() ,budgetCategories.get(i).getCurrentBudget());
        }
        return output;
    }

    public static ArrayList<BudgetCategory> getBudgetCategories() {
        return budgetCategories;
    }

    public static BudgetCategory getBudgetCategoryByName(int i) {
        return budgetCategories.get(i);
    }

    static public ArrayList<String> getCategoryNames()
    {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0 ; i < budgetCategories.size() ; i++)
        {
            names.add(budgetCategories.get(i).getName());
        }
        return names;
    }

    static public BudgetCategory getBudgetCategoryByName(String category)
    {
        for (int i = 0 ; i < budgetCategories.size() ; i++)
        {
            if(budgetCategories.get(i).getName().equalsIgnoreCase(category))
            {
                return budgetCategories.get(i);
            }
        }
        return null;
    }

    static public boolean editCategoryEnvelop(int id , String category , double budget)
    {
        String selection = BudgetCategoriesTable.colID + " = ? ";
        String[] selectionArg = {String.valueOf(id)};

        ContentValues contentValues = new ContentValues();

        contentValues.put(BudgetCategoriesTable.colName,category);
        contentValues.put(BudgetCategoriesTable.colMaxBudget,budget);

        int c = databaseManager.updateEntries(BudgetCategoriesTable.TableName , contentValues,selection,selectionArg);
        if(c > 0)
        {
            for (int i = 0 ; i < budgetCategories.size() ; i++)
            {
                if(budgetCategories.get(i).getID()==id)
                {
                    budgetCategories.get(i).setMaxBudget(budget);
                    budgetCategories.get(i).setName(category);
                    return true;
                }
            }
        }

        return false;
    }

    static public boolean addNewBudgetCategory(String name , double budget)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BudgetCategoriesTable.colName,name);
        contentValues.put(BudgetCategoriesTable.colMaxBudget,budget);
        contentValues.put(BudgetCategoriesTable.colCurrentBudget,0);
        long ID = databaseManager.insert(BudgetCategoriesTable.TableName, contentValues);

        if(ID > 0) {

            BudgetCategory newBudgetCategory = new BudgetCategory((int) ID, name, budget);
            budgetCategories.add(newBudgetCategory);

            return true;
        }
        else {
            return false;
        }
    }

    static public boolean addToCurrentExpenses(int id, double addedValue)
    {
        double newValue  = addedValue;
        for (int i = 0 ; i < budgetCategories.size() ; i++)
        {
            if(budgetCategories.get(i).getID() == id)
            {
                double oldValue  = budgetCategories.get(i).getCurrentBudget();
                budgetCategories.get(i).setCurrentBudget(oldValue+addedValue);
                newValue = oldValue+addedValue;

                String selection = BudgetCategoriesTable.colID + " = ? ";
                String[] selectionArg = {String.valueOf(id)};
                ContentValues contentValues = new ContentValues();
                contentValues.put(BudgetCategoriesTable.colCurrentBudget,newValue);

                int c = databaseManager.updateEntries(BudgetCategoriesTable.TableName , contentValues,selection,selectionArg);

                return true;
            }
        }
        return false;
    }

    static public BudgetCategory getBudgetCategoryById(int id) {
        for (int i = 0; i < budgetCategories.size(); i++) {
            if (budgetCategories.get(i).getID() == id) {
                return budgetCategories.get(i);
            }
        }
        return null;
    }

    static public int getCategoryIDFromName(String name)
    {
        for (int i = 0; i < budgetCategories.size(); i++) {
            if (budgetCategories.get(i).getName().equalsIgnoreCase(name))
            {
                return budgetCategories.get(i).getID();
            }
        }
        return 0;
    }

    static public double getTotalExpenses()
    {
        double total = 0;

        for (int i = 0; i < budgetCategories.size(); i++) {
            total+= budgetCategories.get(i).getCurrentBudget();
        }

        return total;
    }

    public static void setAllBudgetCategoriesExpensesToZero()
    {
        for (int i = 0; i < budgetCategories.size(); i++) {
            budgetCategories.get(i).setCurrentBudget(0);
        }
    }
}
