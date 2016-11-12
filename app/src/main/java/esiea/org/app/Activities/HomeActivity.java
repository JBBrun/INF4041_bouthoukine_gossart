package esiea.org.app.Activities;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import esiea.org.app.Database.DatabaseHandler;
import esiea.org.app.Model.User;
import esiea.org.app.Adapter.ListAdapter;
import esiea.org.app.R;

public class HomeActivity extends AppCompatActivity {


    private ListView listView;
    private ListAdapter adapter;
    private List<User> userList;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        db = new DatabaseHandler(getApplicationContext());
        int profil = getIntent().getIntExtra("profil",0);
        // instantiate
        listView = (ListView) findViewById(R.id.list);
        userList = new ArrayList<User>();
        userList = db.getUsersByProfil(profil);
        adapter = new ListAdapter(this, userList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(HomeActivity.this,userList.get(i).getName(),Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.java:
                // User chose the "Settings" item, show the app settings UI...
                return true ;

            case R.id.cSharp:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            case R.id.cpp:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            case R.id.c:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            case R.id.python:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


}
