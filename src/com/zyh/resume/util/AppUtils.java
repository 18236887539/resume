package com.zyh.resume.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.BitmapFactory;

import com.zyh.resume.R;

/**
 * ����Apk��Ϣ�Ĺ�����
 * @author zyh
 */
public class AppUtils {

	/**
	 * ����������ͼ�� //�����Ȩ��
	 */
	public static void installShortCut(Context context, String activityName) {

		SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		boolean hasShrotcut = sp.getBoolean("shortcut", false);
		if (hasShrotcut) return;

		// ���͹㲥��ͼ����������Ҫ�������ͼ��
		Intent intent = new Intent();
		intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");

		// ��ݷ�ʽ����3����Ϣ 1.����2.ͼ�� 3.��ͼ
		intent.putExtra(Intent.EXTRA_SHORTCUT_ICON, BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher));
		intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, context.getResources().getString(R.string.app_name));

		// ����ͼ������ͼ
		Intent shortcutIntent = new Intent();
		shortcutIntent.setAction("android.intent.action.MAIN");
		shortcutIntent.addCategory("android.intent.category.LAUNCHER");
		shortcutIntent.setClassName(context.getPackageName(), activityName);

		intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
		context.sendBroadcast(intent);
		ToastUtils.show(context, "������ݷ�ʽ��" + context.getResources().getString(R.string.app_name));
		sp.edit().putBoolean("shortcut", true).commit();
	}

	/**
	 * �õ�Ӧ�õİ汾��
	 */
	public static String getAppVersion(Context context) {
		PackageManager pm = context.getPackageManager();
		try {
			// �����嵥�ļ�
			PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
			return info.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			// ���ᷢ��
			return "1.0";
		}
	}
}
