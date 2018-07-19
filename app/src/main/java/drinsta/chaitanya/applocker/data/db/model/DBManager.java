package drinsta.chaitanya.applocker.data.db.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static drinsta.chaitanya.applocker.data.db.model.DatabaseHelper.KEY;
import static drinsta.chaitanya.applocker.data.db.model.DatabaseHelper.VALUE;
import static drinsta.chaitanya.applocker.data.db.model.DatabaseHelper._ID;

public class DBManager{
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;
    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String key, String value) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(KEY, key);
        contentValue.put(VALUE, value);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public String Search(String Key) {
        String[] columns = new String[] {  VALUE };
        String value="";
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, KEY + "=?",
                new String[] { String.valueOf(Key) }, null, null, null, null);
        if (cursor != null&& cursor.getCount() > 0) {
            cursor.moveToFirst();
            value =  cursor.getString(cursor.getColumnIndex(VALUE));
        }
        return value;
    }

    public int update(long _id, String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY, name);
        contentValues.put(VALUE, desc);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, _ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, _ID + "=" + _id, null);

    }
    public int Check (String Key) {

        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            do {
                if (Key.equals(cursor.getString(cursor.getColumnIndex(KEY))))
                {return Integer.parseInt(cursor.getString(cursor.getColumnIndex(_ID))); }

            } while (cursor.moveToNext());
        }
        return -1;
    }

    public void clearData(){

        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            ContentValues contentValues = new ContentValues();
            cursor.moveToFirst();
            do {
                contentValues.put(KEY, (cursor.getString(cursor.getColumnIndex(KEY))));
                contentValues.put(VALUE,"");
                database.update(DatabaseHelper.TABLE_NAME, contentValues,null, null);

            } while (cursor.moveToNext());
        }

    }

}

