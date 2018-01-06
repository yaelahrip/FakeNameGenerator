package com.rexaflux.fakename;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        getDataFAKE();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Reload New Fake Datas ...", Snackbar.LENGTH_LONG)
                       // .setAction("Action", null).show();
                getDataFAKE();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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


    public void getDataFAKE()
    {
        request mycurl = new request();
        try {
            String responsenya = mycurl.curl("http://uinames.com/api/?ext");
            JSONObject jsonObj = new JSONObject(responsenya);

            // NAMA
            String nama = jsonObj.getString("name");
            TextView nmx = (TextView) findViewById(R.id.resName);
            nmx.setText("Name : "+ nama);

            // SURNAME
            String sure = jsonObj.getString("surname");
            TextView srx = (TextView) findViewById(R.id.resSure);
            srx.setText("SureName : "+ sure);

            // GENDER
            String gender = jsonObj.getString("gender");
            TextView gndrx = (TextView) findViewById(R.id.resGender);
            gndrx.setText("Gender : "+ gender);

            // REGION
            String region = jsonObj.getString("region");
            TextView rgnx = (TextView) findViewById(R.id.resRegion);
            rgnx.setText("Region : "+ region);



            // Age
            String age = jsonObj.getString("age");
            TextView agex = (TextView) findViewById(R.id.resAge);
            agex.setText("Age : "+ age);

            // Phone
            String phone = jsonObj.getString("phone");
            TextView phnx = (TextView) findViewById(R.id.resPhone);
            phnx.setText("Phone : "+ phone);

            // Email
            String email = jsonObj.getString("email");
            TextView emailx = (TextView) findViewById(R.id.resEmail);
            emailx.setText("Email : "+ email);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

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

    @SuppressLint("CutPasteId")
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.nav_addnew)
        {
            getDataFAKE();

            //DrawerLayout mDrawerLayout;
            //mDrawerLayout = (DrawerLayout) findViewById(R.id.nav_view);
            //mDrawerLayout.closeDrawers();


        }else if (id == R.id.nav_about)
        {
            Intent aboutx = new Intent(MainActivity.this,About.class);
            startActivity(aboutx);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
