package com.example.expensestracker.mainactivity.fragments.budgetmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.expensestracker.R;
import com.example.expensestracker.budget_categories.BudgetCategory;

import java.util.ArrayList;

public class BudgetManagerListAdapter extends BaseAdapter
{
    ArrayList<BudgetCategory> budgetCategories;
    Context context;

    public BudgetManagerListAdapter(Context context, ArrayList<BudgetCategory> budgetCategories) {
        this.budgetCategories = budgetCategories;

        this.context = context;
    }

    @Override
    public int getCount() {
        return budgetCategories.size();
    }

    @Override
    public Object getItem(int i) {
        return budgetCategories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        view = inflater.inflate(R.layout.list_item_budget_manager, null);

        final BudgetCategory budgetCategory = budgetCategories.get(i);

        TextView categoryName = view.findViewById(R.id.category_name);
        TextView maxExpenses = view.findViewById(R.id.max_expenses);

        categoryName.setText(budgetCategory.getName());
        maxExpenses.setText("$"+String.valueOf(budgetCategory.getMaxBudget()));

        return view;
    }
}
