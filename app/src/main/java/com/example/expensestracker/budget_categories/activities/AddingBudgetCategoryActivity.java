package com.example.expensestracker.budget_categories.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.expensestracker.R;
import com.example.expensestracker.budget_categories.ui.IconsListAdapter;

import java.util.ArrayList;

public class AddingBudgetCategoryActivity extends AppCompatActivity
{

    ArrayList<Integer> icons;
    IconsListAdapter iconsListAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_budget_category);
        icons = new ArrayList<>();

        icons.add(R.drawable.ic_dollar);
        icons.add(R.drawable.ic_menu_camera);
        icons.add(R.drawable.plan);
        icons.add(R.drawable.trending);
        icons.add(R.drawable.ic_dollar);
        icons.add(R.drawable.money_bag);
        icons.add(R.drawable.ic_paper_money);
        icons.add(R.drawable.ic_shopaholic);
        icons.add(R.drawable.icon_shopaholic);

        listView = findViewById(R.id.list_view_id);
        iconsListAdapter = new IconsListAdapter(icons , this);
        listView.setAdapter(iconsListAdapter);
        iconsListAdapter.notifyDataSetChanged();
    }
}