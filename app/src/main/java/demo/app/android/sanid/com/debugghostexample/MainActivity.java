package demo.app.android.sanid.com.debugghostexample;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import demo.app.android.sanid.com.debugghostexample.content.MyContentProvider;
import demo.app.android.sanid.com.debugghostexample.content.tables.DemoTable;

public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

    private ListView mListView;
    private SimpleCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View empty = findViewById(R.id.tv_empty);
        mListView = (ListView)findViewById(R.id.list);
        mListView.setEmptyView(empty);

        mCursorAdapter = new SimpleCursorAdapter(getBaseContext(), android.R.layout.simple_list_item_2,
                null, new String[]{DemoTable.COLUMN_NAME, DemoTable.COLUMN_DESC},
                new int[]{android.R.id.text1, android.R.id.text2}, 0);

        mListView.setAdapter(mCursorAdapter);

        storeSomeSharedPrefs();
        storeSomeSharedPrefs2();
    }

    private void storeSomeSharedPrefs() {
        SharedPreferences preferences = getSharedPreferences("DEMO_SHARED_PREFS", MODE_PRIVATE);
        SharedPreferences.Editor e = preferences.edit();

        e.putInt("PREFS_MY_FIRST_INT", 1);
        e.putInt("PREFS_MY_SECOND_INT", 22);
        e.putString("PREFS_MY_STRING", "Hello there, I stored this text for DebugGhost!");
        e.putBoolean("PREFS_MY_BOOL_IS_TRUE", true);

        e.commit();
    }

    private void storeSomeSharedPrefs2() {
        SharedPreferences preferences = getSharedPreferences("ANOTHER_PACK_OF_SHARED_PREFS", MODE_PRIVATE);
        SharedPreferences.Editor e = preferences.edit();

        e.putInt("PREFS_MY_FIRST_INT", 1);
        e.putInt("PREFS_MY_SECOND_INT", 22);
        e.putFloat("PREFS_FLOAT", 2.2f);
        e.putLong("PREFS_lelelelong", 33l);
        e.putString("PREFS_MY_STRING", "Hello there, I stored this text for DebugGhost!");
        e.putBoolean("PREFS_MY_BOOL_IS_TRUE", true);

        Set<String> someLalaLandStuff = new HashSet<>();
        someLalaLandStuff.add("LalaOne");
        someLalaLandStuff.add("LalaTwo");
        someLalaLandStuff.add("LalaThree");
        e.putStringSet("PREFS_string_SET", someLalaLandStuff);

        Set<String> someLalaLandStuff2 = new LinkedHashSet<>();
        someLalaLandStuff2.add("LalaOne");
        someLalaLandStuff2.add("LalaTwo");
        someLalaLandStuff2.add("LalaThree");
        e.putStringSet("PREFS_string_SET_2", someLalaLandStuff);

        e.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        getLoaderManager().restartLoader(0, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences preferences = getSharedPreferences("DEMO_SHARED_PREFS", MODE_PRIVATE);

        Log.d("GhostPrefs", preferences.getString("PREFS_MY_STRING", "nothing set"));
        Log.d("GhostPrefs", String.valueOf(preferences.getInt("PREFS_MY_FIRST_INT", -1)));
        Log.d("GhostPrefs", String.valueOf(preferences.getInt("PREFS_MY_SECOND_INT", -1)));
        Log.d("GhostPrefs", String.valueOf(preferences.getBoolean("PREFS_MY_BOOL_IS_TRUE", false)));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(getBaseContext(),
                MyContentProvider.DEMO_CONTENT_URI, DemoTable.FULL_PROJECTION, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }
}
