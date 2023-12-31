package com.example.program_01.Database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.program_01.Controllers.Session;
import com.example.program_01.Models.Business;
import com.example.program_01.Models.Service;

import java.util.ArrayList;

public class ServiceDatabase
{
    Database ctx;

    public ServiceDatabase(Context c)
    {
        // Create new database instance here.
        ctx = new Database(c);
    }

    public static void create(SQLiteDatabase db)
    {
        //ORDER: serviceId -> businessId-> serviceType -> serviceDesc
        //db.execSQL("CREATE TABLE " + DatabaseVaribles.SERVICE_TABLE + " (serviceId primary key auto_increment, businessId, serviceType, serviceDesc, foreign key (businessId) references business_table (email));");
        String command = "CREATE TABLE " + DatabaseVaribles.SERVICE_TABLE + " (serviceId INTEGER PRIMARY KEY AUTOINCREMENT, businessId TEXT NOT NULL, serviceType TEXT NOT NULL, serviceDesc TEXT NOT NULL, FOREIGN KEY (businessId) REFERENCES " + DatabaseVaribles.BUSINESS_TABLE + " (email));";
        db.execSQL(command);
        Log.d("DEBUG", "Created service table");
    }

    public static void clean(SQLiteDatabase db)
    {
        //Drop Service table
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseVaribles.SERVICE_TABLE + ";");
    }

    @SuppressLint("Range")
    public ArrayList<Service> getAllServicesUnderBusiness(String e)
    {
        ArrayList<Service> listOfServices = new ArrayList<Service>();

        //Query to select all services where businessId == the given email
        String selectQuery = "SELECT * FROM " + DatabaseVaribles.SERVICE_TABLE + " WHERE businessId = '" + e + "';";

        SQLiteDatabase db = ctx.getReadableDatabase(); //Get readable
        Cursor cursor = db.rawQuery(selectQuery, null);

        int sId;
        String bId;
        String sType;
        String sDesc;

        if (cursor.moveToFirst()) //If there was something there
        {
            do
            {
                //Get the information
                sId = cursor.getInt(cursor.getColumnIndex("serviceId"));
                bId = cursor.getString(cursor.getColumnIndex("businessId"));
                sType = cursor.getString(cursor.getColumnIndex("serviceType"));
                sDesc = cursor.getString(cursor.getColumnIndex("serviceDesc"));

                //Make a new Service object given the information (put in array)
                listOfServices.add(new Service(sId, bId, sType, sDesc));
            }
            while (cursor.moveToNext()); //Until there are none left

            db.close(); //CLOSE

            return listOfServices;
        }
        return new ArrayList<Service>();
    }

    public void createService(String bId, String sType, String sDesc) // Creates a service in the database with the given info
    {
        SQLiteDatabase db = ctx.getWritableDatabase();
        String command = "INSERT INTO " + DatabaseVaribles.SERVICE_TABLE + " (businessId, serviceType, serviceDesc) VALUES ('" + bId + "','" + sType + "','" + sDesc + "');";
        db.execSQL(command);
        db.close(); //CLOSE
    }

    public void updateService(String type, String description, int i) // Updates the service with the given info. ServiceId not included (primary key)
    {
        SQLiteDatabase db = ctx.getWritableDatabase();
        String updateCommand = "UPDATE " + DatabaseVaribles.SERVICE_TABLE + " SET businessId = '" + Session.getBusiness().getEmail() + "', serviceType = '" + type + "' , serviceDesc = '" + description + "' WHERE serviceId = '" + i + "';";
        db.execSQL(updateCommand); //Execute
        db.close(); //CLOSE
    }

    public void deleteService(int i)
    {
        SQLiteDatabase db = ctx.getWritableDatabase();
        //Deletes the service with the given serviceId (i) from the business table
        db.execSQL("DELETE FROM " + DatabaseVaribles.SERVICE_TABLE + " WHERE serviceId = '" + i + "';");
        db.close(); //CLOSE
    }

    public void deleteALLServices() //I used this to just get rid of all services since I changed services
    {
        SQLiteDatabase db = ctx.getWritableDatabase();
        db.execSQL("DELETE FROM " + DatabaseVaribles.SERVICE_TABLE + ";");
        db.close(); //CLOSE
    }

    @SuppressLint("Range")
    public ArrayList<Service> getAllServicesByType(String serviceType)
    {
        SQLiteDatabase db = ctx.getReadableDatabase();
        ArrayList<Service> listOfServices = new ArrayList<Service>();

        String sql = "SELECT * FROM " + DatabaseVaribles.SERVICE_TABLE + " WHERE serviceType = '" + serviceType + "';";
        Cursor c = db.rawQuery(sql, null);

        if (c.moveToNext())
        {
            do
            {
                int sId = c.getInt(c.getColumnIndex("serviceId"));
                String bId = c.getString(c.getColumnIndex("businessId"));
                String sType = c.getString(c.getColumnIndex("serviceType"));
                String sDesc = c.getString(c.getColumnIndex("serviceDesc"));

                listOfServices.add(new Service(sId, bId, sType, sDesc));
            }
            while (c.moveToNext());
        }

        db.close();
        return listOfServices;

    }

    @SuppressLint("Range")
    public Service getServiceById(int serviceId)
    {
        SQLiteDatabase db = ctx.getReadableDatabase();
        Service _service = null;

        String sql = "SELECT * FROM " + DatabaseVaribles.SERVICE_TABLE + " WHERE serviceID = '" + serviceId + "';";
        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst())
        {
            int sId = c.getInt(c.getColumnIndex("serviceId"));
            String bId = c.getString(c.getColumnIndex("businessId"));
            String sType = c.getString(c.getColumnIndex("serviceType"));
            String sDesc = c.getString(c.getColumnIndex("serviceDesc"));

            _service = new Service(sId, bId, sType, sDesc);
        }

        return  _service;
    }
}
