package com.example.expensestracker.mainactivity.fragments.budgetmanager;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.expensestracker.budget_categories.ui.BudgetCategoryInfoFragment;

import com.example.expensestracker.R;
import com.example.expensestracker.budget_categories.BudgetCategory;
import com.example.expensestracker.budget_categories.BudgetCategoryManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BudgetManagerFragment extends Fragment {

    ListView budgetCategoryListView;

    static BudgetManagerListAdapter budgetManagerListAdapter;
    ArrayList<BudgetCategory> budgetCategories;

    FragmentActivity fragmentActivity;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)  {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_budget_manager, container, false);
        final Context context = getActivity().getApplicationContext();
        fragmentActivity = (FragmentActivity) getActivity();
        try {
            budgetCategoryListView = root.findViewById(R.id.budget_manager_lv);

            budgetCategories = new ArrayList<>();
            budgetCategories = BudgetCategoryManager.getBudgetCategories();

            budgetManagerListAdapter = new BudgetManagerListAdapter(context, budgetCategories);

            budgetCategoryListView.setAdapter(budgetManagerListAdapter);
            budgetManagerListAdapter.notifyDataSetChanged();

            budgetCategoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    BudgetCategory selectedBudgetCategory = budgetCategories.get(i);
                    editBudgetCategory(selectedBudgetCategory);
                }
            });

            FloatingActionButton fab = root.findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    addNewBudgetCategory();
                }
            });

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return root;
    }

    void editBudgetCategory( BudgetCategory budgetCategory)
    {
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();

        BudgetCategoryInfoFragment budgetCategoryInfoFragment = new BudgetCategoryInfoFragment(budgetCategory);

        budgetCategoryInfoFragment.show(fragmentManager,"New budget");
    }

    @Override
    public void onResume() {
        super.onResume();
        budgetManagerListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        if(budgetManagerListAdapter!=null)
        {
            budgetManagerListAdapter.notifyDataSetChanged();
        }
    }

    public static void refresh()
    {
        if(budgetManagerListAdapter!=null)
        {
            budgetManagerListAdapter.notifyDataSetChanged();
        }
    }

    void addNewBudgetCategory()
    {
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();

        BudgetCategoryInfoFragment budgetCategoryInfoFragment = new BudgetCategoryInfoFragment();

        budgetCategoryInfoFragment.show(fragmentManager,"New budget");
    }


}