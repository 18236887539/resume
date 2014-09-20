package com.zyh.resume;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.zyh.resume.util.AppUtils;

public class Splash extends Activity {

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (sp.getBoolean("showguide", true)) enterGuide();
			else enterHome();
		};
	};
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		sp = getSharedPreferences("config", MODE_PRIVATE);
		// ��������ͼ��
		AppUtils.installShortCut(this, "com.zyh.resume.Splash");
		handler.sendEmptyMessageDelayed(200, 2000);// �ӳ�����
	}

	/**
	 * ������ҳ
	 */
	private void enterHome() {
		startActivity(new Intent(this, HomeActivity.class));
		this.finish();
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
	}

	/**
	 * ��������ҳ
	 */
	private void enterGuide() {
		startActivity(new Intent(this, GuideActivity.class));
		this.finish();
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
	};

}
