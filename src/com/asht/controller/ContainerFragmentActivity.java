package com.asht.controller;

import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.asht.R;
import com.asht.fragment.AshtFragment;
import com.asht.fragment.AshtFragmentCallback;
import com.asht.fragment.EditLoginPasswordFragment;
import com.asht.utl.ApplictionManager;

public class ContainerFragmentActivity<Container> extends FragmentActivity {
	private View containerView;
	private FragmentManager fManager;
	private AshtFragment fragment;

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

	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mLastx = x = event.getX();
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
		return true;
	}

}
