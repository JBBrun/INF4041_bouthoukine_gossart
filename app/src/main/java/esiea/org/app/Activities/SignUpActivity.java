package esiea.org.app.Activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteQuery;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import esiea.org.app.Database.DatabaseHandler;
import esiea.org.app.Database.User;
import esiea.org.app.R;

public class SignUpActivity extends Activity {

    DatabaseHandler db;
    EditText nameView ;
    EditText emailView ;
    EditText passwordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //connection à la base
        db = new DatabaseHandler(getApplicationContext());
        nameView = (EditText) findViewById(R.id.nameValue);
        emailView = (EditText) findViewById(R.id.emailValue);
        passwordView = (EditText) findViewById(R.id.passValue);

    }
    public void submitSignUp(View v)
    {
        String name = nameView.getText().toString();
        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();

        User user = new User(name,email,password,2);
        db.createUser(user);
        Toast.makeText(this,"Succcess",Toast.LENGTH_SHORT);
    }
}
