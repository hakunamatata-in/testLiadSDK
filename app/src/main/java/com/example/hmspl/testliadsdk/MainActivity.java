package com.example.hmspl.testliadsdk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import sdk.accounting.liad.com.liadsdk.LiadNavigator;
import sdk.accounting.liad.com.liadsdk.LiadNavigatorListener;
import sdk.accounting.liad.com.liadsdk.LiadSDK;


public class MainActivity extends AppCompatActivity implements LiadNavigatorListener {

    private LiadSDK mLiadSDK;
    private LiadNavigator mnavigator;
    private EditText etmail;
    private TextView txtOrgId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mnavigator = LiadSDK.getInstance().getNavigator(this);
        etmail = findViewById(R.id.etmail);
        txtOrgId = findViewById(R.id.txtOrgId);
        findViewById(R.id.btnlink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mnavigator.navigateToLoginScreen(etmail.getText().toString());
            }
        });
        findViewById(R.id.btnregister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mnavigator.navigateToSetupScreen(etmail.getText().toString(),"9879879878","testsdk");
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void signupResponse(String emailID) {

            Log.d("emailID", emailID);
    }

    @Override
    public void linkedResponse(String authToken, String orgId) {


        txtOrgId.setText("orgId:"+orgId);
        Log.d("authToken", authToken);
        Log.d("orgId", orgId);
    }
}
