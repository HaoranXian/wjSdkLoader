package com.example.administrator.wjsdkloader;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wj.loader.SDKManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void init(View v) {
        SDKManager.getInstance().SDKInitializer(this, "2000", 1, "确定要购买吗?", "测试专用", "10001", "", new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        }, new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        });
    }

    public void pay(View v) {
        SDKManager.getInstance().BaiduMap(this, "2000", 1, "确定要购买吗?", "测试专用", "10001", "", new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        });
    }

    public void paypoint(View v) {
        Toast.makeText(this, SDKManager.getInstance().g().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        SDKManager.getInstance().s(this);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        SDKManager.getInstance().close(this);
        super.onDestroy();
    }
}
