package edu.cvtc.mwalley.sunsigns_mwalley.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Helper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Person.db";
    private static final String DB_TABLE = "Person_Table";

    // columns
    private static final String ID = "ID";
    private static final String NAME = "NAME";


    // create query
    private static final String CREATE_TABLE = "CREATE TABLE "+ DB_TABLE+ " ("+
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ NAME+ "TEXT" +")";

    public Helper(Context context){
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int il) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);


        onCreate(sqLiteDatabase);
    }

    // create method to insert the data
    public boolean insertData(String name) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,name);

        long result = db.insert(DB_TABLE, null, contentValues);

        return result != -1; // if result is -1 then data won't insert
    }

    // create method to view the data
    public Cursor viewData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+DB_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }
}
