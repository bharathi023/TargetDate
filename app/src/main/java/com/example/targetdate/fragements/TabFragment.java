package com.example.targetdate.fragements;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.targetdate.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class TabFragment extends Fragment {
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 2;

    public TabFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // inflater.inflate(R.layout.fragment_tab, container, false);

        View x = inflater.inflate(R.layout.fragment_tab, null);
        initGui(x);
        initalizeTab(x);
        return  x;
    }

    private void initalizeTab(View x) {

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
                setupTabIcons();

            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {

            }
        });


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e("dasfasdf", "asdfas");
                try {
                    HomeBaseFragment baseFragment = (HomeBaseFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager + ":" + viewPager.getCurrentItem());
                    if (baseFragment != null)
                        baseFragment.onTabSelected();
                } catch (IndexOutOfBoundsException e) {
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initGui(final View x) {
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));




    }

    private void setupTabIcons() {
        try {


            View tabOne = (View) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);

            ((TextView) tabOne.findViewById(R.id.tab)).setText(getString(R.string.nav_home));
            ((ImageView) tabOne.findViewById(R.id.tab_icon)).setImageResource(R.drawable.ic_home);
            if (tabLayout != null)
                tabLayout.getTabAt(0).setCustomView(tabOne);

            View tabTwo = (View) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
            ((TextView) tabTwo.findViewById(R.id.tab)).setText(getString(R.string.profile));
            ((ImageView) tabTwo.findViewById(R.id.tab_icon)).setImageResource(R.drawable.ic_user_profile);
            if (tabLayout != null)
                tabLayout.getTabAt(1).setCustomView(tabTwo);


        } catch (Exception e) {

        }
    }
    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new ProfileFragment();
                default:
                    return  new HomeFragment();


            }
        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return getResources().getString(R.string.nav_home);
                case 1:
                    return getResources().getString(R.string.profile);

            }
            return null;
        }

    }
}