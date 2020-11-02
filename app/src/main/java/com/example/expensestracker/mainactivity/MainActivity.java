package com.example.expensestracker.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.example.expensestracker.budget_categories.ui.BudgetCategoryInfoFragment;
import com.example.expensestracker.R;
import com.example.expensestracker.budget_categories.BudgetCategoryManager;
import com.example.expensestracker.budgetplanner.PlansManager;
import com.example.expensestracker.dbmanagment.BudgetCategoriesTable;
import com.example.expensestracker.dbmanagment.DatabaseController;
import com.example.expensestracker.globalOperations.DateStringFormatter;
import com.example.expensestracker.monthestracker.MonthExpenses;
import com.example.expensestracker.monthestracker.MonthsTracker;
import com.example.expensestracker.transactions.TransactionManager;
import com.example.expensestracker.transactions.activities.AddingTransactionActivity;
import com.example.expensestracker.usermoney.UserWallet;
import com.example.expensestracker.usermoney.ui.AddingIncomeFragment;
import com.example.expensestracker.usermoney.ui.UserWalletFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    DatabaseController databaseController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {

            databaseController = new DatabaseController(this);
            BudgetCategoryManager.LoadCategoryEnvelop();
            MonthsTracker.checkUpdates(this);
            TransactionManager.loadTransactions();
            PlansManager.loadPlans();
            UserWallet.loadUserMoney(this);

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //      .setAction("Action", null).show();

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            NavigationView navigationView = findViewById(R.id.nav_view);

            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_budgetManager , R.id.nav_budget_analysis ,R.id.nav_budgetPlanner
                    ,R.id.nav_monthly_expenses_analysis)
                    .setDrawerLayout(drawer)
                    .build();

            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.add_income:
                addIncome();
                return true;
            case R.id.user_wallet:
                showUserWallet();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    private void addIncome() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        AddingIncomeFragment addingIncomeFragment = new AddingIncomeFragment();

        addingIncomeFragment.show(fragmentManager,"add income");
    }

    private void showUserWallet() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        UserWalletFragment userWalletFragment = new UserWalletFragment();

        userWalletFragment.show(fragmentManager,"add income");
    }

    // TODO // Database contain(plans , transactions , budget categories)
}