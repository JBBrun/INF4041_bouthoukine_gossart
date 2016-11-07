package esiea.org.app.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import esiea.org.app.Database.DatabaseHandler;
import esiea.org.app.Database.User;
import esiea.org.app.R;

public class SignInActivity extends Activity {

    DatabaseHandler db;
    EditText emailEdit ;
    EditText passEdit ;
    TextView errorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        db = new DatabaseHandler(getApplicationContext());
        emailEdit = (EditText) findViewById(R.id.emailValue);
        passEdit = (EditText) findViewById(R.id.passValue);
        errorText = (TextView) findViewById(R.id.labelError);
    }

    public void submitSignIn(View v)
    {
        String email = emailEdit.getText().toString();
        String password = passEdit.getText().toString();
        if(isFound(email,password))
        {
            Intent intent = new Intent(this,HomeActivity.class);
            startActivity(intent);
        }
        else
        {
            errorText.setText(R.string.error_connection);
        }
    }

    public boolean isFound(String email,String password)
    {
        List <User> userList = db.getAllUsers();
        Iterator it = userList.iterator();
        while (it.hasNext())
        {
            User u = (User) it.next();
            if (u.getEmail().equals(email) && u.getPassword().equals(password))
                return true;
        }
        return false;
    }
}
