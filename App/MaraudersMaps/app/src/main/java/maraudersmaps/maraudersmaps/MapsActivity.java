package maraudersmaps.maraudersmaps;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import maraudersmaps.maraudersmaps.Service.PushLocation;

public class MapsActivity extends FragmentActivity
{
    public static final String TAG = MapsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        startService(new Intent(this, PushLocation.class));
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
