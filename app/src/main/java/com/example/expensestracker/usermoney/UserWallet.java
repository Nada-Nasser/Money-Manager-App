package com.example.expensestracker.usermoney;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import  com.example.expensestracker.R;
public class UserWallet
{
    static double userMoney;
    private static SharedPreferences sharedPreferences;
    private static Context userContext;

    static public void loadUserMoney(Context context)
    {
        try {
            userContext = context;
            sharedPreferences = context.getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);
            String m = sharedPreferences.getString("userMoney", "0");
            Log.i("UserWallet", "loadUserMoney: " + m);

            userMoney = Double.parseDouble(m);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static double getUserMoney() {
        return userMoney;
    }

    public static void addIncome(double income)
    {
        userMoney+=income;
        save();
    }

    public static void takeFromWallet(double expense)
    {
        userMoney-=expense;
        save();
    }

    private static void save() {
        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("userMoney", String.valueOf(userMoney));

            //    editor.putFloat("userMoney" , (float) userMoney);

            editor.commit();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
