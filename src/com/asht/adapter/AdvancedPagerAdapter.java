/**
 * 
 */
package com.asht.adapter;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 
 * @author ZAR
 *
 */
public class AdvancedPagerAdapter extends PagerAdapter
{
        // 要显示的view集合
        private ArrayList<View> views;
        
        // 构造函数
        public AdvancedPagerAdapter( ArrayList<View> views)
        {
                this.views = views;
        }
        
        @Override
        public void destroyItem(View arg0, int arg1, Object arg2)
        {
                // TODO Auto-generated method stub
                ((ViewPager) arg0).removeView(views.get(arg1));
        }

        @Override
        public void finishUpdate(View arg0)
        {
                // TODO Auto-generated method stub

        }

        @Override
        public int getCount()
        {
                // TODO Auto-generated method stub
                return views.size();
        }

        @Override
        public Object instantiateItem(View arg0, int arg1)
        {
                // TODO Auto-generated method stub
                ((ViewPager) arg0).addView(views.get(arg1));
                return views.get(arg1);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1)
        {
                // TODO Auto-generated method stub
                boolean result = arg0 == arg1;

                return result;
        }

}
