package esiea.org.app.Activities;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import esiea.org.app.Database.DatabaseHandler;
import esiea.org.app.Model.Login;
import esiea.org.app.Adapter.ListAdapter;
import esiea.org.app.Model.Profile;
import esiea.org.app.R;

public class ClientActivity extends AppCompatActivity {

    private ListView listView;
    private ListAdapter adapter;
    private List<Profile> profileList;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        db = new DatabaseHandler(getApplicationContext());
        addData(db);
        // instantiate
        listView = (ListView) findViewById(R.id.list);
        profileList = new ArrayList<Profile>();
        profileList = db.getConsultants();

        Iterator <Profile> it = profileList.iterator();
        while(it.hasNext())
        {
            Profile p = it.next();
            if(p.getIsFilled().equals("NO"))
                it.remove();
        }


        adapter = new ListAdapter(this, profileList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ClientActivity.this, profileList.get(i).getFirstname(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),ProfilActivity.class);
                intent.putExtra("user", profileList.get(i).getId());
                startActivity(intent);

            }
        });


    }

    public void addData(final DatabaseHandler db) {
        String url = "https://api.myjson.com/bins/3gik6";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject json) {
                        try {
                            JSONArray response = json.getJSONObject("data").getJSONArray("items");
                            Login login ;
                            Profile profile = new Profile();
                            for(int i=0;i<response.length();i++)
                            {
                                JSONObject obj = response.getJSONObject(i);

                                //remplire les donneés
                                login = new Login(obj.getString("username"),obj.getString("password"),obj.getInt("profil"));
                                profile.setFirstname(obj.getString("firstname"));
                                profile.setLastname(obj.getString("lastname"));
                                profile.setEmail(obj.getString("email"));
                                profile.setAge(obj.getInt("age"));
                                profile.setCity(obj.getString("city"));
                                profile.setJob(obj.getString("job"));
                                profile.setIsFilled("YES");
                                login.setProfile(profile);
                                db.createUser(login);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.e("ACCESS ","DENIEDEEEEEEEEEEEEED");
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
                // Login chose the "Settings" item, show the app settings UI...
                return true ;

            case R.id.cSharp:
                // Login chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            case R.id.cpp:
                // Login chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            case R.id.c:
                // Login chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            case R.id.python:
                // Login chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


}
