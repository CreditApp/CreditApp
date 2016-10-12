package com.example.android.itcreditonline;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.android.itcreditonline.Model.Database.DBManager;
import com.example.android.itcreditonline.Model.ReadRss;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private  ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private int[] tabIcons = {
            R.drawable.news_red,
            R.drawable.profile_gray,
            R.drawable.wallet_gray,
            R.drawable.calculator_gray,
            R.drawable.handshake,
            R.drawable.about_gray,
            R.drawable.map_gray
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String loggedUser = intent.getStringExtra("loggedUser");
        DBManager.getInstance(this).saveLastLoggedUser(loggedUser);
        tab();

    }

    @Override
    public void onBackPressed()
    {
        //set tabs to unselected color
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            int tabIconColor = ContextCompat.getColor(MainActivity.this, R.color.tabUnselectedIconColor);
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
        }

        //set news tab to selected color
        int tabIconColor = ContextCompat.getColor(MainActivity.this, R.color.tabSelectedIconColor);
        TabLayout.Tab tab = tabLayout.getTabAt(0);
        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
        
        super.onBackPressed();
    }

    public void tab(){
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        tabLayout.addOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        int tabIconColor = ContextCompat.getColor(MainActivity.this, R.color.tabSelectedIconColor);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                        ((CreditsFragment)adapter.getItem(2)).refreshsAdapter();
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        int tabIconColor = ContextCompat.getColor(MainActivity.this, R.color.tabUnselectedIconColor);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                        ((CreditsFragment)adapter.getItem(2)).refreshsAdapter();
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                        super.onTabReselected(tab);
                        ((CreditsFragment)adapter.getItem(2)).refreshsAdapter();
                    }
                }
        );

    }
    private void setupTabIcons() {
        for (int i = 0; i < tabIcons.length;i++){
            tabLayout.getTabAt(i).setIcon(tabIcons[i]);
        }
    }

    public void setupViewPager(ViewPager viewPager){
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new NewsFragment(),"News");
        adapter.addFrag(new ProfileFragment(), "Profile");
        adapter.addFrag(new CreditsFragment(), "Credits");
        adapter.addFrag(new CalculatorFragment(), "Calculator");
        adapter.addFrag(new ApplyFragment(), "Apply");
        adapter.addFrag(new AboutFragment(), "About");
        adapter.addFrag(new ProfileFragment(), "Map");
        viewPager.setAdapter(adapter);
    }

    //adapter for view pager
    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList =new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }


        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment,String title){
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}







