package com.example.johnc.socialk;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    TextView idText;
    TextView user_nameText;
    String id_email;
    String user_name;
    String profile_picture;
    FloatingActionButton fab;
    Fragment fragment = null;
    boolean FragmentTransaction = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Send_Tweet.class);
                i.putExtra("id_email", id_email);
                i.putExtra("user_name", user_name);
                startActivity(i);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        id_email = intent.getStringExtra("id_email");
        user_name = intent.getStringExtra("user_name");
        profile_picture = intent.getStringExtra("profile_picture");


        View view = navigationView.getHeaderView(0);
        ImageView imageView = view.findViewById(R.id.imageView);
        idText = (TextView) view.findViewById(R.id.textView);
        user_nameText = (TextView) view.findViewById(R.id.user_name);

        idText.setText(id_email);
        user_nameText.setText(user_name);
        Picasso.with(MainActivity.this).load(profile_picture).into(imageView);





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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.action_settings) {
            Intent i = new Intent(MainActivity.this, LogIn.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.nav_perfil) {
            fragment = new User_Class();
            Bundle b = new Bundle();
            b.putString("id_email", id_email);
            b.putString("user_name", user_name);
            b.putString("id_user_name", user_name);
            b.putString("id_profile_picture", profile_picture);
            b.putString("uniqueID2", "uniqueID2");
            fragment.setArguments(b);
            fab.setVisibility(View.VISIBLE);


        } else if (id == R.id.nav_follows) {
            fragment = new Fragment_Follows();
            Bundle b = new Bundle();
            b.putString("id_email", id_email);
            b.putString("id_user_name", user_name);
            b.putString("id_profile_picture", profile_picture);
            fragment.setArguments(b);
            fab.setVisibility(View.VISIBLE);


            FragmentTransaction = true;

        } else if (id == R.id.nav_followers) {
            fragment = new Fragment_Followers();
            Bundle b = new Bundle();
            b.putString("id_email", id_email);
            b.putString("id_user_name", user_name);
            b.putString("id_profile_picture", profile_picture);
            fragment.setArguments(b);
            FragmentTransaction = true;
            fab.setVisibility(View.VISIBLE);


        } else if (id == R.id.nav_message) {
            fragment = new Fragment_Tweets();
            Bundle b = new Bundle();
            b.putString("id_email", id_email);
            b.putString("id_user_name", user_name);
            b.putString("id_profile_picture", profile_picture);
            fragment.setArguments(b);
            FragmentTransaction = true;
            fab.setVisibility(View.VISIBLE);


        } else if (id == R.id.nav_search) {
            fragment = new Search_User();
            Bundle b = new Bundle();
            b.putString("id_email", id_email);
            b.putString("id_user_name", user_name);
            b.putString("id_profile_picture", profile_picture);
            fragment.setArguments(b);
            FragmentTransaction = true;
            fab.setVisibility(View.INVISIBLE);


        }

        if (FragmentTransaction) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
            item.setChecked(true);

        }
        onBackPressed();
        return true;
    }



}