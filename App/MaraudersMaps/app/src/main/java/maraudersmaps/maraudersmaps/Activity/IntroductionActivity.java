package maraudersmaps.maraudersmaps.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import maraudersmaps.maraudersmaps.Globals;
import maraudersmaps.maraudersmaps.R;

public class IntroductionActivity extends AppCompatActivity {

    /**
     * Shows the user some introductory slide.
     * At the end of introduction, we set the firstTime flag as false.
     * After this the user is taken to the LoginActivity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        ViewPager viewPager = new ViewPager(this);
        viewPager.setAdapter(new CustomPagerAdapter(this));
        layout.addView(viewPager);

        setContentView(layout);
    }

    public class CustomPagerAdapter extends PagerAdapter {

        private Context mContext;

        public CustomPagerAdapter(Context context) {
            mContext = context;
        }

        @Override
        public Object instantiateItem(ViewGroup collection, int position) {
            LinearLayout layout = new LinearLayout(mContext);

            Log.i(Globals.TAG, "Position is " + position);
            if (position == getCount() - 1)
            {
                Log.i(Globals.TAG, "Setting button for the last view");
                Button button = new Button(mContext);
                button.setText("Go to Login Page");
                button.setTextSize(20);
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        SharedPreferences preference = getSharedPreferences(Globals.PREFERENCE_NAME, MODE_PRIVATE);
                        if (preference.edit().putBoolean(Globals.FIRST_TIME, false).commit())
                        {
                            Log.i(Globals.TAG, "Setting first time as false");
                            startActivity(new Intent(mContext, LoginActivity.class));
                        }
                    }
                });
                layout.addView(button);
                Log.i(Globals.TAG, "Added button to view");
            }
            else
            {
                ImageView imageView = new ImageView(mContext);
                imageView.setImageResource(R.mipmap.i1);
                imageView.getAdjustViewBounds();
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                layout.addView(imageView);
            }

            collection.addView(layout);
            return layout;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Title";
        }

    }
}