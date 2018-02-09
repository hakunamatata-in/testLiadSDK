package sdk.accounting.liad.com.liadsdk;

import android.content.Intent;
import android.util.Log;

import java.io.Serializable;

/**
 * Created by HMSPL on 22/12/17.
 */

public interface LiadNavigator {

    void navigateToSetupScreen(String emailID, String mno, String name);

    void navigateToLoginScreen(String emailID);
}
