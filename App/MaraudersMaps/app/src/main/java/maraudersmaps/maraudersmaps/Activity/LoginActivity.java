package maraudersmaps.maraudersmaps.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import maraudersmaps.maraudersmaps.Globals;

public class LoginActivity extends FragmentActivity implements
        GoogleApiClient.OnConnectionFailedListener, OnClickListener
{
    SignInButton signInButton;
    GoogleApiClient mGoogleApiClient;

    /**
     * Check if we have userId in the sharedPreference.
     * If yes then check the internet connection along with the userId validity
     * with server. If no connection stay idle. If not valid userId clear the
     * userId and ask to login. If userId not in sharedPreference then
     * ask to login.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        setContentView(layout);

        SharedPreferences preference = getSharedPreferences(Globals.PREFERENCE_NAME, MODE_PRIVATE);
        int userId = preference.getInt(Globals.USER_ID, 0);

        if (userId != 0)
        {
            // check the existence of user
        }
        else
        {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();

            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this , this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();

            signInButton = new SignInButton(this);
            signInButton.setSize(SignInButton.SIZE_STANDARD);
            signInButton.setScopes(gso.getScopeArray());
            layout.addView(signInButton);
            signInButton.setOnClickListener(this);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(View v) {
        if (v == signInButton)
        {
            signIn();
        }
    }

    private void signIn()
    {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, Globals.RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Globals.RC_SIGN_IN)
        {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result)
    {
        Log.d(Globals.TAG, "handleSignInResult:" + result.isSuccess());
        Log.d(Globals.TAG, " " + result.getSignInAccount().getId() + " " +
                result.getSignInAccount().getEmail());
    }
}

