package com.imdevil.viewbase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final static  String TAG = "Main Activity";
    Button btn;
    LinearLayout ll;
    Button btn_1;
    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        btn_1 = findViewById(R.id.btn_1);
        ll = findViewById(R.id.ll);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Log.d(TAG,"heightPixels = "+displayMetrics.heightPixels);
        Log.d(TAG,"widthPixels = "+displayMetrics.widthPixels);
        Log.d(TAG,"Dpi = "+displayMetrics.densityDpi);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"ScrollX"+ll.getScrollX());
                Log.d(TAG,"ScrollY"+ll.getScrollY());
                if (flag){
                    ll.scrollTo(100,0);
                    flag = false;
                    Log.d(TAG,"ScrollX"+ll.getScrollX());
                    Log.d(TAG,"ScrollY"+ll.getScrollY());
                }else {
                    ll.scrollTo(-100,0);
                    Log.d(TAG,"ScrollX"+ll.getScrollX());
                    Log.d(TAG,"ScrollY"+ll.getScrollY());
                }
            }
        });
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) btn_1.getLayoutParams();
                layoutParams.leftMargin += 100;
                layoutParams.topMargin += 100;
                btn_1.invalidate();
                btn_1.requestLayout();
            }
        });


        findViewById(R.id.btn_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });

        findViewById(R.id.btn_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ThirdActivity.class));
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int top = btn.getTop();
                int left = btn.getLeft();
                int bottom = btn.getBottom();
                int right = btn.getRight();
                float x = btn.getX();
                float y = btn.getY();
                float translationX = btn.getTranslationX();
                float translationY = btn.getTranslationY();
                Log.d(TAG,"TOP = "+top);
                Log.d(TAG,"left = "+left);
                Log.d(TAG,"bottom = "+bottom);
                Log.d(TAG,"right = "+right);
                Log.d(TAG,"x = "+x );
                Log.d(TAG,"translationX"+translationX);
            }
        }).start();
    }
}
