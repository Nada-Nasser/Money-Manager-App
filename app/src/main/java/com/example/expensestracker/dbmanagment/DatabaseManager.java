package com.example.expensestracker.dbmanagment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.widget.Toast;

public class DatabaseManager
{
    private SQLiteDatabase sqLiteDatabase;

    private final String DatabaseName = "budgetdb";

    int DBVersion = 1;

    class DBHelper extends SQLiteOpenHelper
    {
        Context context;

        public DBHelper(Context context)
        {
            super(context, DatabaseName, null, DBVersion);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase)
        {
            sqLiteDatabase.execSQL(MonthlyExpensesTable.createMonthlyExpensesTableSQLQuery);
            sqLiteDatabase.execSQL(PlansTable.createPlansTableSQLQuery);
            sqLiteDatabase.execSQL(BudgetCategoriesTable.createBudgetCategoryTableSQLQuery);
            sqLiteDatabase.execSQL(TransactionsTable.createTransactionTableSQLQuery);

            Toast.makeText(context,"Table is created " , Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
        {
            sqLiteDatabase.execSQL(MonthlyExpensesTable.DropMonthlyExpensesTable);
            sqLiteDatabase.execSQL(PlansTable.DropPlansTable);
            sqLiteDatabase.execSQL(TransactionsTable.DropTransactionsTable);
            sqLiteDatabase.execSQL(BudgetCategoriesTable.DropBudgetCategoriesTable);

            onCreate(sqLiteDatabase);
        }
    }

    public DatabaseManager(Context context)
    {
        DBHelper dbHelper = new DBHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public long insert(String tableName , ContentValues contentValues)
    {
        return sqLiteDatabase.insert(tableName,null,contentValues);
    }

    public Cursor queryTable(String tableName , String[] projection , String Selection , String[] SelectionArguments ,
                             String GroupBy, String having, String sortOrder)
    {
        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables(tableName);

        Cursor cursor = sqLiteQueryBuilder.query(sqLiteDatabase,projection,Selection,SelectionArguments
                ,GroupBy,having,sortOrder);

        return cursor;
    }

    public int deleteEntries(String tableName , String Selection , String[] SelectionArguments)
    {
        int count = sqLiteDatabase.delete(tableName,Selection,SelectionArguments);

        return count;
    }

    public int updateEntries(String tableName , ContentValues values , String Selection , String[] SelectionArg)
    {
        int count = sqLiteDatabase.update(tableName,values,Selection,SelectionArg);

        return count;
    }
}
