package com.example.expensestracker.transactions.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.expensestracker.R;
import com.example.expensestracker.transactions.Transaction;

import java.util.ArrayList;
import java.util.Date;

public class TransactionListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<Transaction> transactionList;

    public TransactionListAdapter(Context context, ArrayList<Transaction> transactionList)
    {
        this.context = context;
        this.transactionList = transactionList;
    }

    @Override
    public int getCount() {
        return transactionList.size();
    }

    @Override
    public Object getItem(int i) {
        return transactionList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        view = inflater.inflate(R.layout.list_item_transaction, null);

        final Transaction transaction = transactionList.get(i);

        TextView productName = view.findViewById(R.id.product_name);
        TextView productPrice = view.findViewById(R.id.product_price);
        TextView buyDate  = view.findViewById(R.id.buy_date);

        Date date = transaction.getBuyDate();

        productName.setText(transaction.getProductName());
        productPrice.setText(String.valueOf(transaction.getPrice()));
        buyDate.setText(date.toString());
        return view;
    }
}
