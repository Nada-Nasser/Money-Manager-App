package com.example.expensestracker.budget_categories;

import java.util.ArrayList;

// TODO Deal with local database
public class BudgetCategoryManager
{
    static ArrayList<BudgetCategory> budgetCategories;

    // load all category names and its envelop from backend
    static public boolean LoadCategoryEnvelop()
    {
        budgetCategories = new ArrayList<>();

        budgetCategories.add(new BudgetCategory("Groceries" ,200 ));
        budgetCategories.add(new BudgetCategory("Gas" ,100 ));
        budgetCategories.add(new BudgetCategory("Eating Out" ,150 ));
        budgetCategories.add(new BudgetCategory("Debt Payoff" ,200 ));

        return true;
    }

    public static ArrayList<BudgetCategory> getBudgetCategories() {
        return budgetCategories;
    }

    public static BudgetCategory getBudgetCategory(int i) {
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

    static public BudgetCategory getBudgetCategory(String category)
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

    static public boolean EditCategoryEnvelop(String category)
    {
        throw new UnsupportedOperationException();
    }

    static public boolean addNewBudgetCategory(String category)
    {
        throw new UnsupportedOperationException();
    }

    static public boolean deleteBudgetCategory(String category)
    {
        throw new UnsupportedOperationException();
    }

    static public boolean addToCurrentExpenses(String category , double addedValue)
    {
        for (int i = 0 ; i < budgetCategories.size() ; i++)
        {
            if(budgetCategories.get(i).getName().equalsIgnoreCase(category))
            {
                double oldValue  = budgetCategories.get(i).getCurrentBudget();
                budgetCategories.get(i).setCurrentBudget(oldValue+addedValue);
                return true;
            }
        }
        return false;
    }
}
