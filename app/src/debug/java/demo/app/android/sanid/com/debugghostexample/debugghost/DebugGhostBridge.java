package demo.app.android.sanid.com.debugghostexample.debugghost;


import android.content.Context;

import com.sanid.lib.debugghost.AbstractDebugGhostBridge;

import demo.app.android.sanid.com.debugghostexample.debugghost.commands.AddSomeDataCommand;
import demo.app.android.sanid.com.debugghostexample.debugghost.commands.ShowToastCommand;
import demo.app.android.sanid.com.debugghostexample.debugghost.commands.ShowToastCommandDemo;

public class DebugGhostBridge extends AbstractDebugGhostBridge {

    public DebugGhostBridge(Context context, String databaseName, int databaseVersion) {
        super(context, databaseName, databaseVersion);
        initCommands();
    }

    private void initCommands() {
        addGhostCommand(new ShowToastCommand(mContext, "Send scary toast", "show_toast", " "));
        addGhostCommand(new ShowToastCommandDemo(mContext, "Send demo toast", "show_toast_test", "[]"));

        addGhostCommand(new AddSomeDataCommand(mContext, "Insert db data", "insert_data", "[my default-value]"));
//        addGhostCommand(new AddSomeDataCommand(mContext, "Add some data with default value", "add_some_default_value", "[default]"));
//        addGhostCommand(new AddSomeDataCommand(mContext, "Add some data with value", "add_some_data_value", "[]"));
    }
}