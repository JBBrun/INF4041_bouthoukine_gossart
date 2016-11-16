package esiea.org.app.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import esiea.org.app.Model.User;
import esiea.org.app.Security.Encrypt;

/**
 * Created by Ayoub Bouthoukine on 07/11/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "consultingdb";

    // Table Names
    private static final String TABLE_USER = "user";
    private static final String TABLE_COMPETENCE = "competences";
    private static final String TABLE_COMPETENCE_USER = "competences_user";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";
    private static final String NAME ="name";
    private static final String EMAIL ="email";
    private static final String PASSWORD ="password";
    private static final String PROFIL ="profil";

    private static final String EXPERIENCE ="experience";
    private static final String USER_ID ="user_id";
    private static final String COMPETENCE_ID ="competence_id";



    private static final String CREATE_TABLE_USER = "CREATE TABLE user (id integer primary key autoincrement, name text, email text," +
            " password text, profil int, created_at datetime)";

    private static final String CREATE_TABLE_COMPETENCE = "CREATE TABLE competences (id integer primary key autoincrement, name text)";

    private static final String CREATE_TABLE_COMPETENCE_USER = "CREATE TABLE competences_users ( user_id integer, competence_id integer,experience text, foreign key(user_id) references users(id)," +
            "foreign key (competence_id) references competences(id),UNIQUE (user_id,competence_id))";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_COMPETENCE);
        db.execSQL(CREATE_TABLE_COMPETENCE_USER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldValue, int newValue) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    //create competences


    //create a user
    public long createUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("password", Encrypt.md5Encrypt(user.getPassword()));
        values.put("profil",user.getProfil());
        values.put(KEY_CREATED_AT, getDateTime());

        // insert row
        long user_id = db.insert(TABLE_USER, null, values);
        Log.d("Database","Result : "+user_id);
        return user_id;
    }
    //recuperer les objets users
    public List<User> getAllUsers() {
        List<User> user = new ArrayList<User>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        Log.e("Database ", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        for(String name:c.getColumnNames())
                Log.e("Database",name);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {

                int id = c.getInt(c.getColumnIndex("id"));
                String name = c.getString(c.getColumnIndex("name"));
                String email = c.getString(c.getColumnIndex("email"));
                String password = c.getString(c.getColumnIndex("password"));
                int profil = c.getInt(c.getColumnIndex("profil"));
                User u = new User(name,email,password,profil);
                u.setId(id);
                user.add(u);
            } while (c.moveToNext());
        }
        Log.e("Database ", user.size()+"");
        return user;
    }

    public List<User> getClients() {

        List<User> user = new ArrayList<User>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER + " where profil="+0; // 1=consultant

        Log.e("Database", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                String name = c.getString(c.getColumnIndex("name"));
                String email = c.getString(c.getColumnIndex("email"));
                String password = c.getString(c.getColumnIndex("password"));
                int profil = Integer.parseInt(c.getString(c.getColumnIndex("profil")));
                User u = new User(name,email,password,profil);
                user.add(u);
            } while (c.moveToNext());
        }
        return user;
    }


    public List<User> getAllCompetences( String competence) {
        List<User> user = new ArrayList<User>();

        String selectQuery = "SELECT  * USERS LEFT JOIN  " + TABLE_COMPETENCE_USER +" ON USERS.id = "+ TABLE_COMPETENCE_USER
                + "."+USER_ID +" LEFT JOIN "+TABLE_COMPETENCE+" ON "+TABLE_COMPETENCE_USER+"."+COMPETENCE_ID+"="+TABLE_COMPETENCE +
                "."+COMPETENCE_ID+" where "+TABLE_COMPETENCE+".name ="+"'" +competence+"'";

        Log.e("Database ", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                String name = c.getString(c.getColumnIndex("name"));
                String email = c.getString(c.getColumnIndex("email"));
                String password = c.getString(c.getColumnIndex("password"));
                User u = new User(name,email,password,2);
                user.add(u);
            } while (c.moveToNext());
        }
        return user;
    }



    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
