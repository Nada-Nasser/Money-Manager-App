package com.example.expensestracker.budgetplanner.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.expensestracker.R;
import com.example.expensestracker.budgetplanner.Plan;
import com.example.expensestracker.budgetplanner.PlansManager;
import com.example.expensestracker.budgetplanner.activities.PlanInfoActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BudgetPlannerFragment extends Fragment
{
    ListView plansListView;

    static PlansListAdapter plansListAdapter;
    ArrayList<Plan> plansList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_budget_planner, container, false);
        final Context context = getActivity().getApplicationContext();


        plansListView = root.findViewById(R.id.plans_lv);

        plansList = new ArrayList<>();
        plansList = PlansManager.getPlansList();

        plansListAdapter = new PlansListAdapter(context, plansList);

        plansListView.setAdapter(plansListAdapter);
        plansListAdapter.notifyDataSetChanged();

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startPlanActivity();
            }
        });

        plansListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Plan selectedPlan = plansList.get(i);
                try {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());

                    alertBuilder.setMessage("Do you wont to \"Edit\" or \"Delete\" this plan")
                            .setTitle(plansList.get(i).getTitle() + " Plan")
                            .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // open

                                    editSelectedPlan(context, selectedPlan);
                                }
                            })
                            .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    PlansManager.deletePlan(selectedPlan);
                                    refresh();
                                }
                            }).show();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        return root;
    }

    void editSelectedPlan(Context context , Plan selectedPlan)
    {
        try {
            Intent intent = new Intent(context, PlanInfoActivity.class);

            intent.putExtra("editPlan", true);

            intent.putExtra("planID" , selectedPlan.getPlanID());
            intent.putExtra("planTitle", selectedPlan.getTitle());
            intent.putExtra("planDesc", selectedPlan.getDescription());
            intent.putExtra("planBudget", String.valueOf(selectedPlan.getMoneyNeeded()));

            String year = (selectedPlan.getDeadline().toString()).substring(
                    selectedPlan.getDeadline().toString().length() - 4);

            String dateStr = (selectedPlan.getDeadline().toString()).substring(0, 10) + " " + year;

            Log.i("planDate", "editSelectedPlan: "

                    + (selectedPlan.getDeadline().toString()).substring(0, 10)

                    + "," + year);

            intent.putExtra("planDate", dateStr);
            startActivity(intent);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private void startPlanActivity() {
        Intent intent = new Intent(getContext(), PlanInfoActivity.class);
        intent.putExtra("editPlan" , false);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        plansListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        if(plansListAdapter!=null)
        {
            plansListAdapter.notifyDataSetChanged();
        }
    }

    public static void refresh() // TODO : call it when you add a new plan
    {
        if(plansListAdapter!=null)
        {
            plansListAdapter.notifyDataSetChanged();
        }
    }
}