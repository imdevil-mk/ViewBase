package com.imdevil.viewbase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    MyView myView;
    Button scrollBy;
    MyLinearLayout ml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        myView = findViewById(R.id.myView);
        scrollBy = findViewById(R.id.scrollBy);

        scrollBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView.smoothScrollTo(-100,-100);
            }
        });

    }
}
