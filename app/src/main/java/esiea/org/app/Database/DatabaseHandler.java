package esiea.org.app.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import esiea.org.app.Model.Login;
import esiea.org.app.Model.Profile;
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
    private static final String TABLE_LOGIN = "login";
    private static final String TABLE_COMPETENCE = "competences";
    private static final String TABLE_COMPETENCE_FROFILE= "competences_profile";
    private static final String TABLE_PROFILE ="profile";

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


    //create tables
    private static final String CREATE_TABLE_LOGIN = "CREATE TABLE login (id integer primary key autoincrement, username text ," +
            " password text, profil integer, created_at datetime, UNIQUE(username))";

    private static final String CREATE_TABLE_PROFILE =  "CREATE TABLE profile(id integer, firstname text, lastname text, email text, age integer," +
            "city text, job text,isfilled text, foreign key(id) references user(id))";

    private static final String CREATE_TABLE_COMPETENCE = "CREATE TABLE competences (id integer primary key autoincrement, name text)";

    private static final String CREATE_TABLE_COMPETENCE_PROFILE = "CREATE TABLE competences_profile ( profile_id integer, competence_id integer,experience text," +
            "recommendation integer, foreign key(profile_id) references profile(id)," +
            "foreign key (competence_id) references competences(id),UNIQUE (profile_id,competence_id))";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_LOGIN);
        db.execSQL(CREATE_TABLE_PROFILE);
        db.execSQL(CREATE_TABLE_COMPETENCE);
        db.execSQL(CREATE_TABLE_COMPETENCE_PROFILE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldValue, int newValue) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPETENCE_FROFILE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPETENCE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
        onCreate(db);
    }

    //create competences


    //create a login
    public void createUser(Login login) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", login.getUsername());
        values.put("password", Encrypt.md5Encrypt(login.getPassword()));
        values.put("profil", login.getType());
        values.put(KEY_CREATED_AT, getDateTime());

        // insert row
        long user_id = db.insert(TABLE_LOGIN, null, values);
        if( user_id == -1){ // PROBLEM
            Log.e("DATABASE"," PROBLEEEEEEEEEEEEEEMMMME");
        }
        else
        {
            login.setId((int) user_id);
            long profile_id = createProfile(login.getProfile());
            if(profile_id==-1)
                Log.e("DATABASE","PROFILE CREATION ECHEC");
        }

        db.close();

    }

    public long createProfile(Profile profile){

        ContentValues values = new ContentValues();
        values.put("id",profile.getId());
        values.put("firstname",profile.getFirstname());
        values.put("lastname",profile.getLastname());
        values.put("email",profile.getEmail());
        values.put("age",profile.getAge());
        values.put("city",profile.getCity());
        values.put("job",profile.getJob());
        values.put("isfilled",profile.getIsFilled());
        long profile_id = this.getWritableDatabase().insert("profile",null,values);
        this.getWritableDatabase().close();
        return profile_id;
    }

    public void createUserFromJSON(JSONArray array)
    {

    }

    //recuperer les objets users
    public List<Login> getAllUsers() {
        List<Login> login = new ArrayList<Login>();
        String selectQuery = "SELECT  * FROM login";

        Log.e("Database ", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        /*for(String name:c.getColumnNames())
                Log.e("Database",name);*/

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {

                int id = c.getInt(c.getColumnIndex("id"));
                String username = c.getString(c.getColumnIndex("username"));
                String password = c.getString(c.getColumnIndex("password"));
                int profil = c.getInt(c.getColumnIndex("profil"));
                Login u = new Login(username,password,profil);
                u.setId(id);
                login.add(u);
            } while (c.moveToNext());
        }
        Log.e("Database ", login.size()+"");

        db.close();
        return login;
    }

    public List<Profile> getConsultants() {

        List<Profile> profile = new ArrayList<Profile>();
        String selectQuery = "SELECT  * FROM profile left join login on profile.id=login.id where profil="+1; // 0=client 1=consultant

        Log.e("Database", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                String firstname= c.getString(c.getColumnIndex("firstname"));
                String lastname = c.getString(c.getColumnIndex("lastname"));
                String email = c.getString(c.getColumnIndex("email"));
                int age = c.getInt(c.getColumnIndex("age"));
                String city = c.getString(c.getColumnIndex("city"));
                String job = c.getString(c.getColumnIndex("job"));
                String isfilled = c.getString(c.getColumnIndex("isfilled"));

                Profile p = new Profile();
                p.setFirstname(firstname);
                p.setLastname(lastname);
                p.setEmail(email);
                p.setAge(age);
                p.setCity(city);
                p.setJob(job);
                p.setIsFilled(isfilled);
                profile.add(p);

            } while (c.moveToNext());
        }

        db.close();
        return profile;
    }


    public List<Login> getAllCompetences(String competence) {
        List<Login> login = new ArrayList<Login>();

        String selectQuery = "SELECT  * profile LEFT JOIN profile.id=";

        Log.e("Database ", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                String username = c.getString(c.getColumnIndex("username"));
                String password = c.getString(c.getColumnIndex("password"));
                Login u = new Login(username,password,2);
                login.add(u);
            } while (c.moveToNext());
        }

        db.close();
        return login;
    }



    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
