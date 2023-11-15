package com.example.program_01.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper
{
    public Database(Context ctx)
    {
        super(ctx, DatabaseVaribles.DB_NAME, null, DatabaseVaribles.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        // Create the users table from this static method.
        UsersDatabase.create(sqLiteDatabase);
        BusinessDatabase.create(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        // Drop and clean out the tables.
        UsersDatabase.clean(sqLiteDatabase);
        BusinessDatabase.clean(sqLiteDatabase);

        // Recall onCreate function to restructure database.
        onCreate(sqLiteDatabase);
    }

    /**
     * DEBUG
     *
     * This is a simple debug tool that will print and display all
     * tables that we have in the database at this current moment.
     */
    public void printAllTables()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {
                Log.d("TABLE_NAME", c.getString(0).toString());
                c.moveToNext();
            }
        }

        db.close();
    }
}
