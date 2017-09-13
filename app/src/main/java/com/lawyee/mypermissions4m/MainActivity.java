package com.lawyee.mypermissions4m;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.joker.api.Permissions4M;
import com.joker.api.wrapper.ListenerWrapper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Button mButton5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.button);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton3 = (Button) findViewById(R.id.button3);
        mButton4 = (Button) findViewById(R.id.button4);
        mButton5 = (Button) findViewById(R.id.button5);

        mButton.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                applyOne(MainActivity.this);
                break;
            case R.id.button2:

                break;
            case R.id.button3:

                break;
            case R.id.button4:

                break;
            case R.id.button5:

                break;
        }
    }

    public void applyOne(MainActivity mContext) {
        Permissions4M.get(mContext)
                .requestForce(true)
                .requestPermission(Manifest.permission.READ_CALENDAR)
                .requestCode(200)
                .requestPageType(Permissions4M.PageType.MANAGER_PAGE)
                .requestPageType(Permissions4M.PageType.ANDROID_SETTING_PAGE)
                .requestCallback(new ListenerWrapper.PermissionRequestListener() {
                    @Override
                    public void permissionGranted() {
                        Toast.makeText(MainActivity.this, "申请成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void permissionDenied() {
                        Toast.makeText(MainActivity.this, "申请失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void permissionRationale() {
                        Toast.makeText(MainActivity.this, "申请打开日历权限", Toast.LENGTH_SHORT).show();
                    }
                })
                .request();
    }
}
