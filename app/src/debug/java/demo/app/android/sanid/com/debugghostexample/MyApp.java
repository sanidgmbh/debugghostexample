package demo.app.android.sanid.com.debugghostexample;

import demo.app.android.sanid.com.debugghostexample.content.MyDatabaseHelper;
import demo.app.android.sanid.com.debugghostexample.debugghost.DebugGhostBridge;

public class MyApp extends AbstractDebugGhostExampleApplication {

    private DebugGhostBridge mDebugGhostBridge;

    @Override
    public void onCreate() {
        super.onCreate();

        mDebugGhostBridge = new DebugGhostBridge(this, MyDatabaseHelper.DATABASE_NAME, MyDatabaseHelper.DATABASE_VERSION);
//        mDebugGhostBridge = new DebugGhostBridge(this, null, -1);
        mDebugGhostBridge.startDebugGhost();
    }
}
