package com.darrellii.flickr.flickrviewer.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.darrellii.flickr.flickrviewer.R;
import com.darrellii.flickr.flickrviewer.app.fragment.FlickrListFragment;
import com.darrellii.flickr.flickrviewer.network.OAuth;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        FlickrListFragment.OnImageClickedListener{


    public enum MenuItems {
        INTERESTINGNESS("Interestingness"),
        MY_PHOTOS("My Photos"),
        MY_FAVORITES("My Favorites");

        String mName;
        MenuItems(String name){
            mName = name;
        }

    }

    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        changeFragment(MenuItems.INTERESTINGNESS);

    }

    public void changeFragment(MenuItems item) {
        Fragment fragment = new FlickrListFragment();
        Bundle b = new Bundle();
        b.putInt(getString(R.string.menu_item), item.ordinal());
        fragment.setArguments(b);
        getSupportActionBar().setTitle(item.mName);
        getFragmentManager().executePendingTransactions();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_content, fragment)
                .commitAllowingStateLoss();
        mDrawerLayout.closeDrawers();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_photo_stream) {
            changeFragment(MenuItems.INTERESTINGNESS);
        } else if (id == R.id.nav_my_photo) {
            changeFragment(MenuItems.MY_PHOTOS);
        } else if (id == R.id.nav_my_favorites) {
            changeFragment(MenuItems.MY_FAVORITES);
        } else if (id == R.id.nav_my_logout) {
            OAuth oauth = OAuth.newInstance(getApplicationContext(),
                    getSupportFragmentManager());
            oauth.deleteCredential("flickr");
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onImageClicked(String imageUrl) {
        Intent i = new Intent(this, FullScreenImageActivity.class);
        i.putExtra("imageUrl", imageUrl);
        startActivity(i);
    }


}
