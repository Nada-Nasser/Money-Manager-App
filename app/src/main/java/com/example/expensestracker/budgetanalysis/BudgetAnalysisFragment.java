package com.example.expensestracker.budgetanalysis;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.chart.common.listener.Event;
import com.anychart.chart.common.listener.ListenersInterface;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;
import com.example.expensestracker.R;
import com.example.expensestracker.budget_categories.BudgetCategoryManager;
import com.example.expensestracker.budget_categories.ui.BudgetCategoryListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BudgetAnalysisFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_budget_analysis, container, false);
        final Context context = getActivity().getApplicationContext();
        try {
            AnyChartView anyChartView = root.findViewById(R.id.any_chart_view);
            anyChartView.setProgressBar(root.findViewById(R.id.progress_bar));

            HashMap<String, Double> dataMap = BudgetCategoryManager.getCategoryExpensesMap();
            List<DataEntry> data = new ArrayList<>();

            for (Map.Entry element : dataMap.entrySet()) {
                String key = (String) element.getKey();
                Double value = (Double) element.getValue();

                int intValue = value.intValue();

                data.add(new ValueDataEntry(key, intValue));
                Log.i("onCreateView", "onCreateView: " + intValue);
            }

            buildAnalysisChart(anyChartView, "Your Expenses per category this Month", "Budget Categories"
                    , data);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return root;
    }

    //anyChartView , Pie title , channelsTitle , List<DataEntry>
    private void buildAnalysisChart(AnyChartView anyChartView , String pieTitle ,String channelsTitle
                , List<DataEntry> data) {


        Pie pie = AnyChart.pie();

        pie.setOnClickListener(new ListenersInterface.OnClickListener() {
            @Override
            public void onClick(Event event) {

            }
        });

        pie.data(data);

        pie.title(pieTitle);

        pie.labels().position("outside");

        pie.legend().title().enabled(true);
        pie.legend().title()
                .text(channelsTitle)
                .padding(0d, 0d, 10d, 0d);

        pie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        anyChartView.setChart(pie);

    }
}