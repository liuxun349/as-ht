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
package uk.co.senab.photoview.sample;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.HackyViewPager;
import uk.co.senab.photoview.PhotoView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.asht.R;
import com.asht.model.Resume;
import com.choose.util.ImageManager2;
import com.lidroid.xutils.BitmapUtils;

public class ViewPagerActivity extends Activity {

	private ViewPager mViewPager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mViewPager = new HackyViewPager(this);
		setContentView(mViewPager);

		Intent data = getIntent();

		Bundle bundle = data.getExtras();
		@SuppressWarnings("unchecked")
		final List<Resume> tDataList = (List<Resume>) bundle
				.getSerializable("dataList");
		int index = bundle.getInt("index", 0);

		mViewPager.setAdapter(new SamplePagerAdapter(tDataList));
		mViewPager.setCurrentItem(index);
	}

	class SamplePagerAdapter extends PagerAdapter {

		private List<Resume> resumes = new ArrayList<Resume>();
		BitmapUtils bitmap;

		public SamplePagerAdapter(List<Resume> resumes) {
			this.resumes = resumes;

			bitmap = new BitmapUtils(getApplicationContext());
		}

		@Override
		public int getCount() {
			return resumes.size();
		}

		@Override
		public View instantiateItem(ViewGroup container, int position) {
			PhotoView photoView = new PhotoView(container.getContext());
			// photoView.setImageResource(sDrawables[position]);

			// Now just add PhotoView to ViewPager and return it
			container.addView(photoView, LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);
			// ImageManager2.from(ViewPagerActivity.this).displayImage(photoView,
			// resumes.get(position).getImedicalrecorditemfilename(),
			// R.drawable.camera_default);
			bitmap.display(photoView, "http://115.28.48.85:8080/ascs/"
					+ resumes.get(position).getImedicalrecorditemfilename());
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

}
