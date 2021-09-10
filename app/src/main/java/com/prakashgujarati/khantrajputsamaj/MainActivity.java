package com.prakashgujarati.khantrajputsamaj;

import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.prakashgujarati.khantrajputsamaj.commans.BaseActivity;
import com.prakashgujarati.khantrajputsamaj.fragment.DashboardFragment;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;
import com.prakashgujarati.khantrajputsamaj.utils.PreferenceUtils;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, new DashboardFragment()).commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        callLoginApi();

    }

    private void callLoginApi() {
        PreferenceUtils.setString(Constant.PreferenceConstant.TOKEN, "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiZDJiNjZlOWE4MGUxMWFkMzdkOGJkZDczNDBhMmFmZTIzM2U1YjZlOGY1M2I3ZGJhMDFkNWUzOTdmMTY1YzllYjg4Y2QwZGY0ZmE0MGQ2NjMiLCJpYXQiOjE2MzA3NTQ4MzksIm5iZiI6MTYzMDc1NDgzOSwiZXhwIjoxNjYyMjkwODM5LCJzdWIiOiIxMSIsInNjb3BlcyI6W119.Mnbf7NNOLkuISzzJP2X9FRVyhKQYJG80xKKKtR2Y49YMxuN7-jM5fw5NH5zsXNmJDD_xSTy1ZXO8Ok4woik5r5pVrU1w6OTAaAAGpPUVvRuRa5eleVg0sHqoXj53dTtfRvhzjVPDHw0YerwUZgKvdGLpp_WDXKCwOUMdNLIJygtsNDRS5U-0gObFqFo3ebTA00eZr6zgXut2b5NiBTgPn3GT0CE2tAfOf6nIjd-C_CJ6e6ie7b5gTeTykJjsYZyW8SMDP9z_ESX0jDeG4nN0zlIeHDDofMiF1m6xF96fCBloJD370incMeXCBRxB_i-9EPYXYtaZQHdPbwM5_baiLE9gA2o9gMzDRWPTd-JZE3hBmpnagVLmf-BV1Cvet01ezkzf-zFFeQgSk-cJDujcfxIkllk2RRqCqe3UtajGDPt6ZiIdSSprdNmTv-oKlNgNgvYu-81clyiVAJ7bv_4hgHRRJCSWwNx74ZWeTUiqYovGOdnz1TrEyRsZwcRU2GXbNhoBLLPjs8MXt_-YtIMVO4e1TVksruIyXKSzYUHTfxUkIPOd2loqrY_So2gjaz89BF6LDB6ABaEmbfQWqCQUIBnRnmRo1UHfIxnaCACfeqNKUNZHvtvD_BqFS_WTEMvQYP47LBo1sYy_t9dw1GYTU7CXSYbAo2GrD-u63S89BBg", this);
    }

  /*  @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void ObjactView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, new DashboardFragment()).commit();
    }
}
