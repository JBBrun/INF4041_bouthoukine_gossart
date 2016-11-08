package esiea.org.app.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ayoub Bouthoukine on 07/11/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "consultingdb";

    // Table Names
    private static final String TABLE_USER = "users";
    private static final String TABLE_COMPETENCE = "competences";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";
    private static final String NAME ="name";
    private static final String EMAIL ="email";
    private static final String PASSWORD ="password";
    private static final String PROFIL ="profil";

    private static final String FIRSTCOMPETENCE ="firstCompetence";
    private static final String SECONDCOMPETENCE ="secondCompetences";
    private static final String EXPERIENCE ="experience";
    private static final String USER_ID ="user_id";
    private static final String COMPETENCE_ID ="competence_id";





    private static final String CREATE_TABLE_USERS = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME +"TEXT," + EMAIL + " TEXT," + PASSWORD + " TEXT," + PROFIL + "INTEGER, " + KEY_CREATED_AT
            + " DATETIME" + ")";

 /*   private static final String CREATE_TABLE_USERS = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, email TEXT, password TEXT, profil INTEGER, " + KEY_CREATED_AT
            + " DATETIME" + ")";
*/


    private static final String CREATE_TABLE_COMPETENCE = "CREATE TABLE "
            + TABLE_COMPETENCE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ NAME +" TEXT)";


    private static final String CREATE_TABLE_COMPETENCE_USER = "CREATE TABLE "
            + TABLE_COMPETENCE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ USER_ID + "FOREIGN KEY("+ USER_ID+") REFERENCES"+TABLE_USER+"(id)"+ "FOREIGN KEY"+ COMPETENCE_ID+") REFERENCES"+TABLE_COMPETENCE+"(id)"+")";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_COMPETENCE);
        db.execSQL(CREATE_TABLE_COMPETENCE_USER);

        User user1 = new User("nicolas","go@go.com","gogo",1);
        User user2 = new User("ayoub","ay@ay.com","ayay",1);
        User user3 = new User("farouk","fa@fa.com","fafa",1);
        User user4 = new User("khaled","kha@kha.com","khakha",1);
        createUser(user1);
        createUser(user2);
        createUser(user3);
        createUser(user4);


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
        values.put("password", user.getPassword());
        values.put(KEY_CREATED_AT, getDateTime());

        // insert row
        long user_id = db.insertOrThrow(TABLE_USER, null, values);

        return user_id;
    }
    //recuperer les objets users
    public List<User> getAllUsers() {
        List<User> user = new ArrayList<User>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

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
