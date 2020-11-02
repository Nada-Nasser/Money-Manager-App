package com.example.expensestracker.budget_categories.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.expensestracker.R;

import java.util.ArrayList;

public class IconsListAdapter extends BaseAdapter
{
    ArrayList<Integer> icons;
    Context context;

    public IconsListAdapter(ArrayList<Integer> icons, Context context) {
        this.icons = icons;
        this.context = context;
    }

    @Override
    public int getCount() {
        return icons.size();
    }

    @Override
    public Object getItem(int i) {
        return icons.get(i);
    }

    @Override
    public long getItemId(int i) {
        return icons.get(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        view = inflater.inflate(R.layout.list_item_icon, null);

        int icon = icons.get(i);
        ImageView imageView = view.findViewById(R.id.icon_img);
        imageView.setImageResource(icon);

        return view;
    }
}
