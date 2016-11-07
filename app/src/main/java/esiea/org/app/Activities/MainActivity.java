package esiea.org.app.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import esiea.org.app.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void goToSignIn(View v)
    {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    public void goToSignUp(View v)
    {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
