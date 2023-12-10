package com.example.program_01.Database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.program_01.Models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDatabase
{
    Database db;

    public OrderDatabase(Context ctx)
    {
        db = new Database(ctx);
    }

    public static void create(SQLiteDatabase db)
    {
        String sql = "CREATE TABLE " + DatabaseVaribles.ORDER_TABLE + " (orderId INTEGER PRIMARY KEY AUTOINCREMENT, serviceId INTEGER NOT NULL, businessId TEXT, userId TEXT NOT NULL, FOREIGN KEY (serviceId) REFERENCES " + DatabaseVaribles.SERVICE_TABLE + " (serviceId) ON DELETE CASCADE, FOREIGN KEY (businessId) REFERENCES " + DatabaseVaribles.BUSINESS_TABLE + " (email) ON DELETE CASCADE, FOREIGN KEY (userId) REFERENCES " + DatabaseVaribles.USER_TABLE + " (email) ON DELETE CASCADE);";
        db.execSQL(sql);
    }

    public static void clean(SQLiteDatabase db)
    {
        //Drop Service table
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseVaribles.ORDER_TABLE + ";");
    }

    /**
     * Get all orders belonging to a business.
     * @param bizId ID of the business.
     * @return List of orders.
     */
    @SuppressLint("Range")
    public ArrayList<Order> geOrdersByBusinessId(String bizId)
    {
        ArrayList<Order> listOfOrders = new ArrayList<Order>();
        String sql = "SELECT * FROM " + DatabaseVaribles.ORDER_TABLE + " WHERE businessId = '" + bizId +"';";

        SQLiteDatabase _db = db.getReadableDatabase();
        Cursor cursor = _db.rawQuery(sql, null);

        if (cursor.moveToFirst())
        {
            do
            {
                int orderId = cursor.getInt(cursor.getColumnIndex("orderId"));
                int serviceId = cursor.getInt(cursor.getColumnIndex("serviceId"));
                String businessId = cursor.getString(cursor.getColumnIndex("businessId"));
                String userId = cursor.getString(cursor.getColumnIndex("userId"));

                listOfOrders.add(new Order(orderId, businessId, serviceId, userId));
            }
            while (cursor.moveToNext());
        }

        _db.close();
        return listOfOrders;
    }

    @SuppressLint("Range")
    public ArrayList<Order> getOrderByUser(String userId)
    {
        ArrayList<Order> listOfOrders = new ArrayList<Order>();
        String sql = "SELECT * FROM " + DatabaseVaribles.ORDER_TABLE + " WHERE userId = '" + userId +"';";

        SQLiteDatabase _db = db.getReadableDatabase();
        Cursor cursor = _db.rawQuery(sql, null);

        if (cursor.moveToFirst())
        {
            do
            {
                int orderId = cursor.getInt(cursor.getColumnIndex("orderId"));
                int serviceId = cursor.getInt(cursor.getColumnIndex("serviceId"));
                String businessId = cursor.getString(cursor.getColumnIndex("businessId"));
                String _userId = cursor.getString(cursor.getColumnIndex("userId"));

                listOfOrders.add(new Order(orderId, businessId, serviceId, _userId));
            }
            while (cursor.moveToNext());
        }

        _db.close();
        return listOfOrders;
    }

    /**
     * Create a new order record.
     * @param _order New order object.
     */
    public void createOrder(Order _order)
    {
        SQLiteDatabase _db = db.getWritableDatabase();
        String sql = "INSERT INTO " + DatabaseVaribles.ORDER_TABLE + " (serviceId, businessId, userId) VALUES ('" + _order.getServiceId() + "','" + _order.getBusinessId() + "','" + _order.getUserId() + "');";
        _db.execSQL(sql);
        _db.close();
    }

    public void deleteUserOrders(String userId)
    {
        SQLiteDatabase _db = db.getWritableDatabase();
        String query = "DELETE FROM " + DatabaseVaribles.ORDER_TABLE + " WHERE userId = '"+userId+"';";
        _db.execSQL(query);
        _db.close();
    }

    public void deleteOrder(int orderId)
    {
        SQLiteDatabase _db = db.getWritableDatabase();
        String query = "DELETE FROM " + DatabaseVaribles.ORDER_TABLE + " WHERE orderId = '"+orderId+"';";
        _db.execSQL(query);
        _db.close();
    }
}
