package com.example.expensestracker.monthestracker.ui;

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
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.example.expensestracker.R;
import com.example.expensestracker.budget_categories.BudgetCategoryManager;
import com.example.expensestracker.monthestracker.MonthsTracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonthlyExpensesAnalysisFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_monthly_expenses_analysis, container, false);
        final Context context = getActivity().getApplicationContext();
        try {
            AnyChartView anyChartView = root.findViewById(R.id.any_chart_view);
            anyChartView.setProgressBar(root.findViewById(R.id.progress_bar));

            HashMap<String, Double> dataMap = MonthsTracker.getMonthlyExpensesMap(); // month/year , Expenses
            List<DataEntry> data = new ArrayList<>();

            for (Map.Entry element : dataMap.entrySet()) {
                String key = (String) element.getKey();
                Double value = (Double) element.getValue();

                int intValue = value.intValue();

                data.add(new ValueDataEntry(key, intValue));
                Log.i("onCreateView", "onCreateView: " + intValue);
            }

            buildAnalysisChart(anyChartView, data);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return root;
    }

    private void buildAnalysisChart(AnyChartView anyChartView,List<DataEntry> seriesData) {

        if(seriesData.isEmpty())
        {
            seriesData.add(new ValueDataEntry("You didn't use the app more than one month" , 0));
        }

        Cartesian cartesian = AnyChart.line();

        cartesian.animation(true);

        cartesian.padding(10d, 20d, 5d, 20d);

        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        cartesian.title("Your Total Expenses Over Last Five Months"); //cartesianTitle

        cartesian.yAxis(0).title("Your Expenses ($)"); // yAxisTitle
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");

        Line series1 = cartesian.line(series1Mapping);
        series1.name("Your Expenses");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);

        anyChartView.setChart(cartesian);
    }
}