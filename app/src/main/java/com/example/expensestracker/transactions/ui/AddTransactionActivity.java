package com.example.expensestracker.transactions.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.expensestracker.R;
import com.example.expensestracker.budget_categories.BudgetCategory;
import com.example.expensestracker.budget_categories.BudgetCategoryManager;
import com.example.expensestracker.transactions.Transaction;
import com.example.expensestracker.transactions.TransactionManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddTransactionActivity extends AppCompatActivity {

    Spinner productCategorySpinner;
    EditText productNameTextField;
    EditText productPriceTextField;
    DatePicker transactionDatePicker;

    ArrayList<String> productCategories;
    ArrayAdapter categoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        try {
            productCategorySpinner = findViewById(R.id.product_category_spinner);
            productNameTextField  = findViewById(R.id.product_name_textField);
            productPriceTextField = findViewById(R.id.product_price_textField);
            transactionDatePicker = findViewById(R.id.date_picker);


            productCategories = BudgetCategoryManager.getCategoryNames();

            categoriesAdapter = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,productCategories);
            productCategorySpinner.setAdapter(categoriesAdapter);
            productCategorySpinner.setSelection(0);

        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void onClickAddTransaction(View view)
    {
        try {
            Date transactionDate = getDateFromDatePicker();
            BudgetCategory budgetCategory = BudgetCategoryManager.
                    getBudgetCategory(productCategorySpinner.getSelectedItem().toString());

            String productName = productNameTextField.getText().toString();
            double productPrice = Double.parseDouble(productPriceTextField.getText().toString());

            Transaction transaction = new Transaction(budgetCategory , productName,productPrice , transactionDate);
            TransactionManager.addNewTransaction(transaction);

            makeLongToast(transaction.toString());
        }
        catch (Exception e)
        {
            makeLongToast(e.getMessage());
            e.printStackTrace();
        }

    }

    private Date getDateFromDatePicker() {
        int day = transactionDatePicker.getDayOfMonth();
        int month = transactionDatePicker.getMonth();
        int year = transactionDatePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,day);

        return calendar.getTime();
    }

    private void makeLongToast(String msg)
    {
        Toast.makeText(getApplicationContext(),msg , Toast.LENGTH_LONG).show();
    }
}