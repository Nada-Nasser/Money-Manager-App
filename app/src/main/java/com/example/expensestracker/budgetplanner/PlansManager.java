package com.example.expensestracker.budgetplanner;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.expensestracker.dbmanagment.DatabaseController;
import com.example.expensestracker.dbmanagment.DatabaseManager;
import com.example.expensestracker.dbmanagment.PlansTable;
import com.example.expensestracker.globalOperations.DateStringFormatter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class PlansManager
{
    static ArrayList<Plan> plansList;
    static DatabaseManager databaseManager;

    public static ArrayList<Plan> getPlansList() {
        return plansList;
    }

    public static void loadPlans() throws ParseException //TODO from database
    {
        plansList = new ArrayList<>();
        databaseManager = DatabaseController.getDatabaseManager();
        //String title, String description, double moneyNeeded, Date deadline

        // from database
        Cursor cursor = databaseManager.queryTable(PlansTable.TableName
                ,null,null,
                null,null,null,null);


        if(cursor.moveToFirst()) {
            do {
                int Id = cursor.getInt(cursor.getColumnIndex(PlansTable.colID));
                String title = cursor.getString(cursor.getColumnIndex(PlansTable.colTitle));
                String description = cursor.getString(cursor.getColumnIndex(PlansTable.colDescription));
                double moneyNeeded = cursor.getDouble(cursor.getColumnIndex(PlansTable.colNeededMoney));
                Date deadline      = DateStringFormatter.StringToDate(cursor.getString(cursor.getColumnIndex(PlansTable.colDeadline)));

                plansList.add(new Plan(Id,title,description,moneyNeeded,deadline));
            } while (cursor.moveToNext());
        }
/*
        plansList.add(new Plan("Buy home" , "desc" , 5000.0 , Calendar.getInstance().getTime()));
        plansList.add(new Plan("Buy car" , "desc" , 15000.0 , Calendar.getInstance().getTime()));
        plansList.add(new Plan("Get married" , "desc" , 20000.0 , Calendar.getInstance().getTime()));
        plansList.add(new Plan("University" , "desc" , 700.0 , Calendar.getInstance().getTime()));
        plansList.add(new Plan("Clothes" , "desc" , 1000.0 , Calendar.getInstance().getTime()));
        plansList.add(new Plan("Travel" , "desc" , 2000.0 , Calendar.getInstance().getTime()));
        plansList.add(new Plan("Summer vacation" , "desc" , 1200.0 , Calendar.getInstance().getTime()));
        plansList.add(new Plan("Sleep" , "desc" , 0.0 , Calendar.getInstance().getTime()));
   */
    }

    public static void addPlan(String title, String desc, double budget, Date deadline)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PlansTable.colDeadline,DateStringFormatter.DateToString(deadline));
        contentValues.put(PlansTable.colDescription,desc);
        contentValues.put(PlansTable.colNeededMoney,budget);
        contentValues.put(PlansTable.colTitle,title);

        long ID = databaseManager.insert(PlansTable.TableName, contentValues);

        if(ID > 0) {
            plansList.add(new Plan((int)ID, title,desc,budget,deadline));
        }
    }

    public static void editPlan(int ID , String title, String desc, double budget, Date deadline)
    {
        String selection = PlansTable.colID + " = ? ";
        String[] selectionArg = {String.valueOf(ID)};

        ContentValues contentValues = new ContentValues();

        contentValues.put(PlansTable.colTitle,title);
        contentValues.put(PlansTable.colDescription,desc);
        contentValues.put(PlansTable.colNeededMoney,budget);
        contentValues.put(PlansTable.colDeadline,DateStringFormatter.DateToString(deadline));

        int c = databaseManager.updateEntries(PlansTable.TableName , contentValues,selection,selectionArg);

        if(c > 0) {

            for (int i = 0; i < plansList.size(); i++) {
                if (plansList.get(i).getPlanID() == ID) {
                    plansList.get(i).setTitle(title);
                    plansList.get(i).setDeadline(deadline);
                    plansList.get(i).setDescription(desc);
                    plansList.get(i).setMoneyNeeded(budget);
                    return;
                }
            }

        }
    }

    public static void deletePlan(Plan plan)
    {
        String selection = PlansTable.colID + " = ? ";
        String[] selectionArg = {String.valueOf(plan.getPlanID())};

        int c = databaseManager.deleteEntries(PlansTable.TableName , selection,selectionArg);

        for (int i = 0 ; i < plansList.size() ; i++)
        {
            if (plansList.get(i).getTitle().equalsIgnoreCase(plan.getTitle()))
            {
                plansList.remove(i);
                return;
            }
        }
    }

}
