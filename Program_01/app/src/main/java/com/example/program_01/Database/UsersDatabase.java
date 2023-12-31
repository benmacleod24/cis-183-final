package com.example.program_01.Database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.program_01.Models.User;

public class UsersDatabase
{
    // The database instance for this clas.
    Database ctx;
    BusinessDatabase bizDb;

    public UsersDatabase(Context _ctx)
    {
        // Create the database instance for user in this class.
        ctx = new Database(_ctx);
        //bizDb = new BusinessDatabase(_ctx);
    }

    // Static method for creating classes.
    public static void create(SQLiteDatabase _db)
    {
        final String query = "CREATE TABLE " + DatabaseVaribles.USER_TABLE + " (email TEXT PRIMARY KEY NOT NULL, password TEXT NOT NULL, firstName TEXT NOT NULL, lastName TEXT);";
        _db.execSQL(query);
    }

    public static void clean(SQLiteDatabase _db)
    {
        final String query = "DROP TABLE " + DatabaseVaribles.USER_TABLE + ";";
        _db.execSQL(query);
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
            return new User(cursor.getString(cursor.getColumnIndex("email")), cursor.getString(cursor.getColumnIndex("password")), cursor.getString(cursor.getColumnIndex("firstName")), cursor.getString(cursor.getColumnIndex("lastName")));
        }

        // Return null here.
        db.close();
        return null;
    }

    public void create(String email, String password, String firstName, String lastName)
    {
        // Predefined query for inserting into the users database.
        String query = "INSERT INTO " + DatabaseVaribles.USER_TABLE + " (email, password, firstName, lastName) VALUES (";


        //I had to comment the bizDb stuff out because it was throwing me an error.
        //Solution: Don't check if business email already exists here, instead check on the "CreateAccount" page.
        //Make an instance of bizDb on CreateAccount.java to do this instead of here
        // Business Email Already Exist.
        //if (bizDb.getBusinessByEmail(email) != null)
        //{
        //    return;
        //}

        // Get a writeable database instance.
        SQLiteDatabase db = ctx.getWritableDatabase();

        // Execute insert query.
        db.execSQL(query + "'"+ email +"','" + password + "','" + firstName + "','" + lastName + "');");
        db.close();
    }

    public boolean doesEmailExist(String email)
    {
        String query = "SELECT email FROM " + DatabaseVaribles.USER_TABLE + " WHERE email ='"+ email +"';";

        SQLiteDatabase db = ctx.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void deleteUser(String userId)
    {
        String query = "DELETE FROM " + DatabaseVaribles.USER_TABLE + " WHERE email = '" + userId +"';";
        SQLiteDatabase db = ctx.getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    public void updateUser(User _user)
    {
        SQLiteDatabase db = ctx.getWritableDatabase();
        String query = "UPDATE " + DatabaseVaribles.USER_TABLE + " SET password = '" + _user.getPassword() + "', firstName = '" + _user.getFirstName() + "', lastName = '" + _user.getLastName() + "' WHERE email = '" + _user.getEmail() + "';";
        db.execSQL(query);
        db.close();
    }
}
