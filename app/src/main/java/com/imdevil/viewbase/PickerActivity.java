package com.imdevil.viewbase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.imdevil.viewbase.trash.Picker;

import java.util.ArrayList;
import java.util.List;

public class PickerActivity extends AppCompatActivity {

    private Picker pi;
    private List<String> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);

        pi = findViewById(R.id.pi);

        int i = 0;
        while(i<30){
            dataList.add("0000000"+i);
            i++;
        }

        pi.setDataList(dataList);
    }
}
