package demo.app.android.sanid.com.debugghostexample.content;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import demo.app.android.sanid.com.debugghostexample.content.tables.DemoTable;

public class MyContentProvider extends ContentProvider {

    public static final String AUTHORITY = "demo.app.android.sanid.com.debugghostexample.provider";

    private static final int DEMO = 10;
    private static final int DEMO_ID = 20;
    private static final String DEMO_BASE_PATH = "demo";
    public static final Uri DEMO_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + DEMO_BASE_PATH);

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, DEMO_BASE_PATH, DEMO);
        sURIMatcher.addURI(AUTHORITY, DEMO_BASE_PATH + "/#", DEMO_ID);
    }

    private MyDatabaseHelper mDbHelper;
    private Context mContext;

    @Override
    public boolean onCreate() {

        mContext = getContext();
        mDbHelper = new MyDatabaseHelper(mContext);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.close();

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor = null;

        switch (sURIMatcher.match(uri)) {
            case DEMO:
                queryBuilder.setTables(DemoTable.TABLE_NAME);
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI");
        }

        if (cursor == null) {
            cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        }

        cursor.setNotificationUri(mContext.getContentResolver(), uri);

        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        long insertedId = -1;
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        switch (sURIMatcher.match(uri)) {
            case DEMO:
                insertedId = db.insert(DemoTable.TABLE_NAME, null, values);
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI");
        }

        mContext.getContentResolver().notifyChange(uri, null);

        return Uri.withAppendedPath(uri, String.valueOf(insertedId));
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int updateCount = -1;
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        switch (sURIMatcher.match(uri)) {
            case DEMO:
                updateCount = db.update(DemoTable.TABLE_NAME, values, selection, selectionArgs);
                break;
            case DEMO_ID:
                selection = DemoTable.COLUMN_ID + " = ? ";
                selectionArgs = new String[]{uri.getLastPathSegment()};
                updateCount = db.update(DemoTable.TABLE_NAME, values, selection, selectionArgs);
                break;
        }

        mContext.getContentResolver().notifyChange(DEMO_CONTENT_URI, null);

        return updateCount;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
