package com.archer.start;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        PackageInfo pinfo;
        try {
            pinfo = getPackageManager().getPackageInfo(this.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
            TextView tv = (TextView) this.findViewById(R.id.aboutPageVerText);
            tv.setText(pinfo.versionName);
        } catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public void onBackBtnClick(View v) {
        finish();
    }

}
