/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.asht.controller;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.asht.R;
import com.asht.model.Resume;
import com.asht.view.HackyViewPager;
import com.yj.compress.YJBitmap;

public class ViewPagerActivity extends Activity {

	private HackyViewPager mViewPager;
	private View fl_photo_prompt;
	int index;
	TextView tv_photo_pro;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phote);
		mViewPager = (HackyViewPager) findViewById(R.id.hvp_photo);
		fl_photo_prompt = findViewById(R.id.fl_photo_prompt);
		tv_photo_pro = (TextView) findViewById(R.id.tv_photo_prompt);

		mViewPager.postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent data = getIntent();

				Bundle bundle = data.getExtras();
				@SuppressWarnings("unchecked")
				final List<Resume> tDataList = (List<Resume>) bundle
						.getSerializable("dataList");
				if (tDataList == null) {

				} else {
					index = bundle.getInt("index", 0);
					tv_photo_pro.setText((index + 1) + "/" + tDataList.size());
				}

				mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

					@Override
					public void onPageSelected(int arg0) {
						index = arg0;
						tv_photo_pro.setText((index + 1) + "/"
								+ tDataList.size());
					}

					@Override
					public void onPageScrolled(int arg0, float arg1, int arg2) {

					}

					@Override
					public void onPageScrollStateChanged(int arg0) {

					}
				});
				mViewPager.setAdapter(new SamplePagerAdapter(tDataList));
				mViewPager.setCurrentItem(index);

			}
		}, 1000);

		findViewById(R.id.tv_title_back).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						finish();
					}
				});
	}

	class SamplePagerAdapter extends PagerAdapter {

		private List<Resume> resumes = new ArrayList<Resume>();
		YJBitmap yjBitmap;

		public SamplePagerAdapter(List<Resume> resumes) {
			this.resumes = resumes;

			yjBitmap = YJBitmap.create(getApplicationContext());
		}

		@Override
		public int getCount() {
			return resumes.size();
		}

		@Override
		public View instantiateItem(ViewGroup container, final int position) {
			final PhotoView photoView = new PhotoView(container.getContext());
			container.addView(photoView, LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);

			yjBitmap.display(
					photoView,
					"http://115.28.48.85:8080/ascs/"
							+ resumes.get(position)
									.getImedicalrecorditemfilename(),
					yjBitmap.getBitmapFromCache("http://115.28.48.85:8080/ascs/"
							+ resumes.get(position).getMinFileName()));
			photoView.setId(10000 + position);
			photoView.setOnClickListener(onclick);
			return photoView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

	}

	OnClickListener onclick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (fl_photo_prompt.getVisibility() == View.VISIBLE) {
				fl_photo_prompt.setVisibility(View.GONE);
			} else {
				fl_photo_prompt.setVisibility(View.VISIBLE);
			}
		}
	};

}
