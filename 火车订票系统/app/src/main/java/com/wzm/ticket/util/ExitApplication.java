package com.wzm.ticket.util;

import java.util.LinkedList;
import java.util.List;
import android.app.Activity;
import android.app.Application;
/**
 *  ��ȫ�˳�Ӧ�ó���ķ��������õ���ģʽ
 */
public class ExitApplication extends Application {
	//LinedList����������ɾ��add��remove�����죬��ѯ����ArrayList��ѯ�죨��Ϊ�������ţ�
	private List<Activity> activityList = new LinkedList<Activity> ();
	private static ExitApplication instance;
	private ExitApplication() {
	}

	// ����ģʽ�л�ȡΨһ��ExitApplicationʵ��
	public static ExitApplication getInstance() {
		if (null == instance) {
			instance = new ExitApplication();
		}
		return instance;
	}

	// ���Activity��������
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	// ��������Activity��ɱ��Ӧ��
	public void exit() {
		for (Activity activity:activityList) {
			activity.finish();
		}
		System.exit(0);
	}
}