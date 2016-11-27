package com.example.asus.mycar.logoActivity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.asus.mycar.R;

public class LogoActivity extends AppCompatActivity {

    TextView myTextView;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo_activity);
        myTextView = (TextView) findViewById(R.id.helloTextView);
        LogoActivityPresenter logoActivityPresenter = new LogoActivityPresenter(this);

        myTextView.post(new Runnable()
        {
            @Override
            public void run()
            {
                myTextView.animate().translationX(myTextView.getWidth()/2).setDuration(1000).setInterpolator(new LinearOutSlowInInterpolator());
            }
        });
        logoActivityPresenter.startNewActivity();



//        Message msg = new Message();
//        msg.what = 5;
//
//
//        Handler splashHandler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                switch (msg.what) {
//                    case SLEEP:
//                        Intent intentAndroid = new Intent(mainActivityView, LanguageActivity.class);
//                        mainActivityView.startActivityForResult(intentAndroid, 0);
//                        mainActivityView.finish();
//                        break;
//
//                }
//                super.handleMessage(msg);
//            }
//        };
//        splashHandler.sendMessageDelayed(msg, 3000);





                // get the center for the clipping circle
             //   int cx = myTextView.getWidth() / 2;
              //  int cy = myTextView.getHeight() / 2;

//                int a = (myTextView.getLeft() + myTextView.getRight()) / 2;
//                int b = (myTextView.getTop() + myTextView.getBottom()) / 2;

// get the final radius for the clipping circle
               // float finalRadius = (float) Math.hypot(cx, cy);

//                ObjectAnimator animator = new ObjectAnimator();
//                animator.setProperty(View.TRANSLATION_Y);
//                animator.setFloatValues(0f);
//                animator.setTarget(myTextView);
//                animator.setDuration(250);
//                animator.setInterpolator(new LinearOutSlowInInterpolator());
//                animator.start();


//                    public void run(){
//                        myTextView.setTranslationX(100-myTextView.getWidth());
//                        // do something
//                    }



// create the animator for this view (the start radius is zero)
              //  Animator anim =
             //           null;
             //   if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
             //       anim = ViewAnimationUtils.createCircularReveal(myTextView, cx, cy, 0, finalRadius);
             //   }

// make the view visible and start the animation
              //  myTextView.setVisibility(myTextView.VISIBLE);
               // anim.start();


    }
}
