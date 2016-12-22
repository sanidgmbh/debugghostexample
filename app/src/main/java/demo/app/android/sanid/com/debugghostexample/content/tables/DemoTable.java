package demo.app.android.sanid.com.debugghostexample.content.tables;

import android.database.sqlite.SQLiteDatabase;


public class DemoTable {

    public static final String TABLE_NAME = "demo";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "demo_name";
    public static final String COLUMN_DESC = "demo_desc";


    public static final String[] FULL_PROJECTION = new String[] {
            COLUMN_ID,
            COLUMN_NAME,
            COLUMN_DESC
    };

    private static final String DATABASE_DEMO_CREATE = "create table "
            + TABLE_NAME
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text, "
            + COLUMN_DESC + " text "
            + ");";


    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_DEMO_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        // nothing to do in demo
    }
}
