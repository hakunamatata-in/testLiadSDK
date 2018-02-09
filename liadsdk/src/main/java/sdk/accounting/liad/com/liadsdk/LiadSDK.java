package sdk.accounting.liad.com.liadsdk;

import android.content.Intent;
import android.util.Log;

import java.io.Serializable;

/**
 * Created by HMSPL on 22/12/17.
 */

public class LiadSDK {

    private static final LiadSDK ourInstance = new LiadSDK();

    public static LiadSDK getInstance() {
        return ourInstance;
    }

    private LiadSDK() {

    }

    public LiadNavigator getNavigator(LiadNavigatorListener listener){
        return new LiadNavigatorImplementer(listener);
    }

}
