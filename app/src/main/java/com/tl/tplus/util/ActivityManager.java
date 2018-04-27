package com.tl.tplus.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import com.tl.tplus.base.app.CashApplication;

import java.util.List;
import java.util.Stack;

/**
 * 用于管理Activity销毁，退出
 *
 * @author w
 */
public class ActivityManager {
    // Activity栈
    private static Stack<Activity> activityStack;
    // 单例模式  
    private static ActivityManager instance;

    private ActivityManager() {
    }

    /**
     * 单一实例
     */
    public static ActivityManager getAppManager() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    public static Stack<Activity> getActivityStack() {
        return activityStack;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        if(activityStack!=null){
            if(!activityStack.empty()){
                Activity activity = activityStack.lastElement();
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        if (activityStack == null) {
            return;
        }
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 判断某一个类是否存在任务栈里面
     *
     * @return
     */
    public static boolean isExsitMianActivity(Context context, Class cls) {
        boolean flag = false;
        /*Intent intent = new Intent(context, cls);
        ComponentName cmpName = intent.resolveActivity(context.getPackageManager());
        if (cmpName != null) { // 说明系统中存在这个activity
            ActivityManager am = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskInfoList = am.getRunningTasks(10);
            for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
                if (taskInfo.baseActivity.equals(cmpName)) { // 说明它已经启动了
                    flag = true;
                    break;  //跳出循环，优化效率
                }
            }
        }*/
        if (activityStack == null) {
            return false;
        }
        android.app.ActivityManager activityManager = (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);

        }
        return flag;

    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            android.app.ActivityManager activityMgr = (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }


    /**
     * 检测packName是否在当前Task的栈顶
     */
    public static boolean isTopActivy(Context context, String packName) {
        // RunningAppProcessInfo ra = getRunningAppPro();
        if (Build.VERSION.SDK_INT >= 21) {
            return isTopActivy21(context, packName, 2);
        } else {
            return isTopActivy(context, packName, 1);
        }
        // return isTopActivy(packName, 1);
    }

    /**
     * 检测packName是否在当前Task的栈顶
     */
    public static boolean isTopActivy21(Context context, String packName, int maxNum) {
        if (TextUtils.isEmpty(packName)) {
            return false;
        }
        android.app.ActivityManager manager = (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<android.app.ActivityManager.RunningAppProcessInfo> rapList = manager.getRunningAppProcesses();
        if (null != rapList && rapList.size() > 0) {
            for (int i = 0; i < maxNum; i++) {
                if (i < rapList.size()) {
                    String[] pkgS = rapList.get(i).pkgList;
                    if (null != pkgS && pkgS.length > 0) {
                        if (!TextUtils.isEmpty(pkgS[0])) {
                            if (pkgS[0].contains(packName)) {
                                return true;
                            }
                            // return pkgS[0].contains(packName);
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * 检测packName是否在当前Task的栈顶
     */
    public static boolean isTopActivy(Context context, String packName, int maxNum) {
        if (TextUtils.isEmpty(packName)) {
            return false;
        }
        android.app.ActivityManager manager = (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<android.app.ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(maxNum);
        if (null != runningTaskInfos) {
            for (int i = 0; i < runningTaskInfos.size(); i++) {
                String cmpNameTemp = (runningTaskInfos.get(i).topActivity).toString();
                if (!TextUtils.isEmpty(cmpNameTemp)) {
                    if (cmpNameTemp.contains(packName)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 检测packName是否在当前Task的栈顶
     */
    public static boolean isTopActivy(String packName) {
        // RunningAppProcessInfo ra = getRunningAppPro();
        /*if (Build.VERSION.SDK_INT >= 21) {
            return isTopActivy21(packName, 2);
		} else {
			return isTopActivy(packName, 1);
		}*/
        return isTopActivy(packName, 1);
    }

    /**
     * 检测packName是否在当前Task的栈顶
     */
    public static boolean isTopActivy(String packName, int maxNum) {
        if (TextUtils.isEmpty(packName)) {
            return false;
        }
        android.app.ActivityManager manager = (android.app.ActivityManager) CashApplication.getInstance().getSystemService(Context.ACTIVITY_SERVICE);
        List<android.app.ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(maxNum);
        if (null == runningTaskInfos) {
            return false;
        }

        for (int i = 0; i < runningTaskInfos.size(); i++) {
            String cmpNameTemp = (runningTaskInfos.get(i).topActivity).toString();
            if (!TextUtils.isEmpty(cmpNameTemp) && cmpNameTemp.contains(packName)) {
                return true;
            }
        }
        return false;
    }


}
