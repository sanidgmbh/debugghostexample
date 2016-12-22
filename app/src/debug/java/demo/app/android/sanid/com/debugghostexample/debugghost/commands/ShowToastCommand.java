package demo.app.android.sanid.com.debugghostexample.debugghost.commands;

import android.content.Context;
import android.widget.Toast;

import com.sanid.lib.debugghost.commands.AbstractGhostCommand;

/**
 * Created by norbertmoehring on 21/12/2016.
 */

public class ShowToastCommand extends AbstractGhostCommand {

    public ShowToastCommand(Context context, String label, String key, String value) {
        super(context, label, key, value);
    }

    @Override
    public void execute(String s) {
        Toast.makeText(mContext, "'ShowToastCommand' command!", Toast.LENGTH_SHORT).show();
    }
}
