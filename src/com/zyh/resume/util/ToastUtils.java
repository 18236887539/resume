package com.zyh.resume.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

	/**
	 * ��ʾtoast
	 * @param context ������
	 * @param text toast�ı�
	 */
	public static void show(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

	/**
	 * ��ʾtoast
	 * @param context ������
	 * @param text toast�ı�
	 * @param islong ����ʾ
	 */
	public static void show(Context context, String text, boolean islong) {
		if (islong) {
			Toast.makeText(context, text, Toast.LENGTH_LONG).show();
		} else {
			show(context, text);
		}
	}
}
