package com.asht.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.asht.R;

public class RecommendInfoActivity extends Activity implements OnClickListener {

	TextView tv_rid, tv_rname, tv_rhz, tv_rphone, tv_rshenfenzhengType,
			tv_rshenfenzheng, tv_rtuijianshijian, tv_rzhuangtai,
			tv_rshenheshijian, tv_rshenhebeizhu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details_recommend);
		findViewById(R.id.tv_title_back).setOnClickListener(this);
		tv_rid = (TextView) findViewById(R.id.tv_rid);
		tv_rname = (TextView) findViewById(R.id.tv_rname);
		tv_rhz = (TextView) findViewById(R.id.tv_rhz);
		tv_rphone = (TextView) findViewById(R.id.tv_rphone);
		tv_rshenfenzhengType = (TextView) findViewById(R.id.tv_rshenfenzhengType);
		tv_rshenfenzheng = (TextView) findViewById(R.id.tv_rshenfenzheng);
		tv_rtuijianshijian = (TextView) findViewById(R.id.tv_rtuijianshijian);
		tv_rzhuangtai = (TextView) findViewById(R.id.tv_rzhuangtai);
		tv_rshenheshijian = (TextView) findViewById(R.id.tv_rshenheshijian);
		tv_rshenhebeizhu = (TextView) findViewById(R.id.tv_rshenhebeizhu);

		Bundle b = getIntent().getExtras();
		if (b != null) {
			String recommendPhoneNo = b.getString("recommendPhoneNo");
			String recommendtrueName = b.getString("recommendtrueName");
			String recommendCertificateTypeId = b
					.getString("recommendCertificateTypeId");
			String recommendCertificateId = b
					.getString("recommendCertificateId");
			String recommendeMail = b.getString("recommendeMail");
			String recommendDateTime = b.getString("recommendDateTime");
			String recommendState = b.getString("recommendState");
			String examineDateTime = b.getString("examineDateTime");
			String recommendRoleId = b.getString("recommendRoleId");
			tv_rid.setText(recommendeMail);
			tv_rname.setText(recommendtrueName);
			tv_rhz.setText("1001".equals(recommendRoleId) ? "患者" : "医生");
			tv_rphone.setText(recommendPhoneNo);
			tv_rshenfenzheng.setText(recommendCertificateId);
			String shenfen = "";
			if ("1".equals(recommendCertificateTypeId)) {
				shenfen = "军官证";
			} else if ("2".equals(recommendCertificateTypeId)) {
				shenfen = "护照";
			} else {
				shenfen = "身份证";
			}
			tv_rshenfenzhengType.setText(shenfen);
			tv_rtuijianshijian.setText(recommendDateTime);
			tv_rzhuangtai.setText("0".equals(recommendState) ? "未审核" : "审核");
			tv_rshenheshijian.setText(examineDateTime);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_title_back:
			finish();
			break;

		default:
			break;
		}
	}

}
