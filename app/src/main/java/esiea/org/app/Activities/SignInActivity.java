package esiea.org.app.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

import esiea.org.app.Database.DatabaseHandler;
import esiea.org.app.Model.User;
import esiea.org.app.R;

public class SignInActivity extends AppCompatActivity {

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void submitSignIn(View v)
    {
        String email = emailEdit.getText().toString();
        String password = passEdit.getText().toString();
        User u = isFound(email,password);
        if(u!=null)
        {
            Intent intent = new Intent(this,HomeActivity.class);
            intent.putExtra("profil",u.getProfil());
            startActivity(intent);
            finish();
        }
        else
        {
            errorText.setText(R.string.error_connection);
        }
    }

    public void goToSignUp(View v)
    {
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }

    public User isFound(String email,String password)
    {
        List <User> userList = db.getAllUsers();
        Iterator it = userList.iterator();
        while (it.hasNext())
        {
            User u = (User) it.next();
            if (u.getEmail().equals(email) && u.getPassword().equals(password))
                return u;
        }
        return null;
    }
}
