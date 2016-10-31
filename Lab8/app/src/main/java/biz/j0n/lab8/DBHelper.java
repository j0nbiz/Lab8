package biz.j0n.lab8;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * DBHelper.class
 * is an SQLiteOpenHelper class
 * It is a simple implementation, if you use it use it as a base only you must add a lot of functionality
 * It implements  all of the CRUD methods
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String TAG = "DBHELP";
	/*
	 * All of the fields here define the database This example is a simple class
	 * so there are no other fields
	 */

    // table name
    public static final String TABLE_DINOS = "grades";
    // database field names (COLUMN_
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_INFO = "info";
    public static final String COLUMN_ICON_IMG_ID = "icon_img_id";
    public static final String COLUMN_IMG_ID = "img_id";
    // file name
    private static final String DATABASE_NAME = "dinos.db";
    // if the version number is increased the onUpdate() will be called
    private static final int DATABASE_VERSION = 1;
    // static instance to share DBHelper
    private static DBHelper dbh = null;
    // Database creation raw SQL statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_DINOS + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_NAME
            + " text not null, " + COLUMN_INFO + " text not null, "
            + COLUMN_ICON_IMG_ID + " integer not null, " + COLUMN_IMG_ID
            + " integer not null" + ");";

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DBHelper getDBHelper(Context context) {
		/*
		 * Use the application context, which will ensure that you don't
		 * accidentally leak an Activity's context. See this article for more
		 * information: http://bit.ly/6LRzfx
		 */
        if (dbh == null) {
            dbh = new DBHelper(context.getApplicationContext());
            Log.i(TAG, "getDBHelper, dbh == null");
        }
        Log.i(TAG, "getDBHelper()");
        return dbh;
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
		/*
		 * this is one of the few places where it is not an horrific idea to use
		 * raw SQL.
		 */
        database.execSQL(DATABASE_CREATE);
        Log.i(TAG, "onCreate()");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, DBHelper.class.getName() + "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DINOS);
        onCreate(db);
        Log.i(TAG, "onUpgrade()");
    }

    @Override
    public void onOpen(SQLiteDatabase database) {
        Log.i(TAG, "onOpen()");
    }

    public long insertNewDino(String name, String info,
                                 int icon_img_id, int img_id) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_INFO, info);
        cv.put(COLUMN_ICON_IMG_ID, icon_img_id);
        cv.put(COLUMN_IMG_ID, img_id);

        long code = getWritableDatabase().insert(TABLE_DINOS, null, cv);

        return code;
    }

    public Cursor getDinos() {
        return getReadableDatabase().query(TABLE_DINOS, null, null, null,
                null, null, null);
    }
}