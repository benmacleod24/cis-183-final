package com.example.program_01.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper
{
    public static final String DB_NAME = "homework_03_user_database";
    public static  final int DB_VERSION = 1;
    public static  final String TABLE_NAME = "users";
    public UsersDatabase users;

    public Database(Context ctx)
    {
        super(ctx, DB_NAME, null, DB_VERSION);
        users = new UsersDatabase(this);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        // Methods Here
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        // Methods Here
    }
}
