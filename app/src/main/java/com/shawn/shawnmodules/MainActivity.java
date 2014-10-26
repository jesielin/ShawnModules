package com.shawn.shawnmodules;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.mustafaferhan.debuglog.DebugLog;
import com.shawn.graphics.drawable.AlphaCircleProgressDrawable;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv1 = (ImageView) findViewById(R.id.iv1);
        ImageView iv2 = (ImageView) findViewById(R.id.iv2);
        ImageView iv3 = (ImageView) findViewById(R.id.iv3);
        AlphaCircleProgressDrawable drawable1 = new AlphaCircleProgressDrawable(this,AlphaCircleProgressDrawable.RED_STYLE,0.5f);
        AlphaCircleProgressDrawable drawable2 = new AlphaCircleProgressDrawable(this,AlphaCircleProgressDrawable.YELLOW_STYLE,0.2f);
        AlphaCircleProgressDrawable drawable3 = new AlphaCircleProgressDrawable(this, AlphaCircleProgressDrawable.BLUE_STYLE,0.8f);
        iv1.setBackground(drawable1);
        iv2.setBackground(drawable2);
        iv3.setBackground(drawable3);

//        prepareAnimator(drawable1);
//        prepareAnimator(drawable2);
//        prepareAnimator(drawable3);

        DebugLog.e("aaaaaaaaa");
    }

    private void prepareAnimator(AlphaCircleProgressDrawable drawable) {
        Animator animation = ObjectAnimator.ofFloat(drawable, AlphaCircleProgressDrawable.PROGRESS_PROPERTY, 0.0f, 1.0f);
        animation.setDuration(3600);
        animation.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
