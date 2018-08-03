package com.example.broadcastbestpractice;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;
/*操作项目中活动的类*/
public class ActivityCollector {
    /*创建一个集合，用来登记活动*/
    public static List<Activity> activities = new ArrayList<>();
    /*把活动加入到集合*/
    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    /*把活动从集合中移除*/
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    /*移除集合中的所有活动*/
    public static void finishAll(){
        for (Activity activity : activities) {
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
