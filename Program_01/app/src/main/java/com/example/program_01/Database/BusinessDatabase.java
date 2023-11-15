package com.example.program_01.Database;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.program_01.Models.Business;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BusinessDatabase
{
    Database ctx;

    public BusinessDatabase(Database c)
    {
        ctx = c;
    }

    public void create(SQLiteDatabase db)
    {
        Log.d("Database", ctx.getDatabaseName());
        //SQLiteDatabase db = ctx.getWritableDatabase(); //Get writable
        //ORDER: email -> password -> name -> number
        db.execSQL("CREATE TABLE " + DatabaseVaribles.BUSINESS_TABLE + " (email TEXT PRIMARY KEY NOT NULL, password TEXT NOT NULL, name TEXT NOT NULL, number TEXT NOT NULL);");
        db.close(); //CLOSE
    }

    public void clean()
    {
        SQLiteDatabase db = ctx.getWritableDatabase(); //Get writable
        //Drop Business table
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseVaribles.BUSINESS_TABLE + ";");
        db.close(); //CLOSE
    }

    @SuppressLint("Range")
    public ArrayList<Business> getAllBusinesses()
    {
        //Temp arraylist to store all the businesses (will be returned)
        ArrayList<Business> listOfBusinesses = new ArrayList<Business>();

        //Query to select all
        String selectQuery = "SELECT * FROM " + DatabaseVaribles.BUSINESS_TABLE + ";";

        SQLiteDatabase db = ctx.getReadableDatabase(); //Get readable
        //Give the cursor the query
        Cursor cursor = db.rawQuery(selectQuery, null);

        //ORDER: email -> password -> name -> number
        String e;
        String p;
        String n;
        String num;

        if (cursor.moveToFirst()) //If there was something there
        {
            do
            {
                //Get the information
                e = cursor.getString(cursor.getColumnIndex("email"));
                p = cursor.getString(cursor.getColumnIndex("password"));
                n = cursor.getString(cursor.getColumnIndex("name"));
                num = cursor.getString(cursor.getColumnIndex("number"));

                //Make a new Business object given the information and store it in the temp array
                listOfBusinesses.add(new Business(e, p, n, num));
            }
            while (cursor.moveToNext()); //Until there are none left
        }

        db.close(); //CLOSE

        return listOfBusinesses; //Return the arraylist that is now filled with every business in the business table
    }

    @SuppressLint("Range")
    public Business getBusinessByEmail(String e) //Returns the business with given email
    {
        SQLiteDatabase db = ctx.getReadableDatabase(); //Get readable
        //Query to select the business with the given email (e)
        String query = "SELECT * FROM " + DatabaseVaribles.BUSINESS_TABLE + " WHERE email = '" + e + "';";
        //Cursor to step through that business
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) //If there was a business with that email
        {
            //Return a new object of type Business with the information that the cursor reads
            return new Business(cursor.getString(cursor.getColumnIndex("email")), cursor.getString(cursor.getColumnIndex("password")), cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("number")));
        } //Otherwise return null
        return null;
    }

    public void createBusiness(Business b) // Creates a business in the database with the given info from the Business object b that was caught
    {
        SQLiteDatabase db = ctx.getWritableDatabase(); //Get writable
        //Inserts a new business into the business table with the values from the Business object b that was caught
        db.execSQL("INSERT INTO " + DatabaseVaribles.BUSINESS_TABLE + " VALUES ('" + b.getEmail() + "','" + b.getPassword() + "','" + b.getName() + "','" + b.getNumber() + "');");
        db.close(); //CLOSE
    }

    public void updateBusiness(Business b) // Updates the business with the given info from the Business object b that was caught. Email not included (primary key)
    {
        SQLiteDatabase db = ctx.getWritableDatabase();
        //Query to update the password, name, and number of the business that has the email b.getEmail()
        String updateCommand = "UPDATE " + DatabaseVaribles.BUSINESS_TABLE + " SET pasword = '" + b.getPassword() + "' , name = '" + b.getName() + "' , number = '" + b.getNumber() + "' WHERE email = '" + b.getEmail() + "';";
        db.execSQL(updateCommand); //Execute
        db.close(); //CLOSE
    }

    public void deleteBusiness(String e)
    {
        SQLiteDatabase db = ctx.getWritableDatabase(); //Get writable
        //Deletes the business with the given email (e) from the business table
        db.execSQL("DELETE FROM " + DatabaseVaribles.BUSINESS_TABLE + " WHERE email = '" + e + "';");
        db.close(); //CLOSE
    }
}
