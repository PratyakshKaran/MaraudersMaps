package maraudersmaps.maraudersmaps.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import maraudersmaps.maraudersmaps.Globals;
import maraudersmaps.maraudersmaps.R;

public class FirstActivity extends FragmentActivity
{
    public static final String TAG = "MaraudersMaps";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preference = getSharedPreferences(Globals.PREFERENCE_NAME, MODE_PRIVATE);
        boolean firstTime = preference.getBoolean("firstTime", true);

        if (firstTime)
        {
            Log.i(TAG, "Application being used for first time. Will take the user to introduction");
            startActivity(new Intent(this, IntroductionActivity.class));
        }
        else
        {
            Log.i(TAG, "Not the first time start. Checking if user is logged in.");
            boolean loggedIn = preference.getBoolean("loggedIn", false);
            if (loggedIn)
            {
                Log.i(TAG, "User already logged in. Taking to the home page.");
                startActivity(new Intent(this, HomeActivity.class));
            }
            else
            {
                Log.i(TAG, "Taking the user to log in page");
                startActivity(new Intent(this, LoginActivity.class));
            }
        }
        //startService(new Intent(this, PushLocation.class));
        setContentView(R.layout.activity_first);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
