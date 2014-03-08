package com.asht.model;

import android.view.View;

import com.asht.adapter.MyCasesAdapter.MyCasesItemView;

public class CaseView {

	private MyCasesItemView view;
	private Record record;

	public MyCasesItemView getView() {
		return view;
	}

	public void setView(MyCasesItemView view) {
		this.view = view;
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

}
