package esiea.org.app.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

import esiea.org.app.Database.DatabaseHandler;
import esiea.org.app.Model.User;
import esiea.org.app.R;
import esiea.org.app.Security.Encrypt;

public class SignInActivity extends Activity {

    DatabaseHandler db;
    EditText emailEdit;
    EditText passEdit;
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
        User u = isFound(email,password);
        if(u!=null)
        {
            Intent intent;
            if(u.getProfil() == 0) // Client
            {
                intent = new Intent(this,ClientActivity.class);

            }
            else
            {
               intent = new Intent(this,ConsultantActivity.class);
            }
            startActivity(intent);
            finish();
        }
        else
        {
            errorText.setText(R.string.error_connection);
            passEdit.setText("");
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
        password = Encrypt.md5Encrypt(password);
        while (it.hasNext())
        {
            User u = (User) it.next();
            if (u.getEmail().equals(email) && u.getPassword().equals(password))
                return u;
        }
        return null;
    }
}
