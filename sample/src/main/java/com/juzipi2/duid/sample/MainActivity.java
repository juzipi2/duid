package com.juzipi2.duid.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.juzipi2.util.DUID;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String duid = DUID.getDUID(this);
        System.out.println("duid:" + duid);
        Toast.makeText(this, duid, Toast.LENGTH_LONG).show();
    }
}
