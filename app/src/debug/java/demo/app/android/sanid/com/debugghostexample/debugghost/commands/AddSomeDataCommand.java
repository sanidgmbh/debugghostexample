package demo.app.android.sanid.com.debugghostexample.debugghost.commands;

import android.content.ContentValues;
import android.content.Context;

import com.sanid.lib.debugghost.commands.AbstractGhostCommand;

import demo.app.android.sanid.com.debugghostexample.content.MyContentProvider;
import demo.app.android.sanid.com.debugghostexample.content.tables.DemoTable;

public class AddSomeDataCommand extends AbstractGhostCommand {

    public AddSomeDataCommand(Context context, String label, String key, String value) {
        super(context, label, key, value);
    }

    @Override
    public void execute(String data) {

        String desc = "without parameter";
        if (data != null && data.trim().length() > 0) {
            desc = data;
        }

        ContentValues values = new ContentValues(2);
        values.put(DemoTable.COLUMN_NAME, "Some data");
        values.put(DemoTable.COLUMN_DESC, "Inserted via command " + desc);

        mContext.getContentResolver().insert(MyContentProvider.DEMO_CONTENT_URI, values);

    }
}
