package com.example.expensestracker.budget_categories;

import java.util.ArrayList;
import java.util.HashMap;

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

    static public boolean editCategoryEnvelop(String category , double budget)
    {
        for (int i = 0 ; i < budgetCategories.size() ; i++)
        {
            if(budgetCategories.get(i).getName().equalsIgnoreCase(category))
            {
                budgetCategories.get(i).setMaxBudget(budget);
                return true;
            }
        }
        return false;
    }

    static public boolean addNewBudgetCategory(String name , double budget)
    {
        BudgetCategory newBudgetCategory = new BudgetCategory(name , budget);
        budgetCategories.add(newBudgetCategory);

        return true;
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
