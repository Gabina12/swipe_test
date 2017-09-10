package com.example.admin.swipe;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {


    public static FragmentOne fragmentOne = new FragmentOne();
    public static FragmentTwo fragmentTwo = new FragmentTwo();

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private MainActivity context;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        context = this;
        fragmentOne.activity = this;
        fragmentTwo.activity = this;


    }


    @Override
    public void onBackPressed() {



        //ეს რა ჩემის დიშლაა? ძველი კოდია !!! ___________________________________

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }


        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, R.string.ExitToast, Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);

//    finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_lang) {

            //ბაღა ეს ნახე აბა __________________

//        public static void changeLang(this context, String lang) {
//            Locale myLocale = new Locale(lang);
//            Locale.setDefault(myLocale);
//            android.content.res.Configuration config = new android.content.res.Configuration();
//            config.locale = myLocale;
//            context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
//        }
        }
        return true;

    }



    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container,
                                 Bundle savedInstanceState) {

            int position = getArguments().getInt(ARG_SECTION_NUMBER);
            View rootView;


            switch (position) {
                case 1:
                    //  მონიშნულია პირველი ტაბი

                    // ამ დროს შეიქმნა ყველა კონტროლი
                    // მანამდე არცერთი კონტროლი არ არსებობდა
                    // არც ღილაკები არც ტექსტბოქსები არაფერი!!
                    rootView = inflater.inflate(R.layout.fragment_main, container, false);

                    fragmentOne.init(rootView);
                    break;
                case 2:
                    //  მონიშნულია მეორე ტაბი
                    rootView = inflater.inflate(R.layout.fragment_main_1, container, false);
                    fragmentTwo.init(rootView);
                    break;
                case 3:
                    //  მონიშნულია მესამე ტაბი
                    rootView = inflater.inflate(R.layout.fragment_main_2, container, false);
                    break;
                default:
                    //  მონიშნულია პირველი ტაბი
                    rootView = inflater.inflate(R.layout.fragment_main, container, false);
                    break;
            }

            return rootView;
        }
    }





    // ფრაგმენტების ადაპტერი
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        // აქ დააბრუნე გვერდების რაოდენობა
        @Override
        public int getCount() {
            return 2;
        }

        // აქ დააბრუნე შესაბამისი გვერდების სახელები
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.Tab1);
                case 1:
                    return getString(R.string.Tab2);
                case 2:
                    return getString(R.string.Tab3);
            }
            return null;
        }
    }

}
