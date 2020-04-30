/*
Project: Lab6Silva
Purpose Details: SQLLite
Course: IST 402
Author: Nimesh Silva
Date Developed: October 18th, 2019
Last Date Changed: October 18th, 2019
Revision: v1
*/

package com.example.lab6silva;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

//Add SQLite database classes
import android.database.Cursor;
import android.database.sqlite.*;
import android.util.Log;
import android.content.ContentValues;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declare the db object variable to have proper scope
        SQLiteDatabase db = null;
        try
        {
            //MODE_PRIVATE = only this application
            db = openOrCreateDatabase("MyDB", MODE_PRIVATE, null);

            //Remove the table and start fresh
            db.execSQL("DROP TABLE IF EXISTS myTable");

            //Create the Table
            db.execSQL("CREATE TABLE IF NOT EXISTS " + "myTable(LastName VARCHAR, FirstName VARCHAR, Age INT(3));");

            //Insert a Record
            db.execSQL("INSERT INTO myTable VALUES ('Silva', 'Nimesh', 21);");

            ContentValues cv = new ContentValues();
            //cv.put(key,values)
            cv.put("LastName", "Silva");
            cv.put("FirstName", "Nimesh");
            cv.put("Age", "21");

            //db.insert(table, nullColumnHack, values)
            db.insert("myTable", null, cv);

            //Select a record and set the cursor object
            Cursor c = db.rawQuery("Select * FROM myTable", null);

            //Move to the first record in the table
            c.moveToFirst();

            //Show it in the log
            Log.d("Nimesh:", c.getString(c.getColumnIndex("FirstName")));


            //db.update(table, values, whereClause, whereArgs)
            db.update("myTable", cv, "FirstName=?", new String[]{"Nimesh"});
            c = db.rawQuery("Select * FROM myTable", null);
            c.moveToFirst();
            Log.d("Silva:", c.getString(c.getColumnIndex("LastName")));

            db.execSQL("DELETE FROM myTable WHERE FirstName = 'Nimesh'");
            c = db.rawQuery("Select * FROM myTable", null);
            c.moveToFirst();
            Log.d("Nimesh:", c.getString(c.getColumnIndex("FirstName")));

            //db.delete(table, whereClause, whereArgs)
            db.delete("myTable", "FirstName=?", new String [] {"Nimesh"});

            c = db.rawQuery("Select * FROM myTable", null);
            c.moveToFirst();
            Log.d("Nimesh:", c.getString(c.getColumnIndex("FirstName")));


            //db.insert(table, nullColumnHack, values)
            db.insert("myTable", null, cv);


            //Update record
            db.execSQL("UPDATE myTable SET LastName = 'Silva' WHERE FirstName = 'Nimesh';");

            c = db.rawQuery("Select * FROM myTable", null);
            c.moveToFirst();
            Log.d("Silva:", c.getString(c.getColumnIndex("LastName")));

        }
        catch(Exception e)
        {
            Log.d("Error:", e.toString());
        }
        finally {
            //This will execute always
            db.close();
        }
    }
}
