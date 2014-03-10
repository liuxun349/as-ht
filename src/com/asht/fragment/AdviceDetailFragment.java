package com.asht.fragment;

import com.asht.R;
import com.asht.controller.Controller;
import com.asht.model.Advice;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class AdviceDetailFragment extends AshtFragment implements
		OnClickListener {
	private Advice mAdvice;
	private TextView adviceContent;
	private TextView inputTime;
	private TextView handlContent;
	private TextView handlTime;

	public AdviceDetailFragment() {
		// TODO Auto-generated constructor stub
	}

	public AdviceDetailFragment(Advice advice) {
		// TODO Auto-generated constructor stub
		mAdvice = advice;
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
		Controller.setNomePagTop(getActivity(), true, "意见详情");
		getActivity().findViewById(R.id.tv_title_back).setOnClickListener(this);

		adviceContent = (TextView) getActivity().findViewById(
				R.id.adviceContent);
		inputTime = (TextView) getActivity().findViewById(R.id.inputime);
		handlContent = (TextView) getActivity().findViewById(R.id.handlContent);
		handlTime = (TextView) getActivity().findViewById(R.id.handlTime);

		adviceContent.setText(mAdvice.txtadvice);
		inputTime.setText(mAdvice.dtinputtime);
		if (mAdvice.istate.equals("1")) {
			handlContent.setText(mAdvice.txtresult);
			handlTime.setText(mAdvice.dtclosetime);
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.advice_detail, null, false);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_title_back:
			callback.back();
			break;

		default:
			break;
		}
	}
}
