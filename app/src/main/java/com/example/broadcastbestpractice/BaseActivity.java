package com.example.broadcastbestpractice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
/*自定义活动的基类*/
public class BaseActivity extends AppCompatActivity {
    private ForceOfflineReceiver receiver;
    /*活动创建时添加到活动集合*/
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    /*只在活动页面活动时进行监听*/
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcastbestpractice.FORCE_OFFLINE");
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver, intentFilter);
    }
    /*活动页面不在栈顶时就取消监听动作*/
    @Override
    protected void onPause() {
        super.onPause();
        if(receiver != null){
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    /*活动销毁时从集合中移除*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    /*广播接收器*/
    class ForceOfflineReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(final Context context, Intent intent) {
            /*接收到通知之后，弹出一个提示框*/
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(false);//只有点击弹出框的按钮才会有反应，点击手机的返回键是没有反应的
            builder.setTitle("Warning");
            builder.setMessage("You are forced to be offline. Please try to login again.");
            /*为弹出框注册Ok按钮监听事件*/
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCollector.finishAll();//销毁所有活动
                    Intent intent = new Intent(context,LoginActivity.class);
                    context.startActivity(intent);//重新启动LoginActivity
                }
            });
            builder.show();
        }
    }
}
