package com.example.broadcastbestpractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button forceOfficeline = (Button)findViewById(R.id.force_offline);
        forceOfficeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.broadcastbestpractice.FORCE_OFFLINE");
                sendBroadcast(intent);
            }
        });
    }
}

/*程序的强制下线功能：
*   一、思路：
*       1、适当的时候点击“强制下线”按钮时，发出广播通知。
*       2、在活动页面创建广播接收器，接收到“强制下线”通知时进行下线处理。
*   二、注意事项
*       1、为每一个活动页面都注册一次相同的“强制下线”广播监听，不太现实
*           可以写一个基础类(baseActivity继承AppCompatActivity)来统一处理，所有活动都继承此类，
*           同时写一个活动的管理类(ActivityCollector)。
*       2、“强制下线”广播监听应注册到此时正在活动的页面(及栈顶【放在在活动的onResume()方法里】)
*       3、取消广播监听同样放在此时活动的页面(【onPause()方法中】)
* */
/*过程：
*   1、创建活动的管理类【ActivityCollector】，方法都静态的；
*   2、创建活动的基础类【BaseActivity】，操作都是自动的继承到子类中的；
*   3、创建LoginActivity活动来进行登陆，此活动注册为程序的主活动，取消MainActivity活动作为主活动；
*   4、LoginActivity活动中点击登陆跳转到MainActivity活动；
*   5、MainActivity活动中有一个强制下线的按钮(即：发送一个强制下线的广播通知)；
*   6、广播接收器已注册到每一个继承了BaseActivity的活动中，
*       此时活动的页面如果继承了MainActivity类会监听到“强制下线”的广播通知。
* */
