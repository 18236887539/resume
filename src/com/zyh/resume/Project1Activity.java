package com.zyh.resume;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Project1Activity extends Activity {
	private ViewPager viewPager;
	private LinearLayout pointGroup;
	private TextView iamgeDesc;
	// ͼƬ��ԴID
	private final int[] imageIds = { R.drawable.p1_1, R.drawable.p1_2, R.drawable.p1_3, R.drawable.p1_4, R.drawable.p1_5 };
	// ͼƬ���⼯��
	private final String[] imageDescriptions = { "���������ף��ҾͲ��ܵ���", "�����ֻ��������ٳ������ϸ������˴�ϳ�", "���ر�����Ӱ�������", "������TV�������", "��Ѫ��˿�ķ�ɱ" };
	private ArrayList<ImageView> imageList;

	/**
	 * ��һ��ҳ���λ��
	 */
	protected int lastPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_p1);

		viewPager = (ViewPager) findViewById(R.id.viewpager1);
		pointGroup = (LinearLayout) findViewById(R.id.point_group);
		// iamgeDesc = (TextView) findViewById(R.id.image_desc);
		// iamgeDesc.setText(imageDescriptions[0]);

		imageList = new ArrayList<ImageView>();
		for (int i = 0; i < imageIds.length; i++) {

			// ��ʼ��ͼƬ��Դ
			ImageView image = new ImageView(this);
			image.setBackgroundResource(imageIds[i]);
			imageList.add(image);

			// ���ָʾ��
			ImageView point = new ImageView(this);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);

			params.rightMargin = 20;
			point.setLayoutParams(params);

			point.setBackgroundResource(R.drawable.point_bg);
			if (i == 0) {
				point.setEnabled(true);
			} else {
				point.setEnabled(false);
			}
			pointGroup.addView(point);
		}

		viewPager.setAdapter(new MyPagerAdapter());

		viewPager.setCurrentItem(Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % imageList.size()));

		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			/**
			 * ҳ���л������ position �µ�ҳ��λ��
			 */
			public void onPageSelected(int position) {
				position = position % imageList.size();
				// ����������������
				// iamgeDesc.setText(imageDescriptions[position]);
				// �ı�ָʾ���״̬// �ѵ�ǰ��enbale Ϊtrue
				pointGroup.getChildAt(position).setEnabled(true);
				// ����һ������Ϊfalse
				pointGroup.getChildAt(lastPosition).setEnabled(false);
				lastPosition = position;
			}

			// ҳ�����ڻ�����ʱ�򣬻ص�
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}

			// ��ҳ��״̬�����仯��ʱ�򣬻ص�
			public void onPageScrollStateChanged(int state) {

			}
		});
		// �Զ�ѭ���� 1����ʱ����Timer 2�������߳� while true ѭ�� 3��ColckManager 4�� ��handler ������ʱ��Ϣ��ʵ��ѭ��
		isRunning = true;
		handler.sendEmptyMessageDelayed(0, 2000);
	}

	/**
	 * �ж��Ƿ��Զ�����
	 */
	private boolean isRunning = false;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// ��viewPager ��������һҳ
			viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
			if (isRunning) {
				handler.sendEmptyMessageDelayed(0, 2000);
			}
		};
	};

	protected void onDestroy() {
		isRunning = false;
		super.onDestroy();
	};

	private class MyPagerAdapter extends PagerAdapter {
		/**
		 * ���ҳ�������
		 */
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		/**
		 * �����Ӧλ���ϵ�view container view����������ʵ����viewpager���� position ��Ӧ��λ��
		 */
		public Object instantiateItem(ViewGroup container, int position) {
			// �� container ���һ��view
			container.addView(imageList.get(position % imageList.size()));
			// ����һ���͸�view��Ե�object
			return imageList.get(position % imageList.size());
		}

		/**
		 * �ж� view��object�Ķ�Ӧ��ϵ
		 */
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		/**
		 * ���ٶ�Ӧλ���ϵ�object
		 */
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
			object = null;
		}
	}
}
