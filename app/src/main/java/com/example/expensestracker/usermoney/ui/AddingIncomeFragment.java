package com.example.expensestracker.usermoney.ui;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.expensestracker.R;
import com.example.expensestracker.usermoney.UserWallet;

public class AddingIncomeFragment extends DialogFragment implements View.OnClickListener {

    View addingIncomeView;
    EditText incomeEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        addingIncomeView = inflater.inflate(R.layout.fragment_adding_income,container,false);

        incomeEditText= addingIncomeView.findViewById(R.id.income);
        Button addIncomeButton = addingIncomeView.findViewById(R.id.add_income_button);

        addIncomeButton.setOnClickListener(this);
        return addingIncomeView;
    }

    @Override
    public void onClick(View view) {
        this.dismiss();
        try {
            double income =Double.parseDouble(incomeEditText.getText().toString());
            UserWallet.addIncome(income);
            Toast.makeText(getContext() , "Your Wallet contains now : " + UserWallet.getUserMoney()
            ,Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}