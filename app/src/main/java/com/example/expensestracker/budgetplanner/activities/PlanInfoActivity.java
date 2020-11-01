package com.example.expensestracker.budgetplanner.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import  com.example.expensestracker.R;
import com.example.expensestracker.budgetplanner.Plan;
import com.example.expensestracker.budgetplanner.PlansManager;
import com.example.expensestracker.budgetplanner.ui.BudgetPlannerFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PlanInfoActivity extends AppCompatActivity {

    EditText planTitleEditText;
    EditText planDescEditText;
    EditText planBudgetEditText;
    DatePicker planDeadLineDatePicker;

    boolean editPlan;
    Plan oldPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_info);
        try {

            planTitleEditText = findViewById(R.id.plan_title_tf);
            planDescEditText = findViewById(R.id.plan_desc_tf);
            planBudgetEditText = findViewById(R.id.plan_budget_tf);
            planDeadLineDatePicker = findViewById(R.id.plan_date_picker);

            Bundle bundle = getIntent().getExtras();
            editPlan = bundle.getBoolean("editPlan", false);

            if (editPlan) {

                Button submitBu = findViewById(R.id.submitBu);
                submitBu.setText(R.string.edit_plan);

                int planID = bundle.getInt("planID" , -1);

                String title = bundle.getString("planTitle", "none");
                String desc = bundle.getString("planDesc", "none");
                double budget =
                      Double.parseDouble(bundle.getString("planBudget", "0"));
                String dateStr = bundle.getString("planDate", "none");

                planTitleEditText.setText(title);
                //planTitleEditText.setEnabled(false);
                planDescEditText.setText(desc);
                planBudgetEditText.setText(String.valueOf(budget));

                //Sat Oct 31 2020
                // format = "E MMM dd yyyy"
                Date date = null;
                try {
                    if (!dateStr.equalsIgnoreCase("none")) {

                        Log.i("SimpleDateFormat", "onCreate: " + dateStr);

                        SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd yyyy");
                        date = formatter.parse(dateStr);

                        Log.i("formated date", "onCreate:" +date.toString());

                        // TODO .updateDate Doesnot work
                        planDeadLineDatePicker.updateDate(date.getYear(), date.getMonth(), date.getDay());
                    }
                } catch (ParseException e) {
                    date = Calendar.getInstance().getTime();
                    e.printStackTrace();
                }
                oldPlan = new Plan(planID , title, desc, budget, date);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private Date getDateFromDatePicker() {
        int day = planDeadLineDatePicker.getDayOfMonth();
        int month = planDeadLineDatePicker.getMonth();
        int year = planDeadLineDatePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,day,0,0,0);

        return calendar.getTime();
    }

    public void onClickAddPlan(View view)
    {
        String title = planTitleEditText.getText().toString();
        String desc = planDescEditText.getText().toString();
        double budget = Double.parseDouble(planBudgetEditText.getText().toString());
        Date deadline = getDateFromDatePicker();

        if(editPlan)
        {
            PlansManager.editPlan(oldPlan.getPlanID() , title, desc, budget, deadline);
            BudgetPlannerFragment.refresh();
            makeLongToast("Your Plan edited successfully");
        }
        else {
            PlansManager.addPlan(title, desc, budget, deadline);
            BudgetPlannerFragment.refresh();
            makeLongToast("Your Plan added successfully");
        }
        finish();
    }

    private void makeLongToast(String msg)
    {
        Toast.makeText(getApplicationContext(),msg , Toast.LENGTH_LONG).show();
    }
}