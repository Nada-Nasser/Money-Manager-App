package com.example.expensestracker.mainactivity.fragments.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.expensestracker.R;
import com.example.expensestracker.budget_categories.BudgetCategory;
import com.example.expensestracker.budget_categories.BudgetCategoryManager;
import com.example.expensestracker.budget_categories.ui.BudgetCategoryListAdapter;
import com.example.expensestracker.transactions.activities.AddingTransactionActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    TextView totalExpensesTextView;
    ListView budgetCategoryListView;

    static BudgetCategoryListAdapter budgetCategoryListAdapter;
    ArrayList<BudgetCategory> budgetCategories;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final Context context = getActivity().getApplicationContext();

        totalExpensesTextView = root.findViewById(R.id.total_expenses);
        budgetCategoryListView = root.findViewById(R.id.budget_category_lv);

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //      .setAction("Action", null).show();

                Intent intent = new Intent(context, AddingTransactionActivity.class);
                startActivity(intent);
            }
        });

        budgetCategories = new ArrayList<>();
        budgetCategories = BudgetCategoryManager.getBudgetCategories();

        budgetCategoryListAdapter = new BudgetCategoryListAdapter(context, budgetCategories);

        budgetCategoryListView.setAdapter(budgetCategoryListAdapter);
        budgetCategoryListAdapter.notifyDataSetChanged();

        totalExpensesTextView.setText("$ "+String.valueOf(BudgetCategoryManager.getTotalExpenses()));

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        budgetCategoryListAdapter.notifyDataSetChanged();
        totalExpensesTextView.setText("$ "+String.valueOf(BudgetCategoryManager.getTotalExpenses()));
    }

    @Override
    public void onStart() {
        super.onStart();
        if(budgetCategoryListAdapter!=null)
        {
            budgetCategoryListAdapter.notifyDataSetChanged();
            totalExpensesTextView.setText("$ "+String.valueOf(BudgetCategoryManager.getTotalExpenses()));
        }
    }

    public static void refresh()
    {
        if(budgetCategoryListAdapter!=null)
        {
            budgetCategoryListAdapter.notifyDataSetChanged();
        }
    }
}