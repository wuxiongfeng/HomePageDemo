package org.feng.homepagedemo;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private GridView mGridView;
    private ViewPager mViewPager;
    private TabLayout tabs;
    private View mHead;
    private MyGridAdapter myGridAdapter;
    private MyListAdapter myListAdapter;
    private SimpleFragmentPagerAdapter pagerAdapter;
    private List<String> mList = new ArrayList<>();
    private List<String> mGrid = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listview);

        mHead = LayoutInflater.from(this).inflate(R.layout.item_head, null, false);
        mListView.addHeaderView(mHead);

        mGridView = (GridView) mHead.findViewById(R.id.gridview);
        mViewPager = (ViewPager) mHead.findViewById(R.id.viewpager);
        tabs = (TabLayout) mHead.findViewById(R.id.tabs);

        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(pagerAdapter);
        tabs.setupWithViewPager(mViewPager);


        myGridAdapter = new MyGridAdapter();
        myListAdapter = new MyListAdapter();

        mGridView.setAdapter(myGridAdapter);
        mListView.setAdapter(myListAdapter);

        intData();

        myGridAdapter.notifyDataSetChanged();
        myListAdapter.notifyDataSetChanged();
    }

    private void intData() {
        for (int i = 0; i < 10; i++) {
            mList.add("ITEM(" + i + ")");
            mGrid.add("BUTTON(" + i + ")");
        }
    }

    class MyListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_list, parent, false);
                viewHolder = new ViewHolder();
                convertView.setTag(viewHolder);
                viewHolder.itemContent = (TextView) convertView.findViewById(R.id.item_content);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.itemContent.setText(mList.get(position));
            return convertView;
        }
    }

    class MyGridAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_list, parent, false);
                viewHolder = new ViewHolder();
                convertView.setTag(viewHolder);
                viewHolder.itemContent = (TextView) convertView.findViewById(R.id.item_content);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.itemContent.setText(mList.get(position));
            Drawable topDrawable = getResources().getDrawable(android.R.drawable.ic_menu_more);
            topDrawable.setBounds(0, 0, topDrawable.getMinimumWidth(), topDrawable.getMinimumHeight());
            viewHolder.itemContent.setCompoundDrawables(null, topDrawable, null, null);
            viewHolder.itemContent.setTextSize(10f);
            return convertView;
        }
    }

    class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

        final int PAGE_COUNT = 3;
        private String tabTitles[] = new String[]{"tab1", "tab2", "tab3"};

        public SimpleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            return PageFragment.newInstance();
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }

    class ViewHolder {
        public TextView itemContent;
    }

}

