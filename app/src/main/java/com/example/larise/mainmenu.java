package com.example.larise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class mainmenu extends AppCompatActivity {
    FragmentPagerAdapter adapterViewPager;
    private user user;
    private TabLayout tabLayout;
    private DatabaseReference db;
    private String UID;
    private FirebaseHelper fb;
    private ProgressDialog pd;
    ViewPager vpPager;
    ImageView imgA;
    ImageView imgB;
    ImageView imgC;
    ImageView imgD;
    private int[] tabIconsActive = {
            R.drawable.home_active,
            R.drawable.cart_active,
            R.drawable.profil_active,
            R.drawable.settings_active
    };

    private int[] tabIconsInactive = {
            R.drawable.home_inactive,
            R.drawable.cart_inactive,
            R.drawable.profil_inactive,
            R.drawable.settings_inactive
    };
    private void setupTabIcons() {

        imgA.setImageResource(tabIconsActive[0]);
        imgA.setBackgroundColor(getResources().getColor(R.color.PrimaryDark));
        tabLayout.getTabAt(0).setCustomView(imgA);

        imgB.setImageResource(tabIconsInactive[1]);
        imgB.setBackgroundColor(getResources().getColor(R.color.Surface));
        tabLayout.getTabAt(1).setCustomView(imgB);

        imgC.setImageResource(tabIconsInactive[2]);
        imgC.setBackgroundColor(getResources().getColor(R.color.Surface));
        tabLayout.getTabAt(2).setCustomView(imgC);

        imgD.setImageResource(tabIconsInactive[3]);
        imgD.setBackgroundColor(getResources().getColor(R.color.Surface));
        tabLayout.getTabAt(3).setCustomView(imgD);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        vpPager = findViewById(R.id.vpager);
        pd = new ProgressDialog(mainmenu.this);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        imgA = new ImageView(mainmenu.this);
        imgB = new ImageView(mainmenu.this);
        imgC = new ImageView(mainmenu.this);
        imgD = new ImageView(mainmenu.this);
        tabLayout.setupWithViewPager(vpPager);
        addTabs(vpPager);
        setupTabIcons();
        Intent intent = getIntent();
        UID = intent.getStringExtra("UID");
        fb = (FirebaseHelper) intent.getSerializableExtra("FB");

        if(fb.getUs()!=null){
            Log.e("MAIN",fb.getUs().getEmail());
        }
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        imgA.setImageResource(tabIconsActive[0]);
                        imgA.setBackgroundColor(getResources().getColor(R.color.PrimaryDark));
                        tabLayout.getTabAt(0).setCustomView(imgA);

                        imgB.setImageResource(tabIconsInactive[1]);
                        imgB.setBackgroundColor(getResources().getColor(R.color.Surface));
                        tabLayout.getTabAt(1).setCustomView(imgB);

                        imgC.setImageResource(tabIconsInactive[2]);
                        imgC.setBackgroundColor(getResources().getColor(R.color.Surface));
                        tabLayout.getTabAt(2).setCustomView(imgC);

                        imgD.setImageResource(tabIconsInactive[3]);
                        imgD.setBackgroundColor(getResources().getColor(R.color.Surface));
                        tabLayout.getTabAt(3).setCustomView(imgD);

                        break;
                    case 1:
                        imgA.setImageResource(tabIconsInactive[0]);
                        imgA.setBackgroundColor(getResources().getColor(R.color.Surface));
                        tabLayout.getTabAt(0).setCustomView(imgA);

                        imgB.setImageResource(tabIconsActive[1]);
                        imgB.setBackgroundColor(getResources().getColor(R.color.PrimaryDark));
                        tabLayout.getTabAt(1).setCustomView(imgB);

                        imgC.setImageResource(tabIconsInactive[2]);
                        imgC.setBackgroundColor(getResources().getColor(R.color.Surface));
                        tabLayout.getTabAt(2).setCustomView(imgC);

                        imgD.setImageResource(tabIconsInactive[3]);
                        imgD.setBackgroundColor(getResources().getColor(R.color.Surface));
                        tabLayout.getTabAt(3).setCustomView(imgD);
                        break;
                    case 2:
                        imgA.setImageResource(tabIconsInactive[0]);
                        imgA.setBackgroundColor(getResources().getColor(R.color.Surface));
                        tabLayout.getTabAt(0).setCustomView(imgA);

                        imgB.setImageResource(tabIconsInactive[1]);
                        imgB.setBackgroundColor(getResources().getColor(R.color.Surface));
                        tabLayout.getTabAt(1).setCustomView(imgB);

                        imgC.setImageResource(tabIconsActive[2]);
                        imgC.setBackgroundColor(getResources().getColor(R.color.PrimaryDark));
                        tabLayout.getTabAt(2).setCustomView(imgC);

                        imgD.setImageResource(tabIconsInactive[3]);
                        imgD.setBackgroundColor(getResources().getColor(R.color.Surface));
                        tabLayout.getTabAt(3).setCustomView(imgD);
                        break;
                    case 3:
                        imgA.setImageResource(tabIconsInactive[0]);
                        imgA.setBackgroundColor(getResources().getColor(R.color.Surface));
                        tabLayout.getTabAt(0).setCustomView(imgA);

                        imgB.setImageResource(tabIconsInactive[1]);
                        imgB.setBackgroundColor(getResources().getColor(R.color.Surface));
                        tabLayout.getTabAt(1).setCustomView(imgB);

                        imgC.setImageResource(tabIconsInactive[2]);
                        imgC.setBackgroundColor(getResources().getColor(R.color.Surface));
                        tabLayout.getTabAt(2).setCustomView(imgC);

                        imgD.setImageResource(tabIconsActive[3]);
                        imgD.setBackgroundColor(getResources().getColor(R.color.PrimaryDark));
                        tabLayout.getTabAt(3).setCustomView(imgD);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new menu(), "Menu");
        adapter.addFrag(new pesanan(), "Pesanan");
        Fragment prof = profil.newInstance(fb);
        adapter.addFrag(prof, "Profil");
        adapter.addFrag(new pengaturan(), "Pengaturan");
        viewPager.setAdapter(adapter);
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {

            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {

            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
