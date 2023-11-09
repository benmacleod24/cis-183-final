package com.example.program_01.Database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.program_01.Models.User;

public class UsersDatabase
{
    Database ctx;

    public UsersDatabase(Database ctx)
    {
        this.ctx = ctx;
    }

    public void create()
    {
        final String query = "CREATE TABLE " + DatabaseVaribles.USER_TABLE + " (email TEXT PRIMARY KEY NOT NULL, password TEXT NOT NULL);";
        SQLiteDatabase db = ctx.getReadableDatabase();
        db.execSQL(query);
    }

    public void clean()
    {
        final String query = "DROP TABLE " + DatabaseVaribles.USER_TABLE + ";";
        SQLiteDatabase db = ctx.getReadableDatabase();
        db.execSQL(query);
    }

    @SuppressLint("Range")
    public User getUserByEmail(String email)
    {
        // Readable database instance.
        SQLiteDatabase db = ctx.getReadableDatabase();

        // Query for finding the user by their email.
        String query = "SELECT * FROM " + DatabaseVaribles.USER_TABLE + " WHERE email = '" + email + "';";

        // Get the record from the database.
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst())
        {
            return new User(cursor.getString(cursor.getColumnIndex("email")), cursor.getString(cursor.getColumnIndex("password")));
        }

        // Return null here.
        return null;
    }


    public void create(String email, String password)
    {
        // Predefined query for inserting into the users database.
        String query = "INSERT INTO " + DatabaseVaribles.USER_TABLE + " (email, password) VALUES (";

        // Get a writeable database instance.
        SQLiteDatabase db = ctx.getWritableDatabase();

        // Execute insert query.
        db.execSQL(query + "'"+ email +"','" + password + "');");
    }
}
