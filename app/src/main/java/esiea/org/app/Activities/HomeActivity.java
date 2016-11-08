package esiea.org.app.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import esiea.org.app.Database.DatabaseHandler;
import esiea.org.app.Database.User;
import esiea.org.app.ListAdapter;
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
        // instantiate
        listView = (ListView) findViewById(R.id.list);
        userList = new ArrayList<User>();
        adapter = new ListAdapter(this, userList);
        listView.setAdapter(adapter);


        User u = new User("Ayoub","ay@ay.com","ayoub",1);
        userList.add(u);
        //userList = db.getAllUsers();

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


}
