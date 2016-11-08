package esiea.org.app.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import esiea.org.app.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void goToSignIn(View v)
    {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void goToSignUp(View v)
    {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        this.finish();
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
