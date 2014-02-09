package com.asht.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;

public class AshtFragment extends Fragment {
	public AshtFragmentCallback callback;

	public void setAshtFragmentCallback(AshtFragmentCallback callback) {
		this.callback = callback;
	}
	public OnTouchListener onScrollerTouchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				if (getActivity().getCurrentFocus() != null
						&& getActivity().getCurrentFocus().getWindowToken() != null) {
					((InputMethodManager) getActivity().getSystemService(
							getActivity().INPUT_METHOD_SERVICE))
							.hideSoftInputFromWindow(getActivity()
									.getCurrentFocus().getWindowToken(),
									InputMethodManager.HIDE_NOT_ALWAYS);
				}
			}
			return false;
		}
	};
}
