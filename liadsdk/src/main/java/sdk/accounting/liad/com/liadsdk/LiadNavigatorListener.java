package sdk.accounting.liad.com.liadsdk;

/**
 * Created by HMSPL on 22/12/17.
 */

public interface LiadNavigatorListener {

    public void signupResponse(String emailID);

    public void linkedResponse(String authToken, String orgId);
}
