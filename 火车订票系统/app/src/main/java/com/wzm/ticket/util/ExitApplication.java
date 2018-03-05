package com.wzm.ticket.util;

import java.util.LinkedList;
import java.util.List;
import android.app.Activity;
import android.app.Application;
/**
 *  完全退出应用程序的方法：利用单例模式
 */
public class ExitApplication extends Application {
	//LinedList对于新增和删除add和remove操作快，查询慢；ArrayList查询快（因为有索引号）
	private List<Activity> activityList = new LinkedList<Activity> ();
	private static ExitApplication instance;
	private ExitApplication() {
	}

	// 单例模式中获取唯一的ExitApplication实例
	public static ExitApplication getInstance() {
		if (null == instance) {
			instance = new ExitApplication();
		}
		return instance;
	}

	// 添加Activity到容器中
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	// 遍历所有Activity并杀死应用
	public void exit() {
		for (Activity activity:activityList) {
			activity.finish();
		}
		System.exit(0);
	}
}