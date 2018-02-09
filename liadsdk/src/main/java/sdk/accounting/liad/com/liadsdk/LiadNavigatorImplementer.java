package sdk.accounting.liad.com.liadsdk;

import android.content.Intent;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;

/**
 * Created by HMSPL on 22/12/17.
 */

class LiadNavigatorImplementer implements LiadNavigator, Serializable {

    private transient LiadNavigatorListener mlistener;

    public LiadNavigatorImplementer(LiadNavigatorListener listener) {

        mlistener = listener;
    }

    @Override
    public void navigateToSetupScreen(String emailID, String mno,String name) {

        Intent intent = new Intent(LiadSDKApplication.getLiadSDK().getApplicationContext(), NavigatorActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Constants.ActionKeys.NAVIGATE_TO_SETUP);
        intent.putExtra(Constants.BundleKeys.EMAIL, emailID);
        intent.putExtra(Constants.BundleKeys.MOBILE_NO,mno);
        intent.putExtra(Constants.BundleKeys.NAME,name);
        LiadSDKApplication.getLiadSDK().getApplicationContext().startActivity(intent);
        setNavigateListerToActivity();
    }

    @Override
    public void navigateToLoginScreen(String emailID) {

        Intent intent = new Intent(LiadSDKApplication.getLiadSDK().getApplicationContext(), NavigatorActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Constants.ActionKeys.NAVIGATE_TO_LOGIN);
        intent.putExtra(Constants.BundleKeys.EMAIL, emailID);
        LiadSDKApplication.getLiadSDK().getApplicationContext().startActivity(intent);
        setNavigateListerToActivity();
    }

    void setNavigateListerToActivity(){

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                NavigatorActivity.getInstance().setNavigatorListener(mlistener);
            }
        }, 500);
    }

}

