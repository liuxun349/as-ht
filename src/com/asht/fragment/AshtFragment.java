package com.asht.fragment;

import android.support.v4.app.Fragment;
import android.view.MotionEvent;

public class AshtFragment extends Fragment {
	public AshtFragmentCallback callback;

	public void setAshtFragmentCallback(AshtFragmentCallback callback) {
		this.callback = callback;
	}

}
