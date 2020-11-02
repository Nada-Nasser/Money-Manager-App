package com.example.expensestracker.budgetplanner.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.expensestracker.R;
import com.example.expensestracker.budgetplanner.Plan;
import com.example.expensestracker.usermoney.UserWallet;

import java.util.ArrayList;
import java.util.Calendar;

public class PlansListAdapter extends BaseAdapter {

    Context context;
    ArrayList<Plan> plans;

    public PlansListAdapter(Context context, ArrayList<Plan> plans) {
        this.context = context;
        this.plans = plans;
    }

    @Override
    public int getCount() {
        return plans.size();
    }

    @Override
    public Object getItem(int i) {
        return plans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        view = inflater.inflate(R.layout.list_item_plans, null);

        final Plan  plan = plans.get(i);

        RelativeLayout planCardLayout = view.findViewById(R.id.plan_card_layout);
        TextView planTitle = view.findViewById(R.id.plan_title);
        TextView planDesc = view.findViewById(R.id.plan_desc);
        TextView planDate = view.findViewById(R.id.plan_date);
        TextView planBudget = view.findViewById(R.id.plan_budget);

        String year = (plan.getDeadline().toString()).substring(
                plan.getDeadline().toString().length()-4);

        String dateStr = (plan.getDeadline().toString()).substring(0 , 11) + ", " + year;
    //    String dateStr = (plan.getDeadline().toString());

        planTitle.setText(plan.getTitle());
        planDesc.setText(plan.getDescription());
        planDate.setText(dateStr);
        planBudget.setText("$ "+String.valueOf(plan.getMoneyNeeded()));

        if(plan.getMoneyNeeded() <= UserWallet.getUserMoney())
        {
            planCardLayout.setBackgroundResource(R.color.doneColor);
        }

        if (plan.getDeadline().compareTo(Calendar.getInstance().getTime()) < 0)
        {
            planCardLayout.setBackgroundResource(R.color.worryColor);
        }


        return view;
    }
}
