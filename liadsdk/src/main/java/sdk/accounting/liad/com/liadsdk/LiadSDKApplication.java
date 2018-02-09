package sdk.accounting.liad.com.liadsdk;

import android.app.Application;

/**
 * Created by HMSPL on 22/12/17.
 */

public class LiadSDKApplication extends Application {

    private static LiadSDKApplication mliadSDKApplication;

    public static LiadSDKApplication getLiadSDK(){
        return mliadSDKApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mliadSDKApplication = LiadSDKApplication.this;
    }

}
