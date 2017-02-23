package com.example.administrator.mytest;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"onCreate",0).show();
    }
    private  Handler handler = new Handler();

    private Runnable myRunnable = new Runnable() {
                    @Override
                public void run() {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
                }
            };

    /**
     * 点击横屏事件
     * @param v
     */
    public void changeScan(View v){
        Toast.makeText(this,"切换了",0).show();
        if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            //三秒后才能自由变换
            handler.postDelayed(myRunnable,5000);
       }
    }


    /**
     * 锁定屏幕
     * @param v
     */
    public void lockScan(View v){
        Toast.makeText(this,"锁定了",0).show();
        ((Button)v).setText("解锁");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
    }

    /**
     * 处理返回按键
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Toast.makeText(this,"自由切换了"+newConfig.orientation,0).show();
        //如果是竖屏，不允许自由变换，只能手动去切换横竖屏
        //手动切换的横屏，就只能自由变换为横屏
    }
}
