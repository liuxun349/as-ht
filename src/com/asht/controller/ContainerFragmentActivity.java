package com.asht.controller;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;

import com.asht.R;
import com.asht.fragment.AshtFragment;
import com.asht.fragment.AshtFragmentCallback;
import com.asht.utl.ApplictionManager;

public class ContainerFragmentActivity<Container> extends FragmentActivity {
	private View containerView;
	private FragmentManager fManager;
	private AshtFragment fragment;
	private Activity mActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.container);
		fManager = getSupportFragmentManager();
		fragment = ApplictionManager.getInstance().currentFragment;
		fragment.setAshtFragmentCallback(callback);
		fManager.beginTransaction().add(R.id.fragment_container, fragment)
				.commit();
		mActivity = this;
	}

	private AshtFragmentCallback callback = new AshtFragmentCallback() {

		@Override
		public void recevierDate(Object date) {
			// TODO Auto-generated method stub

		}

		@Override
		public void back() {
			// TODO Auto-generated method stub
			if (fManager.getBackStackEntryCount() == 0) {
				ContainerFragmentActivity.this.finish();
			} else {
				fManager.popBackStack();
			}
		}
	};
	private float mLastx = 0;
	private float x = 0;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mLastx = x = event.getX();
			System.out.println(" yes ");
			if (getCurrentFocus() != null
					&& getCurrentFocus().getWindowToken() != null) {
				((InputMethodManager) getSystemService(mActivity.INPUT_METHOD_SERVICE))
						.hideSoftInputFromWindow(getCurrentFocus()
								.getWindowToken(),
								InputMethodManager.HIDE_NOT_ALWAYS);
			}
			break;
		case MotionEvent.ACTION_MOVE:
			mLastx = event.getX();
			break;
		case MotionEvent.ACTION_UP:
			if (mLastx - x > getWindowManager().getDefaultDisplay().getWidth() / 2) {
				callback.back();
			}
			break;
		}
		return super.onTouchEvent(event);
	}
}
