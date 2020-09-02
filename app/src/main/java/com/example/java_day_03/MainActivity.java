package com.example.java_day_03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnClickUtils.init(this);
    }

    @OnClick({R.id.button,R.id.button2,R.id.button3})
    public void OnClick(View view){

        switch (view.getId()){

            case R.id.button:

                Log.e(MainActivity.class.getSimpleName(),"button");

                break;

            case R.id.button2:

                Log.e(MainActivity.class.getSimpleName(),"button2");

                break;

            case R.id.button3:


                Log.e(MainActivity.class.getSimpleName(),"button3");
                break;

        }




    }


}
