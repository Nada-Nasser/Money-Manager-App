package com.example.expensestracker.budget_categories.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.expensestracker.R;
import com.example.expensestracker.budget_categories.BudgetCategory;
import com.example.expensestracker.budget_categories.BudgetCategoryManager;
import com.example.expensestracker.mainactivity.fragments.budgetmanager.BudgetManagerFragment;
import com.example.expensestracker.mainactivity.fragments.home.HomeFragment;

public class BudgetCategoryInfoFragment extends DialogFragment implements View.OnClickListener
{
    View newBudgetCategoryView;
    boolean isNew;
    BudgetCategory budgetCategory;

    EditText budgetCategoryNameEditText;
    EditText budgetCategoryPriceEditText;
    Button addBudgetCategoryButton;

    public BudgetCategoryInfoFragment( BudgetCategory budgetCategory) // false
    {
        this.isNew = false;
        this.budgetCategory =budgetCategory;
    }

    public BudgetCategoryInfoFragment() // true
    {
        this.isNew = true;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        newBudgetCategoryView = inflater.inflate(R.layout.fragment_budget_category_info,container,false);

        budgetCategoryNameEditText = newBudgetCategoryView.findViewById(R.id.category_name);
        budgetCategoryPriceEditText = newBudgetCategoryView.findViewById(R.id.Category_budget);
        addBudgetCategoryButton = newBudgetCategoryView.findViewById(R.id.add_Budget_button);

        if (!isNew)
        {
            budgetCategoryNameEditText.setText(budgetCategory.getName());

  //          budgetCategoryNameEditText.setEnabled(false);

            budgetCategoryNameEditText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext()  , R.string.errorMsg ,
                            Toast.LENGTH_LONG).show();
                }
            });

            budgetCategoryPriceEditText.setText(String.valueOf(budgetCategory.getMaxBudget()));
        }

        addBudgetCategoryButton.setOnClickListener(this);


        return newBudgetCategoryView;
    }

    @Override
    public void onClick(View view) {
        this.dismiss();
        String budgetCategoryName = budgetCategoryNameEditText.getText().toString();
        double budget = Double.parseDouble(budgetCategoryPriceEditText.getText().toString());

        if (isNew)
            BudgetCategoryManager.addNewBudgetCategory(budgetCategoryName , budget);
        else
            BudgetCategoryManager.editCategoryEnvelop(budgetCategory.getID() , budgetCategoryName , budget);

        HomeFragment.refresh();
        BudgetManagerFragment.refresh();
    }
}