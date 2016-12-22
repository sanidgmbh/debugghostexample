package demo.app.android.sanid.com.debugghostexample;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

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
    }

    @Override
    protected void onStart() {
        super.onStart();

        getLoaderManager().restartLoader(0, null, this);
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
