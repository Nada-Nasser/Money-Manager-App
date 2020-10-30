package com.example.expensestracker.budget_categories.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.expensestracker.R;
import com.example.expensestracker.budget_categories.BudgetCategory;
import com.example.expensestracker.transactions.activities.ListingCategoryTransactionsActivity;

import java.util.ArrayList;

public class BudgetCategoryListAdapter extends BaseAdapter
{
    ArrayList<BudgetCategory> budgetCategories;
    Context context;

    public BudgetCategoryListAdapter(Context context, ArrayList<BudgetCategory> budgetCategories) {
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
        view = inflater.inflate(R.layout.list_item_budget_categoty, null);

        final BudgetCategory budgetCategory = budgetCategories.get(i);

        LinearLayout linearLayout = view.findViewById(R.id.expenses_layout);
        SeekBar seekBar = view.findViewById(R.id.budget_seekbar);
        TextView categoryName = view.findViewById(R.id.category_name);
        TextView currentExpenses = view.findViewById(R.id.current_expenses);
        TextView maxExpenses = view.findViewById(R.id.max_expenses);

        categoryName.setText(budgetCategory.getName());
        currentExpenses.setText("$"+String.valueOf(budgetCategory.getCurrentBudget()));
        maxExpenses.setText("$"+String.valueOf(budgetCategory.getMaxBudget()));

        seekBar.setMax((int) budgetCategory.getMaxBudget());
        seekBar.setProgress((int) budgetCategory.getCurrentBudget());

        if (budgetCategory.getCurrentBudget() > budgetCategory.getMaxBudget())
        {
            linearLayout.setBackgroundResource(R.drawable.customborder);
        }

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent( context , ListingCategoryTransactionsActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("category", budgetCategory.getName());
                    context.startActivity(intent);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        return view;
    }
}
