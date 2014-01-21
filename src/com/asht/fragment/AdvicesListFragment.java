package com.asht.fragment;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ListView;

import com.asht.R;
import com.asht.adapter.AdviceAdapter;
import com.asht.controller.Controller;
import com.asht.model.Advice;

public class AdvicesListFragment extends AshtFragment {
	private ListView mListView;
	private List<Advice> sourceAdvices;
	private View addAdvice;

	public AdvicesListFragment() {

	}

	public AdvicesListFragment(List<Advice> source) {
		// TODO Auto-generated constructor stub
		this.sourceAdvices = source;
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Controller.setNomePagTop(getActivity(), true, "意见反馈");
		mListView = (ListView) getActivity().findViewById(R.id.advice_list);
		AdviceAdapter adapter = new AdviceAdapter(getActivity(), sourceAdvices);
		mListView.setAdapter(adapter);
		final int width = getActivity().getWindowManager().getDefaultDisplay()
				.getWidth() / 2;
		mListView.setOnTouchListener(new OnTouchListener() {
			private float mLastx = 0;
			private float x = 0;

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					mLastx = x = arg1.getX();
					break;
				case MotionEvent.ACTION_MOVE:
					mLastx = arg1.getX();
					break;
				case MotionEvent.ACTION_UP:
					if (mLastx - x > width) {
						callback.back();
					}
					break;
				}
				return true;
			}
		});
		addAdvice = getActivity().findViewById(R.id.btnEdit);
		addAdvice.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Controller.newAdvice(getActivity());
			}
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.advice_list, null, false);
	}
}
