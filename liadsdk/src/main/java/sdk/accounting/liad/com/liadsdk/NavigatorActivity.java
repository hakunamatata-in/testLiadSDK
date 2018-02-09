package sdk.accounting.liad.com.liadsdk;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class NavigatorActivity extends AppCompatActivity {

    private LiadNavigatorListener mNavigator;

    private static final String LIAD_LOGIN_ACTIVITY = "com.liad.accounting.module.user.management.LoginActivity";
    private static final String LIAD_SETUP_ACTIVITY = "com.liad.accounting.module.user.setup.SetupActivity";
    private static final String LIAD_BUNDLE = "com.liad.accounting";
    private static final String LIAD_MARKET_URL = "market://details?id=com.liad.accounting";
    private static String action = "";
    private static Bundle activitybundle;
    private static Activity mcontext;

    static NavigatorActivity mInstance;
    private BroadcastReceiver br;

    public static NavigatorActivity getInstance() {

        return mInstance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInstance = this;
        action = getIntent().getAction();
        activitybundle = getIntent().getExtras();
        mcontext = this;
        launchingLiadActivities();

    }

    void launchingLiadActivities() {

        if (getPackageManager().getLaunchIntentForPackage(LIAD_BUNDLE) != null) {
            // We found the activity now start the activity
            Intent intent = new Intent();
            intent.setAction(Constants.ActionKeys.ACTIVITY_FROM_SDK);
            if (null != getIntent().getAction() && getIntent().getAction().equals(Constants.ActionKeys.NAVIGATE_TO_LOGIN)) {
                intent.setClassName(LIAD_BUNDLE, LIAD_LOGIN_ACTIVITY);
            } else if (null != getIntent().getAction() && getIntent().getAction().equals(Constants.ActionKeys.NAVIGATE_TO_SETUP)) {
                intent.setClassName(LIAD_BUNDLE, LIAD_SETUP_ACTIVITY);
            }
            intent.putExtras(getIntent().getExtras());
            startActivityForResult(intent, 111);
        } else {
            // Bring user to the market or let them choose an app?
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse(LIAD_MARKET_URL));
            startActivityForResult(intent, 222);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 111) {

            String orgid = "";
            String authtoken = "";
            if (null != data.getExtras()) {

                if (data.getExtras().containsKey("orgid")) {
                    orgid = (String) data.getExtras().get("orgid");
                }
                if (data.getExtras().containsKey("authtoken")) {
                    authtoken = (String) data.getExtras().get("authtoken");
                }
            }
            mNavigator.linkedResponse(authtoken, orgid);
        }
        finish();
    }

    public void setNavigatorListener(LiadNavigatorListener listener) {
        mNavigator = listener;
    }


    public static class InstallationReciver extends BroadcastReceiver {

        public InstallationReciver() {

        }

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO: This method is called when the BroadcastReceiver is receiving
            // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
            if (context.getPackageManager().getLaunchIntentForPackage(LIAD_BUNDLE) != null) {
                // We found the activity now start the activity
                Intent nintent = new Intent();
                nintent.setAction(Constants.ActionKeys.ACTIVITY_FROM_SDK);
                if (null != action && action.equals(Constants.ActionKeys.NAVIGATE_TO_LOGIN)) {
                    nintent.setClassName(LIAD_BUNDLE, LIAD_LOGIN_ACTIVITY);
                } else if (null != action && action.equals(Constants.ActionKeys.NAVIGATE_TO_SETUP)) {
                    nintent.setClassName(LIAD_BUNDLE, LIAD_SETUP_ACTIVITY);
                }
                nintent.putExtras(activitybundle);
                mcontext.startActivityForResult(nintent, 111);
            } else {
                // Bring user to the market or let them choose an app?
                Intent nintent = new Intent(Intent.ACTION_VIEW);
                nintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                nintent.setData(Uri.parse(LIAD_MARKET_URL));
                mcontext.startActivityForResult(nintent, 222);
            }
        }
    }
}





