package com.example.program_01.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper
{
    public UsersDatabase users;
    public BusinessDatabase businesses;

    public Database(Context ctx)
    {
        super(ctx, DatabaseVaribles.DB_NAME, null, DatabaseVaribles.DB_VERSION);
        users = new UsersDatabase(this);
        businesses = new BusinessDatabase(this);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        users.create();
        businesses.create();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        // Drop and clean out the tables.
        users.clean();
        businesses.clean();

        // Recall onCreate function to restructure database.
        onCreate(sqLiteDatabase);
    }
}
