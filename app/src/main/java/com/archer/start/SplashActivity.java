package com.archer.start;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = View.inflate(this, R.layout.activity_splash, null);
        setContentView(view);

        PackageInfo pinfo;
        try {
            pinfo = getPackageManager().getPackageInfo(this.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
            TextView tv = (TextView) this.findViewById(R.id.splashVerText);
            tv.setText(pinfo.versionName);
        } catch (NameNotFoundException e1) {
            e1.printStackTrace();
        }

        new Thread() {

            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
                gotoMainPage();
            }

        }.start();
    }

    private void gotoMainPage() {
        Intent intent = (new Intent(this, MainActivity.class));
        startActivity(intent);
        finish();
    }
}
